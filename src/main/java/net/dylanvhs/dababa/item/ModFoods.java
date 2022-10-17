package net.dylanvhs.dababa.item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public class ModFoods {
    public static final FoodProperties BLUEBERRY = new FoodProperties.Builder().nutrition(1).saturationMod(0.1f)
            .build();

    public static final FoodProperties BLUEBERRY_PIE = new FoodProperties.Builder().nutrition(3).saturationMod(0.3f)
             .build();
}