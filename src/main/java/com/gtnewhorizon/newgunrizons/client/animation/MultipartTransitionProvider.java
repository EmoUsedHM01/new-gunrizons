package com.gtnewhorizon.newgunrizons.client.animation;

import java.util.List;

import com.gtnewhorizon.newgunrizons.state.RenderableState;

public interface MultipartTransitionProvider {

    List<MultipartTransition> getPositioning(RenderableState var1);
}
