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
                World world = player.getWorld();
                if (world.getRegistryKey() == World.OVERWORLD) {
                    BlockPos playerPosition = player.getBlockPos();
                    // used ai to find the syntax for getting player
                    // prevents player from drowning
                    if (world.getBlockState(playerPosition).isOf(Blocks.WATER)) {
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.WATER_BREATHING,
                                // duration
                                400,
                                // amplifier
                                255,
                                // potion effect particles NOT visible?
                                true,
                                // is visible
                                false
                        ));
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.NIGHT_VISION,
                                // duration
                                400,
                                // amplifier
                                255,
                                // potion effect particles NOT visible?
                                true,
                                // is visible
                                false
                        ));
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.DOLPHINS_GRACE,
                                // duration
                                400,
                                // amplifier
                                2,
                                // potion effect particles NOT visible?
                                true,
                                // is visible
                                false
                        ));
                    }

                    // prevents player from dying from lava/fire
                    if (player.isOnFire()) {
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.FIRE_RESISTANCE,
                                // duration
                                20,
                                // amplifier
                                255,
                                // potion effect particles NOT visible?
                                true,
                                // is visible
                                false
                        ));
                    }


                    String[] positiveEffects = new String[]{
                            "effect.minecraft.water_breathing",
                            "effect.minecraft.absorption",
                            "effect.minecraft.conduit_power",
                            "effect.minecraft.dolphins_grace",
                            "effect.minecraft.fire_resistance",
                            "effect.minecraft.haste",
                            "effect.minecraft.health_boost",
                            "effect.minecraft.hero_of_the_village",
                            "effect.minecraft.instant_health",
                            "effect.minecraft.invisibility",
                            "effect.minecraft.jump_boost",
                            "effect.minecraft.luck",
                            "effect.minecraft.night_vision",
                            "effect.minecraft.regeneration",
                            "effect.minecraft.resistance",
                            "effect.minecraft.saturation",
                            "effect.minecraft.speed",
                            "effect.minecraft.strength",
                            "effect.minecraft.water_breathing",
                            "effect.minecraft.weaving"};


                    java.util.Collection<StatusEffectInstance> Effects = player.getStatusEffects();

                    for (StatusEffectInstance effect : Effects) {
                        // used ai to figure out this print statement. The one I was using before, was printing StatusEffect@9a07409 (while I was debugging it)
                        if (List.of(positiveEffects).contains(effect.getEffectType().value().getTranslationKey())) {
                            player.setStatusEffect(new StatusEffectInstance(
                                    effect.getEffectType(),
                                    Integer.MAX_VALUE,
                                    effect.getAmplifier(),
                                    true,
                                    false
                            ), player);
                        } else if (player.hasStatusEffect(effect.getEffectType())) {
                            player.setStatusEffect(new StatusEffectInstance(
                                    effect.getEffectType(),
                                    0,
                                    effect.getAmplifier(),
                                    true,
                                    false
                            ), player);
                        }

                    }

                    // if a solid block is in your head (~ ~1 ~) destroy it. (should prevent you from suffocating)
                    if (!world.getBlockState(playerPosition.up()).isOf(Blocks.WATER) && !world.getBlockState(playerPosition.up()).isOf(Blocks.AIR) &&
                            !world.getBlockState(playerPosition.up()).isOf(Blocks.CAVE_AIR) && !world.getBlockState(playerPosition.up()).isOf(Blocks.VOID_AIR)
                            && !world.getBlockState(playerPosition.up()).isOf(Blocks.TALL_GRASS) && !world.getBlockState(playerPosition.up()).isOf(Blocks.NETHER_PORTAL)) {
                        String setblock = String.format("setblock %d %d %d air destroy",
                                playerPosition.getX(), playerPosition.getY() + 1, playerPosition.getZ());

                        // player takes 1 tick of damage before the block gets broken so I'll regen them.
                        server.getCommandManager().executeWithPrefix(server.getCommandSource(), setblock);
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.REGENERATION,
                                10,
                                255,
                                true,
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
                                Blocks.JUNGLE_LOG, Blocks.MANGROVE_LOG, Blocks.SPRUCE_LOG};

                        // checks if player is looking at a log/tree.
                        if (Arrays.stream(treeTypes).anyMatch(tree -> tree == targetBlock)) {
                            String setblock = String.format("setblock %d %d %d air destroy",
                                    blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
                            // I have to use server.getCommandSource so I don't have to assign the command to a player.
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), setblock);
                        }
                    }

                    // disables fall damage. I tested the number, (down to 2 decimals) and 0.66 is the lowest it can go.
                    // if the number is negative, that's measuring the player going down. If its positive, that's measuring the player going up.
                    if (player.getVelocity().y < -0.66) {
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.SLOW_FALLING,
                                20,
                                255,
                                true,
                                false
                        ));
                    }

                    // regenerates hunger if you aren't at max health
                    // prevents player from dying from hunger
                    if (player.getHealth() < player.getMaxHealth()) {
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.SATURATION,
                                20,
                                255,
                                true,
                                false
                        ));
                    }


                    // used ai to describe how to use the world.getOtherEntities
                    // creates a 5x5 box around the player
                    Box boundary = player.getBoundingBox().expand(5.0);
                    // makes a list of every mob in the box
                    List<Entity> entities = world.getOtherEntities(player, boundary);
                    // prevents hostile mobs from hurting the player (except skeletons which might shoot the player)
                    for (Entity entity : entities) {


                        String mobType = entity.getType().toString().replace("entity.minecraft.", "");


                        if (mobType.equals("iron_golem")) {
                            BlockPos golemPos = entity.getBlockPos();
                            // makes it so number 1, its a string, and number 2, it is valid minecraft command syntax. It has this syntax by default entity.minecraft.minecraft:mob_here
                            // we need it to be just minecraft:entity_here
                            String golemType = entity.getType().toString().replace("entity.minecraft.", "");
                            // separate the position of the entity into X, Y, and Z
                            double golem_x = entity.getX();
                            double golem_y = entity.getY();
                            double golem_z = entity.getZ();

                            // makes the entity have no ai.
                            // It's important that this is here or else the entity will still be moving when the signs are being spawned making there be multiple signs.
                            String noAiGolem = String.format("execute positioned %f %f %f run data merge entity @e[type=%s,sort=nearest,limit=1,distance=..1] {NoAI:1b}",
                                    golem_x, golem_y, golem_z, golemType);
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), noAiGolem);

                            // adds a chest
                            String chestGolem = String.format("setblock %d %d %d minecraft:oak_sign{front_text:{messages:['{\"text\":\"I am god\"}','{\"text\":\"I will be back\"}','{\"text\":\"you will die\"}','{\"text\":\"\"}']}}",
                                    golemPos.getX(), golemPos.getY(), golemPos.getZ());
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), chestGolem);

                            // kills the hostile mob
                            String killGolem = String.format("execute as @p as @s positioned %f %f %f run kill @e[type=%s,sort=nearest,limit=1,distance=..1]",
                                    golem_x, golem_y, golem_z, golemType);
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), killGolem);
                        }


                        // if its hostile,
                        else if (entity instanceof HostileEntity) {
                            // get its position
                            BlockPos hostileEntityPos = entity.getBlockPos();

                            // makes it so number 1, its a string, and number 2, it is valid minecraft command syntax. It has this syntax by default entity.minecraft.minecraft:mob_here
                            // we need it to be just minecraft:entity_here
                            String entityType = entity.getType().toString().replace("entity.minecraft.", "");
                            // separate the position of the entity into X, Y, and Z
                            double x = entity.getX();
                            double y = entity.getY();
                            double z = entity.getZ();

                            // makes the entity have no ai.
                            // It's important that this is here or else the entity will still be moving when the signs are being spawned making there be multiple signs.
                            String noAiEntity = String.format("execute positioned %f %f %f run data merge entity @e[type=%s,sort=nearest,limit=1,distance=..1] {NoAI:1b}",
                                    x, y, z, entityType);
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), noAiEntity);

                            // adds a sign to make it more funny
                            String signEntity = String.format("setblock %d %d %d minecraft:oak_sign{front_text:{messages:['{\"text\":\"%s died to: \"}','{\"text\":\"your existence\"}','{\"text\":\"\"}','{\"text\":\"\"}']}}",
                                    hostileEntityPos.getX(), hostileEntityPos.getY(), hostileEntityPos.getZ(),
                                    entityType);
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), signEntity);

                            // kills the hostile mob
                            String killEntity = String.format("execute as @p as @s positioned %f %f %f run kill @e[type=%s,sort=nearest,limit=1,distance=..1]",
                                    x, y, z, entityType);
                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), killEntity);

                        }
                    }
                }
            }
        });
    }
}

