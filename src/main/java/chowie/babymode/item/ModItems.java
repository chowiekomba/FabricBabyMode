package chowie.babymode.item;

import chowie.babymode.Babymode;
import chowie.babymode.item.custom.UtilityWand;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item UTILITY_WAND = registerItem("utility_wand", new UtilityWand(new Item.Settings().maxDamage(64)));

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Babymode.MOD_ID, name), item);
    }

    public static void registerModItems () {
        Babymode.LOGGER.info("Registering Mod Items for " + Babymode.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(UTILITY_WAND);
        });
    }
}
