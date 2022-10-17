package net.dylanvhs.dababa.entity.client;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DaBabaModel extends AnimatedGeoModel<DaBabaEntity> {
    @Override
    public ResourceLocation getModelLocation(DaBabaEntity object) {
        return new ResourceLocation(DaBabaMod.MOD_ID, "geo/dababa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DaBabaEntity object) {
        return DaBabaRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DaBabaEntity animatable) {
        return new ResourceLocation(DaBabaMod.MOD_ID, "animations/dababa.animation.json");
    }
}
