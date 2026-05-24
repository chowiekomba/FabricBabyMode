package chowie.betterbabymode.util;

import chowie.betterbabymode.BetterBabyMode;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;

public class LogTimer implements ServerTickEvents.EndTick {
    public static LogTimer INSTANCE = new LogTimer();
    public final Map<ServerPlayer, BlockPosInt> playerMap = new HashMap<>();

    public void setTimer(ServerPlayer player, BlockPosInt block) {
        playerMap.put(player, block);
    }

    @Override
    public void onEndTick(@NonNull MinecraftServer server) {
        for (ServerPlayer player : playerMap.keySet()) {
            playerMap.getOrDefault(player, null).decrementTime();
            if (playerMap.put(player, playerMap.getOrDefault(player, null)) instanceof BlockPosInt block) {

                HitResult result = player.pick(4.5, player.level().getServer().getTickCount(), false);
                if (result instanceof BlockHitResult blockResult) {
                    if (!player.level().getBlockState(blockResult.getBlockPos()).is(BlockTags.LOGS)
                            || !player.level().getBlockState(block.getBlockPos()).is(BlockTags.LOGS)) {
                        playerMap.remove(player);
                    }
                } else {
                    playerMap.remove(player);
                }

                if (block.getTime() == 0) {
                    player.level().destroyBlock(block.getBlockPos(), true);
                    playerMap.remove(player);
                }
            }
        }
    }

    public static void register() {
        BetterBabyMode.LOGGER.info("Registering LogTimer for " + BetterBabyMode.MOD_ID);
        ServerTickEvents.END_SERVER_TICK.register(INSTANCE);
    }
}
