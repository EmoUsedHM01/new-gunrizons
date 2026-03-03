package com.gtnewhorizon.newgunrizons.client.resource;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TransformingResourceManager implements IResourceManager {

    private final IResourceManager delegate;
    private final UnaryOperator<ResourceLocation> locationTransformer;

    public TransformingResourceManager(IResourceManager delegate, UnaryOperator<ResourceLocation> locationTransformer) {
        this.delegate = delegate;
        this.locationTransformer = locationTransformer;
    }

    public Set<String> getResourceDomains() {
        return this.delegate.getResourceDomains();
    }

    public IResource getResource(ResourceLocation location) throws IOException {
        return this.delegate.getResource(this.locationTransformer.apply(location));
    }

    public List<IResource> getAllResources(ResourceLocation location) throws IOException {
        return this.delegate.getAllResources(this.locationTransformer.apply(location));
    }
}
