package net.dylanvhs.dababa.entity.client;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.custom.PampamEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PampamModel extends AnimatedGeoModel<PampamEntity> {
    @Override
    public ResourceLocation getModelLocation(PampamEntity object) {
        return new ResourceLocation(DaBabaMod.MOD_ID, "geo/pampam.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PampamEntity object) {
        return new ResourceLocation(DaBabaMod.MOD_ID, "textures/entity/pampam/pampam.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PampamEntity animatable) {
        return new ResourceLocation(DaBabaMod.MOD_ID, "animations/pampam.animation.json");
    }
}
