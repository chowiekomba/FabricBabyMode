package chowie.babymode.command;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

// I did swaddle commands cause these commands are supposed to make you feel like a baby in a swaddle
public class swaddleCommands {
    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                BlockPos playerPosition = player.getBlockPos();
                World world = player.getWorld();
                // used ai to find the syntax for getting player position
                if (world.getBlockState(playerPosition).isOf(Blocks.WATER)) {
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.WATER_BREATHING,
                            // duration
                            200,
                            // amplifier
                            255,
                            // idk what this does but its here
                            false,
                            // is visible
                            false
                    ));
                }
                // if a solid block is in your head (~ ~1 ~) destroy it. (should prevent you from suffocating)
                if (!world.getBlockState(playerPosition.up()).isOf(Blocks.WATER) && !world.getBlockState(playerPosition.up()).isOf(Blocks.AIR) &&
                !world.getBlockState(playerPosition.up()).isOf(Blocks.CAVE_AIR) && !world.getBlockState(playerPosition.up()).isOf(Blocks.VOID_AIR)) {
                    String setblock = String.format("setblock %d %d %d air destroy",
                            playerPosition.getX(), playerPosition.getY() + 1, playerPosition.getZ());

                    // player takes 1 tick of damage before the block gets broken so I'll regen them.
                    server.getCommandManager().executeWithPrefix(server.getCommandSource(), setblock);
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.REGENERATION,
                            10,
                            255,
                            false,
                            false
                    ));
                }
                // used ai to explain the ray cast method.
                // THIS IS WHERE THE MAX DISTANCE IS. (keep forgetting where it is)
                HitResult looking = player.raycast(1, 0, false);

                // checks if what the player is looking at is a block or not
                if (looking.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult block = (BlockHitResult) looking;
                    // position of the block
                    BlockPos blockPosition = block.getBlockPos();
                    // gets the name of block
                    Block targetBlock = world.getBlockState(blockPosition).getBlock();
                    // types of trees
                    Block[] treeTypes = {Blocks.OAK_LOG, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG, Blocks.CHERRY_LOG,
                    Blocks.JUNGLE_LOG, Blocks.MANGROVE_LOG};

                    // checks if player is looking at a log/tree.
                    if (Arrays.stream(treeTypes).anyMatch(tree -> tree == targetBlock)) {
                        String setblock = String.format("setblock %d %d %d air destroy",
                                blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
                        // I have to use server.getCommandSource so I don't have to assign the command to a player.
                        server.getCommandManager().executeWithPrefix(server.getCommandSource(), setblock);
                    }
                }


                if (player.getHealth() < player.getMaxHealth()) {
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.SATURATION,
                            10,
                            255,
                            false,
                            false
                    ));
                }
                // used ai to describe how to use the world.getOtherEntities
                Box boundary = player.getBoundingBox().expand(5.0);
                List<Entity> entities = world.getOtherEntities(player, boundary);
                for (Entity entity : entities) {
                    if (entity instanceof HostileEntity) {
                        String killentity = String.format("execute as @p at @s run kill @e[type=%s,distance=..5]",
                                entity.getType().toString().replace("entity.minecraft.", ""));
                        server.getCommandManager().executeWithPrefix(server.getCommandSource(), killentity);
                    }
                }
            }
        });
    }
}

