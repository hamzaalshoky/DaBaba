package net.dylanvhs.dababa.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.dylanvhs.dababa.entity.variant.DaBabaVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class DaBabaRenderer extends GeoEntityRenderer<DaBabaEntity> {
    public static final Map<DaBabaVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DaBabaVariant.class), (p_114874_) -> {
                p_114874_.put(DaBabaVariant.DEFAULT,
                        new ResourceLocation(DaBabaMod.MOD_ID, "textures/entity/deer/dababa.png"));
                p_114874_.put(DaBabaVariant.SNOW,
                        new ResourceLocation(DaBabaMod.MOD_ID, "textures/entity/deer/dababa_snow.png"));
            });
    
    
    
    public DaBabaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DaBabaModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(DaBabaEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }


    @Override
    public RenderType getRenderType(DaBabaEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
