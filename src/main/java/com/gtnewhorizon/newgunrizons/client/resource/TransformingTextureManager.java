package com.gtnewhorizon.newgunrizons.client.resource;

import java.util.function.UnaryOperator;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TransformingTextureManager extends TextureManager {

    private final TextureManager delegate;
    private final UnaryOperator<ResourceLocation> locationTransformer;

    public TransformingTextureManager(TextureManager delegate, UnaryOperator<ResourceLocation> locationTransformer) {
        super(null);
        this.delegate = delegate;
        this.locationTransformer = locationTransformer;
    }

    public void bindTexture(ResourceLocation resource) {
        this.delegate.bindTexture(this.locationTransformer.apply(resource));
    }

    public boolean loadTickableTexture(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
        return this.delegate.loadTickableTexture(this.locationTransformer.apply(textureLocation), textureObj);
    }

    public boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj) {
        return this.delegate.loadTexture(this.locationTransformer.apply(textureLocation), textureObj);
    }

    public ITextureObject getTexture(ResourceLocation textureLocation) {
        return this.delegate.getTexture(this.locationTransformer.apply(textureLocation));
    }

    public ResourceLocation getDynamicTextureLocation(String name, DynamicTexture texture) {
        return this.delegate.getDynamicTextureLocation(name, texture);
    }

    public void tick() {
        this.delegate.tick();
    }

    public void deleteTexture(ResourceLocation textureLocation) {
        this.delegate.deleteTexture(this.locationTransformer.apply(textureLocation));
    }

    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.delegate.onResourceManagerReload(resourceManager);
    }
}
