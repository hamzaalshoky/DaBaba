package net.dylanvhs.dababa.entity;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.entity.custom.DaBabaEntity;
import net.dylanvhs.dababa.entity.custom.PampamEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, DaBabaMod.MOD_ID);


    public static final RegistryObject<EntityType<DaBabaEntity>> DA_BABA = ENTITY_TYPES.register("da_baba",
            () -> EntityType.Builder.of(DaBabaEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(DaBabaMod.MOD_ID, "da_baba").toString()));

    public static final RegistryObject<EntityType<PampamEntity>> PAMPAM = ENTITY_TYPES.register("pampam",
            () -> EntityType.Builder.of(PampamEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(DaBabaMod.MOD_ID, "pampam").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
