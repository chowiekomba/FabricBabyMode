package chowie.betterbabymode.datagen;

import chowie.betterbabymode.mixin.ShapedRecipeBuilderMixin;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                HolderLookup.RegistryLookup<Enchantment> enchantLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
                ItemEnchantments.Mutable mutableSwordEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.SHARPNESS), 10);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.FIRE_ASPECT), 10);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.LOOTING), 10);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.KNOCKBACK), 10);
                mutableSwordEnchants.set(enchantLookup.getOrThrow(Enchantments.SWEEPING_EDGE), 10);
                ItemEnchantments swordEnchants = mutableSwordEnchants.toImmutable();

                ItemEnchantments.Mutable mutablePickaxeEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutablePickaxeEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutablePickaxeEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutablePickaxeEnchants.set(enchantLookup.getOrThrow(Enchantments.FORTUNE), 10);
                mutablePickaxeEnchants.set(enchantLookup.getOrThrow(Enchantments.EFFICIENCY), 10);
                ItemEnchantments pickaxeEnchants = mutablePickaxeEnchants.toImmutable();

                ItemEnchantments.Mutable mutableAxeEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableAxeEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableAxeEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableAxeEnchants.set(enchantLookup.getOrThrow(Enchantments.SHARPNESS), 10);
                mutableAxeEnchants.set(enchantLookup.getOrThrow(Enchantments.EFFICIENCY), 10);
                mutableAxeEnchants.set(enchantLookup.getOrThrow(Enchantments.FORTUNE), 10);
                ItemEnchantments axeEnchants = mutableAxeEnchants.toImmutable();

                ItemEnchantments.Mutable mutableShovelEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableShovelEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableShovelEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableShovelEnchants.set(enchantLookup.getOrThrow(Enchantments.EFFICIENCY), 10);
                mutableShovelEnchants.set(enchantLookup.getOrThrow(Enchantments.FORTUNE), 10);
                ItemEnchantments shovelEnchants = mutableShovelEnchants.toImmutable();

                ItemEnchantments.Mutable mutableHoeEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableHoeEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableHoeEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableHoeEnchants.set(enchantLookup.getOrThrow(Enchantments.EFFICIENCY), 10);
                mutableHoeEnchants.set(enchantLookup.getOrThrow(Enchantments.FORTUNE), 10);
                ItemEnchantments hoeEnchants = mutableHoeEnchants.toImmutable();

                ItemEnchantments.Mutable mutableSpearEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.EFFICIENCY), 10);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.LOOTING), 10);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.KNOCKBACK), 10);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.FIRE_ASPECT), 10);
                mutableSpearEnchants.set(enchantLookup.getOrThrow(Enchantments.LUNGE), 10);
                ItemEnchantments spearEnchants = mutableSpearEnchants.toImmutable();

                ItemEnchantments.Mutable mutableHelmetEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.THORNS), 10);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.RESPIRATION), 10);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.AQUA_AFFINITY), 10);
                mutableHelmetEnchants.set(enchantLookup.getOrThrow(Enchantments.PROTECTION), 10);
                ItemEnchantments helmetEnchants = mutableHelmetEnchants.toImmutable();

                ItemEnchantments.Mutable mutableChestplateEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableChestplateEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableChestplateEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableChestplateEnchants.set(enchantLookup.getOrThrow(Enchantments.THORNS), 10);
                mutableChestplateEnchants.set(enchantLookup.getOrThrow(Enchantments.PROTECTION), 10);
                ItemEnchantments chestplateEnchants = mutableChestplateEnchants.toImmutable();

                ItemEnchantments.Mutable mutableLeggingEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableLeggingEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableLeggingEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableLeggingEnchants.set(enchantLookup.getOrThrow(Enchantments.THORNS), 10);
                mutableLeggingEnchants.set(enchantLookup.getOrThrow(Enchantments.PROTECTION), 10);
                mutableLeggingEnchants.set(enchantLookup.getOrThrow(Enchantments.SWIFT_SNEAK), 10);
                ItemEnchantments leggingEnchants = mutableLeggingEnchants.toImmutable();

                ItemEnchantments.Mutable mutableBootEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.THORNS), 10);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.PROTECTION), 10);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.FEATHER_FALLING), 10);
                mutableBootEnchants.set(enchantLookup.getOrThrow(Enchantments.SOUL_SPEED), 10);
                ItemEnchantments bootEnchants = mutableBootEnchants.toImmutable();

                ItemEnchantments.Mutable mutableMaceEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.FIRE_ASPECT), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.WIND_BURST), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.BREACH), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.DENSITY), 10);
                ItemEnchantments maceEnchants = mutableMaceEnchants.toImmutable();

                ItemEnchantments.Mutable mutableFishingRodEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.LUCK_OF_THE_SEA), 10);
                mutableMaceEnchants.set(enchantLookup.getOrThrow(Enchantments.LURE), 10);
                ItemEnchantments fishingRodEnchants = mutableFishingRodEnchants.toImmutable();

                ItemEnchantments.Mutable mutableBowEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.FLAME), 10);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.INFINITY), 10);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.POWER), 10);
                mutableBowEnchants.set(enchantLookup.getOrThrow(Enchantments.PUNCH), 10);
                ItemEnchantments bowEnchants = mutableBowEnchants.toImmutable();

                ItemEnchantments.Mutable mutableGenericEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableGenericEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableGenericEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                ItemEnchantments genericEnchants = mutableGenericEnchants.toImmutable();

                ItemEnchantments.Mutable mutableCrossbowEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
                mutableCrossbowEnchants.set(enchantLookup.getOrThrow(Enchantments.MENDING), 1);
                mutableCrossbowEnchants.set(enchantLookup.getOrThrow(Enchantments.UNBREAKING), 10);
                mutableCrossbowEnchants.set(enchantLookup.getOrThrow(Enchantments.QUICK_CHARGE), 10);
                mutableCrossbowEnchants.set(enchantLookup.getOrThrow(Enchantments.MULTISHOT), 10);
                mutableCrossbowEnchants.set(enchantLookup.getOrThrow(Enchantments.PIERCING), 10);
                ItemEnchantments crossbowEnchants = mutableCrossbowEnchants.toImmutable();

                // sword builders
                ShapedRecipeBuilder woodenSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.WOODEN_SWORD, swordEnchants);
                ShapedRecipeBuilder stoneSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.STONE_SWORD, swordEnchants);
                ShapedRecipeBuilder goldenSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_SWORD, swordEnchants);
                ShapedRecipeBuilder copperSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_SWORD, swordEnchants);
                ShapedRecipeBuilder ironSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_SWORD, swordEnchants);
                ShapedRecipeBuilder diamondSwordBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_SWORD, swordEnchants);

                // spear builders
                ShapedRecipeBuilder woodenSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.WOODEN_SPEAR, spearEnchants);
                ShapedRecipeBuilder stoneSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.STONE_SPEAR, spearEnchants);
                ShapedRecipeBuilder goldenSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_SPEAR, spearEnchants);
                ShapedRecipeBuilder copperSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_SPEAR, spearEnchants);
                ShapedRecipeBuilder ironSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_SPEAR, spearEnchants);
                ShapedRecipeBuilder diamondSpearBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_SPEAR, spearEnchants);

                // pickaxe builders
                ShapedRecipeBuilder woodenPickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.WOODEN_PICKAXE, pickaxeEnchants);
                ShapedRecipeBuilder stonePickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.STONE_PICKAXE, pickaxeEnchants);
                ShapedRecipeBuilder goldenPickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.GOLDEN_PICKAXE, pickaxeEnchants);
                ShapedRecipeBuilder copperPickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.COPPER_PICKAXE, pickaxeEnchants);
                ShapedRecipeBuilder ironPickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.IRON_PICKAXE, pickaxeEnchants);
                ShapedRecipeBuilder diamondPickaxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.DIAMOND_PICKAXE, pickaxeEnchants);

                // axe builders
                ShapedRecipeBuilder woodenAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.WOODEN_AXE, axeEnchants);
                ShapedRecipeBuilder stoneAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.STONE_AXE, axeEnchants);
                ShapedRecipeBuilder goldenAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.GOLDEN_AXE, axeEnchants);
                ShapedRecipeBuilder copperAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.COPPER_AXE, axeEnchants);
                ShapedRecipeBuilder ironAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.IRON_AXE, axeEnchants);
                ShapedRecipeBuilder diamondAxeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.DIAMOND_AXE, axeEnchants);

                // shovel builders
                ShapedRecipeBuilder woodenShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.WOODEN_SHOVEL, shovelEnchants);
                ShapedRecipeBuilder stoneShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.STONE_SHOVEL, shovelEnchants);
                ShapedRecipeBuilder goldenShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.GOLDEN_SHOVEL, shovelEnchants);
                ShapedRecipeBuilder copperShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.COPPER_SHOVEL, shovelEnchants);
                ShapedRecipeBuilder ironShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.IRON_SHOVEL, shovelEnchants);
                ShapedRecipeBuilder diamondShovelBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.DIAMOND_SHOVEL, shovelEnchants);

                // hoe builders
                ShapedRecipeBuilder woodenHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.WOODEN_HOE, hoeEnchants);
                ShapedRecipeBuilder stoneHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.STONE_HOE, hoeEnchants);
                ShapedRecipeBuilder goldenHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.GOLDEN_HOE, hoeEnchants);
                ShapedRecipeBuilder copperHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.COPPER_HOE, hoeEnchants);
                ShapedRecipeBuilder ironHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.IRON_HOE, hoeEnchants);
                ShapedRecipeBuilder diamondHoeBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.DIAMOND_HOE, hoeEnchants);

                // helmet builders
                ShapedRecipeBuilder goldenHelmetBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_HELMET, helmetEnchants);
                ShapedRecipeBuilder copperHelmetBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_HELMET, helmetEnchants);
                ShapedRecipeBuilder ironHelmetBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_HELMET, helmetEnchants);
                ShapedRecipeBuilder diamondHelmetBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_HELMET, helmetEnchants);

                // chestplate builders
                ShapedRecipeBuilder goldenChestplateBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_CHESTPLATE, chestplateEnchants);
                ShapedRecipeBuilder copperChestplateBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_CHESTPLATE, chestplateEnchants);
                ShapedRecipeBuilder ironChestplateBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_CHESTPLATE, chestplateEnchants);
                ShapedRecipeBuilder diamondChestplateBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_CHESTPLATE, chestplateEnchants);

                // legging builders
                ShapedRecipeBuilder goldenLeggingBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_LEGGINGS, leggingEnchants);
                ShapedRecipeBuilder copperLeggingBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_LEGGINGS, leggingEnchants);
                ShapedRecipeBuilder ironLeggingBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_LEGGINGS, leggingEnchants);
                ShapedRecipeBuilder diamondLeggingBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_LEGGINGS, leggingEnchants);

                // boot builders
                ShapedRecipeBuilder goldenBootBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.GOLDEN_BOOTS, bootEnchants);
                ShapedRecipeBuilder copperBootBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.COPPER_BOOTS, bootEnchants);
                ShapedRecipeBuilder ironBootBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.IRON_BOOTS, bootEnchants);
                ShapedRecipeBuilder diamondBootBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.DIAMOND_BOOTS, bootEnchants);

                // misc builders
                ShapedRecipeBuilder maceBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.MACE, maceEnchants);
                ShapedRecipeBuilder fishingRodBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.TOOLS,
                        Items.FISHING_ROD, fishingRodEnchants);
                ShapedRecipeBuilder bowBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.BOW, bowEnchants);
                ShapelessRecipeBuilder flintAndSteelBuilder = ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.TOOLS,
                new ItemStackTemplate(Items.FLINT_AND_STEEL, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS,
                        genericEnchants).build()));
                ShapedRecipeBuilder shieldBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.SHIELD, genericEnchants);
                ShapedRecipeBuilder crossbowBuilder = makeRecipeBuilder(itemLookup, RecipeCategory.COMBAT,
                        Items.CROSSBOW, crossbowEnchants);

                // all sword recipes
                makeSwordRecipe(output, getHasName(Items.STICK), has(Items.STICK), ItemTags.WOODEN_TOOL_MATERIALS,
                        woodenSwordBuilder);
                makeSwordRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenSwordBuilder);
                makeSwordRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperSwordBuilder);
                makeSwordRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stoneSwordBuilder);
                makeSwordRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironSwordBuilder);
                makeSwordRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondSwordBuilder);

                // all spear recipes
                makeSpearRecipe(output, getHasName(Items.STICK), has(Items.STICK), ItemTags.WOODEN_TOOL_MATERIALS,
                        woodenSpearBuilder);
                makeSpearRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenSpearBuilder);
                makeSpearRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperSpearBuilder);
                makeSpearRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stoneSpearBuilder);
                makeSpearRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironSpearBuilder);
                makeSpearRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondSpearBuilder);

                // all pickaxe recipes
                makePickaxeRecipe(output, getHasName(Items.STICK), has(Items.STICK),
                        ItemTags.WOODEN_TOOL_MATERIALS, woodenPickaxeBuilder);
                makePickaxeRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stonePickaxeBuilder);
                makePickaxeRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenPickaxeBuilder);
                makePickaxeRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperPickaxeBuilder);
                makePickaxeRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironPickaxeBuilder);
                makePickaxeRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondPickaxeBuilder);

                // all axe recipes
                makeAxeRecipe(output, getHasName(Items.STICK), has(Items.STICK),
                        ItemTags.WOODEN_TOOL_MATERIALS, woodenAxeBuilder);
                makeAxeRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stoneAxeBuilder);
                makeAxeRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenAxeBuilder);
                makeAxeRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperAxeBuilder);
                makeAxeRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironAxeBuilder);
                makeAxeRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondAxeBuilder);

                // all shovel recipes
                makeShovelRecipe(output, getHasName(Items.STICK), has(Items.STICK),
                        ItemTags.WOODEN_TOOL_MATERIALS, woodenShovelBuilder);
                makeShovelRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stoneShovelBuilder);
                makeShovelRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenShovelBuilder);
                makeShovelRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperShovelBuilder);
                makeShovelRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironShovelBuilder);
                makeShovelRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondShovelBuilder);

                // all hoe recipes
                makeHoeRecipe(output, getHasName(Items.STICK), has(Items.STICK),
                        ItemTags.WOODEN_TOOL_MATERIALS, woodenHoeBuilder);
                makeHoeRecipe(output, getHasName(Items.COBBLESTONE), has(ItemTags.STONE_TOOL_MATERIALS),
                        ItemTags.STONE_TOOL_MATERIALS, stoneHoeBuilder);
                makeHoeRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenHoeBuilder);
                makeHoeRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperHoeBuilder);
                makeHoeRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironHoeBuilder);
                makeHoeRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondHoeBuilder);

                // all helmet recipes
                makeHelmetRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenHelmetBuilder);
                makeHelmetRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperHelmetBuilder);
                makeHelmetRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironHelmetBuilder);
                makeHelmetRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondHelmetBuilder);

                // all chestplate recipes
                makeChestplateRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenChestplateBuilder);
                makeChestplateRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperChestplateBuilder);
                makeChestplateRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironChestplateBuilder);
                makeChestplateRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondChestplateBuilder);

                // all legging recipes
                makeLeggingRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenLeggingBuilder);
                makeLeggingRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperLeggingBuilder);
                makeLeggingRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironLeggingBuilder);
                makeLeggingRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondLeggingBuilder);

                // all boot recipes
                makeBootRecipe(output, getHasName(Items.GOLD_INGOT), has(ItemTags.GOLD_TOOL_MATERIALS),
                        ItemTags.GOLD_TOOL_MATERIALS, goldenBootBuilder);
                makeBootRecipe(output, getHasName(Items.COPPER_INGOT), has(ItemTags.COPPER_TOOL_MATERIALS),
                        ItemTags.COPPER_TOOL_MATERIALS, copperBootBuilder);
                makeBootRecipe(output, getHasName(Items.IRON_INGOT), has(ItemTags.IRON_TOOL_MATERIALS),
                        ItemTags.IRON_TOOL_MATERIALS, ironBootBuilder);
                makeBootRecipe(output, getHasName(Items.DIAMOND), has(ItemTags.DIAMOND_TOOL_MATERIALS),
                        ItemTags.DIAMOND_TOOL_MATERIALS, diamondBootBuilder);

                // misc recipes
                maceBuilder
                        .pattern(" # ")
                        .pattern(" I ")
                        .define('#', Blocks.HEAVY_CORE)
                        .define('I', Items.BREEZE_ROD)
                        .unlockedBy(getHasName(Items.BREEZE_ROD), has(Items.BREEZE_ROD))
                        .unlockedBy(getHasName(Blocks.HEAVY_CORE), has(Blocks.HEAVY_CORE))
                        .save(output);
                fishingRodBuilder
                        .define('#', Items.STICK)
                        .define('X', Items.STRING)
                        .pattern("  #")
                        .pattern(" #X")
                        .pattern("# X")
                        .unlockedBy("has_string", this.has(Items.STRING))
                        .save(output);
                bowBuilder
                        .define('#', Items.STICK)
                        .define('S', Items.STRING)
                        .pattern("S# ")
                        .pattern("S #")
                        .pattern("S# ")
                        .unlockedBy("has_string", this.has(Items.STRING))
                        .save(output);
                shieldBuilder
                        .define('W', ItemTags.WOODEN_TOOL_MATERIALS)
                        .define('o', Items.IRON_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(output);
                flintAndSteelBuilder
                        .requires(Items.IRON_INGOT)
                        .requires(Items.FLINT)
                        .unlockedBy("has_flint", this.has(Items.FLINT))
                        .unlockedBy("has_obsidian", this.has(Blocks.OBSIDIAN))
                        .save(this.output);
                crossbowBuilder
                        .define('~', Items.STRING)
                        .define('#', Items.STICK)
                        .define('&', Items.IRON_INGOT)
                        .define('$', Blocks.TRIPWIRE_HOOK)
                        .pattern("#&#")
                        .pattern("~$~")
                        .pattern(" # ")
                        .unlockedBy("has_string", has(Items.STRING))
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .unlockedBy("has_tripwire_hook", has(Blocks.TRIPWIRE_HOOK))
                        .save(output);
            }
        };
    }

    static ShapedRecipeBuilder makeRecipeBuilder(HolderLookup.RegistryLookup<Item> itemLookup, RecipeCategory category,
                                                 Item item, ItemEnchantments enchants) {
        return ShapedRecipeBuilderMixin.newShapedRecipeBuilder(itemLookup, category,
                new ItemStackTemplate(item, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS, enchants).build()));
    }

    static void makeSwordRecipe(RecipeOutput output, String unlockedByName, Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy,
                                TagKey<Item> blade, ShapedRecipeBuilder builder) {
        builder
                .pattern(" X ")
                .pattern(" X ")
                .pattern(" # ")
                .define('X', blade)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeSpearRecipe(RecipeOutput output, String unlockedByName, Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy,
                                TagKey<Item> blade, ShapedRecipeBuilder builder) {
        builder
                .pattern("  X")
                .pattern(" # ")
                .pattern("#  ")
                .define('X', blade)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makePickaxeRecipe(RecipeOutput output, String unlockedByName,
                                  Criterion<?> unlockedBy, TagKey<Item> material,
                                  ShapedRecipeBuilder builder) {
        builder
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeAxeRecipe(RecipeOutput output, String unlockedByName,
                              Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                              ShapedRecipeBuilder builder) {
        builder
                .pattern(" XX")
                .pattern(" #X")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }


    static void makeShovelRecipe(RecipeOutput output, String unlockedByName,
                              Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                              ShapedRecipeBuilder builder) {
        builder
                .pattern(" X ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeHoeRecipe(RecipeOutput output, String unlockedByName,
                                 Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                                 ShapedRecipeBuilder builder) {
        builder
                .pattern(" XX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', material)
                .define('#', Items.STICK)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeHelmetRecipe(RecipeOutput output, String unlockedByName,
                                 Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                                 ShapedRecipeBuilder builder) {
        builder
                .pattern("XXX")
                .pattern("X X")
                .pattern("   ")
                .define('X', material)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeChestplateRecipe(RecipeOutput output, String unlockedByName,
                                 Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                                 ShapedRecipeBuilder builder) {
        builder
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', material)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeLeggingRecipe(RecipeOutput output, String unlockedByName,
                                     Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                                     ShapedRecipeBuilder builder) {
        builder
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', material)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    static void makeBootRecipe(RecipeOutput output, String unlockedByName,
                                  Criterion<InventoryChangeTrigger.TriggerInstance> unlockedBy, TagKey<Item> material,
                                  ShapedRecipeBuilder builder) {
        builder
                .pattern("XXX")
                .pattern("X X")
                .pattern("   ")
                .define('X', material)
                .unlockedBy(unlockedByName, unlockedBy)
                .save(output);
    }

    @Override
    public @NonNull String getName() {
        return "ModRecipeProvider";
    }

    @Override
    protected @NonNull Identifier getRecipeIdentifier(Identifier identifier) {
        return Identifier.withDefaultNamespace(identifier.getPath());
    }
}
