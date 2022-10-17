package net.dylanvhs.dababa.world.gen;

import net.dylanvhs.dababa.entity.ModEntityTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class ModEntityGeneration {
    //da baba spawn
    public static void onEntitySpawn(final BiomeLoadingEvent event){
        if(doesBiomeMatch(event.getName(), Biomes.FOREST)){
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.DA_BABA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.FLOWER_FOREST)){
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.DA_BABA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DARK_FOREST)){
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.DA_BABA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BAMBOO_JUNGLE)){
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.PAMPAM.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.JUNGLE)){
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.PAMPAM.get(), 6, 1, 3));
        }
    }

    public static boolean doesBiomeMatch(ResourceLocation biomeNameIn, ResourceKey<Biome> biomeIn) {
        return biomeNameIn.getPath().matches(biomeIn.location().getPath());
    }
}

