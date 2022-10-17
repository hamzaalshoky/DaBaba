package net.dylanvhs.dababa.events;

import net.dylanvhs.dababa.entity.ModEntityTypes;
import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.dylanvhs.dababa.entity.custom.PampamEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.DA_BABA.get(), DaBabaEntity.setAttributes());
        event.put(ModEntityTypes.PAMPAM.get(), PampamEntity.setAttributes());
    }
}
