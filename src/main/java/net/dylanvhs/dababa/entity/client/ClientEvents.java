package net.dylanvhs.dababa.entity.client;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.ModEntityTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DaBabaMod.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.DA_BABA.get(), DaBabaRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.PAMPAM.get(), PampamRenderer::new);
    }
}
