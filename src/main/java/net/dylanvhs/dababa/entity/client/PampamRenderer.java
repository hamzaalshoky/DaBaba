package net.dylanvhs.dababa.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.dylanvhs.dababa.entity.custom.PampamEntity;
import net.dylanvhs.dababa.entity.variant.DaBabaVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class PampamRenderer extends GeoEntityRenderer<PampamEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("dababa:textures/entity/pampam/pampam.png");
    private static final ResourceLocation CROWNED = new ResourceLocation("dababa:textures/entity/pampam/pampam_crowned.png");

    public PampamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PampamModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(PampamEntity instance) {
        return instance.isCrowned() ? CROWNED : TEXTURE;
    }


    @Override
    public RenderType getRenderType(PampamEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1F, 1F, 1F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
