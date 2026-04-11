package com.gtnewhorizon.newgunrizons.client.animation;

import com.gtnewhorizon.newgunrizons.state.RenderableState;
import java.util.List;

public interface MultipartTransitionProvider {
   List<MultipartTransition> getPositioning(RenderableState var1);
}
