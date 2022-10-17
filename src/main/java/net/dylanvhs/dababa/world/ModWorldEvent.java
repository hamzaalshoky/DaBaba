package net.dylanvhs.dababa.world;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.world.gen.ModEntityGeneration;
import net.dylanvhs.dababa.world.gen.ModFlowerGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DaBabaMod.MOD_ID)
public class ModWorldEvent {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModFlowerGeneration.generateFlowers(event);
        ModEntityGeneration.onEntitySpawn(event);
    }
}
