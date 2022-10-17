package net.dylanvhs.dababa.item;

import net.dylanvhs.dababa.DaBabaMod;
import net.dylanvhs.dababa.block.ModBlocks;
import net.dylanvhs.dababa.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DaBabaMod.MOD_ID);

    public static final RegistryObject<ForgeSpawnEggItem> DABABA_SPAWN_EGG = ITEMS.register("dababa_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DA_BABA,8865820, 4337969,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<ForgeSpawnEggItem> PAMPAM_SPAWN_EGG = ITEMS.register("pampam_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.PAMPAM,12998958, 3874330,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_BUSH.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BLUEBERRY)));

    public static final RegistryObject<Item> BLUEBERRY_PIE = ITEMS.register("blueberry_pie",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BLUEBERRY_PIE)));

    public static final RegistryObject<Item> FLOWER_CROWN = ITEMS.register("flower_crown",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
