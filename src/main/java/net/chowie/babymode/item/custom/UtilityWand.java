package net.chowie.babymode.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class UtilityWand extends Item {
    private static final Map<Block, Block> UTILITY_MAP =
            Map.of(
                    Blocks.DIRT, Blocks.OBSIDIAN,
                    Blocks.SANDSTONE, Blocks.TNT,
                    Blocks.RED_SANDSTONE, Blocks.TNT,
                    Blocks.SAND, Blocks.GLASS,
                    Blocks.RED_SAND, Blocks.GLASS,
                    Blocks.BOOKSHELF, Blocks.ENCHANTING_TABLE,
                    Blocks.COBBLESTONE, Blocks.STONE,
                    Blocks.CLAY, Blocks.BRICKS,
                    Blocks.PUMPKIN, Blocks.JACK_O_LANTERN,
                    Blocks.NETHERRACK, Blocks.NETHER_BRICKS
            );
    public UtilityWand(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (UTILITY_MAP.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), UTILITY_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.2"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.3"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.4"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.5"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.6"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.7"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down.8"));
        } else {
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand.shift_down"));
            tooltip.add(Text.translatable("tooltip.babymode.utility_wand"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
