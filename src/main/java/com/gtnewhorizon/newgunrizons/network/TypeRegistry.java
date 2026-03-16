package com.gtnewhorizon.newgunrizons.network;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.buffer.ByteBuf;
import lombok.Getter;

public class TypeRegistry {

    private static final String SHA1PRNG_ALG = "SHA1PRNG";
    private final ConcurrentMap<UUID, Class<? extends UniversallySerializable>> typeRegistry = new ConcurrentHashMap<>();
    @Getter
    private static final TypeRegistry instance = new TypeRegistry();

    private TypeRegistry() {}

    public <T extends UniversallySerializable> void register(Class<T> cls) {
        this.typeRegistry.put(this.getUuid(cls), cls);
    }

    public UUID getUuid(Class<?> cls) {
        try {
            SecureRandom random = SecureRandom.getInstance(SHA1PRNG_ALG);
            random.setSeed(
                cls.getName()
                    .getBytes());
            return new UUID(random.nextLong(), random.nextLong());
        } catch (NoSuchAlgorithmException e) {
            return UUID.fromString(
                this.getClass()
                    .getName());
        }
    }

    public void toBytes(UniversallySerializable object, ByteBuf buf) {
        UUID typeUuid = this.getUuid(object.getClass());
        if (!this.typeRegistry.containsKey(typeUuid)) {
            throw new RuntimeException(
                "Failed to serialize object " + object + " because its class is not registered: " + object.getClass());
        } else {
            buf.writeLong(typeUuid.getMostSignificantBits());
            buf.writeLong(typeUuid.getLeastSignificantBits());
            if (object.getClass()
                .isEnum()) {
                buf.writeInt(((Enum<?>) object).ordinal());
            } else {
                object.serialize(buf);
            }
        }
    }

    public <T extends UniversallySerializable> T fromBytes(ByteBuf buf) {
        long mostSigBits = buf.readLong();
        long leastSigBits = buf.readLong();
        UUID typeUuid = new UUID(mostSigBits, leastSigBits);
        @SuppressWarnings("unchecked")
        Class<T> targetClass = (Class<T>) this.typeRegistry.get(typeUuid);
        if (targetClass == null) {
            throw new RuntimeException("Failed to deserialize object. Did you forget to register type?");
        } else {
            UniversallySerializable instance;
            if (targetClass.isEnum()) {
                T[] constants = targetClass.getEnumConstants();
                instance = constants[buf.readInt()];
            } else {
                try {
                    instance = targetClass.newInstance();
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException("Cannot create instance of  " + targetClass);
                }

                instance.init(buf);
            }

            return targetClass.cast(instance);
        }
    }
}
