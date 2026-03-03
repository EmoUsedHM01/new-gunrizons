package com.gtnewhorizon.newgunrizons.state;

import java.util.function.BiConsumer;

public interface PermitManager {

    <S extends ManagedState<S>, P extends Permit<S>, E extends ExtendedState<S>> void request(P var1, E var2,
        BiConsumer<P, E> var3);

    <S extends ManagedState<S>, P extends Permit<S>, E extends ExtendedState<S>> void registerEvaluator(
        Class<? extends P> var1, Class<? extends E> var2, BiConsumer<P, E> var3);
}
