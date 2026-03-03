package com.gtnewhorizon.newgunrizons.network;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

import net.minecraft.client.Minecraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.config.PlayerContext;
import com.gtnewhorizon.newgunrizons.state.ExtendedState;
import com.gtnewhorizon.newgunrizons.state.ManagedState;
import com.gtnewhorizon.newgunrizons.state.Permit;
import com.gtnewhorizon.newgunrizons.state.PermitManager;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class NetworkPermitManager implements PermitManager, IMessageHandler<PermitMessage, IMessage> {

    private static final Logger logger = LogManager.getLogger(NetworkPermitManager.class);
    private final ModContext modContext;
    private final Map<UUID, Object> permitCallbacks = new HashMap<>();
    private final Map<Class<?>, BiConsumer<Permit<?>, ?>> evaluators = new HashMap<>();

    public NetworkPermitManager(ModContext modContext) {
        this.modContext = modContext;
    }

    public <S extends ManagedState<S>, P extends Permit<S>, E extends ExtendedState<S>> void request(P permit,
        E extendedState, BiConsumer<P, E> callback) {
        this.permitCallbacks.put(permit.getUuid(), callback);
        this.modContext.getChannel()
            .sendToServer(new PermitMessage(permit, extendedState));
    }

    public <S extends ManagedState<S>, P extends Permit<S>, E extends ExtendedState<S>> void registerEvaluator(
        Class<? extends P> permitClass, Class<? extends E> esClass, BiConsumer<P, E> evaluator) {
        this.evaluators.put(permitClass, (p, c) -> {
            logger.debug("Processing permit {} for instance {}", p, c);
            evaluator.accept(permitClass.cast(p), esClass.cast(c));
        });
    }

    public IMessage onMessage(PermitMessage permitMessage, MessageContext ctx) {
        Permit<?> permit = permitMessage.getPermit();
        Object extendedState = permitMessage.getContext();
        if (ctx.side == Side.SERVER) {
            if (extendedState instanceof PlayerContext) {
                ((PlayerContext) extendedState).setPlayer(ctx.getServerHandler().playerEntity);
            }

            BiConsumer<Permit<?>, Object> evaluator = (BiConsumer) this.evaluators.get(permit.getClass());
            if (evaluator != null) {
                evaluator.accept(permit, extendedState);
            }

            PermitMessage message = new PermitMessage(permit, extendedState);
            this.modContext.getChannel()
                .sendTo(message, ctx.getServerHandler().playerEntity);
        } else {

            if (extendedState instanceof PlayerContext) {
                ((PlayerContext) extendedState).setPlayer(Minecraft.getMinecraft().thePlayer);
            }

            BiConsumer<Permit<?>, Object> callback = (BiConsumer) this.permitCallbacks.remove(permit.getUuid());
            if (callback != null) {
                callback.accept(permit, extendedState);
            }

        }

        return null;
    }
}
