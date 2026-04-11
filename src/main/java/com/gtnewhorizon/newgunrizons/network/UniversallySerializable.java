package com.gtnewhorizon.newgunrizons.network;

import io.netty.buffer.ByteBuf;

public interface UniversallySerializable {
   default void init(ByteBuf buf) {
   }

   default void serialize(ByteBuf buf) {
   }
}
