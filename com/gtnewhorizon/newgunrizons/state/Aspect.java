package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;

public interface Aspect<T extends ManagedState<T>, E extends ItemInstance<T>> {
   void setStateManager(StateManager<T, ? super E> var1);
}
