package net.chowie.babymode.item;

import net.chowie.babymode.item.custom.UtilityWand;
import net.chowie.babymode.BabyMode;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item UTILITY_WAND = registerItem("utility_wand", new UtilityWand(new Item.Settings().maxDamage(64)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BabyMode.MOD_ID, name), item);
    }

    public static void registerModItems () {
        BabyMode.LOGGER.info("Registering Mod Items for " + BabyMode.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(UTILITY_WAND);
        });
    }
}
