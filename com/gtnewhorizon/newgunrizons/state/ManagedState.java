package com.gtnewhorizon.newgunrizons.state;

import com.gtnewhorizon.newgunrizons.network.UniversallySerializable;

public interface ManagedState<T extends ManagedState<T>> extends UniversallySerializable {
   default T getPreparingPhase() {
      return null;
   }

   default T getCommitPhase() {
      return null;
   }
}
