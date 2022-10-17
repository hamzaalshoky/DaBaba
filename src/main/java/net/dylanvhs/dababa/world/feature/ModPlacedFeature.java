package net.dylanvhs.dababa.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacedFeature {
    public static final Holder<PlacedFeature> BLUEBERRY_BUSH_PLACED = PlacementUtils.register("blueberry_bush_placed",
            ModConfiguredFeature.BLUEBERRY_BUSH_PATCH, RarityFilter.onAverageOnceEvery(16),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
}
