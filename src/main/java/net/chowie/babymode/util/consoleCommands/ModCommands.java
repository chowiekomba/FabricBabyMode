package net.chowie.babymode.util.consoleCommands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.chowie.babymode.util.consoleCommands.config.SpawnSignsState;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModCommands {
    private static SpawnSignsState state;

    private static int spawnSignsTrue(CommandContext<ServerCommandSource> context) {
        state.spawnSigns = true;
        state.markDirty();
        context.getSource().getServer().getPlayerManager()
                .broadcast(Text.literal("Signs Will Now Spawn!"), false);
        return 0;
    }

    private static int spawnSignsFalse(CommandContext<ServerCommandSource> context) {
        state.spawnSigns = false;
        state.markDirty();
        context.getSource().getServer().getPlayerManager()
                .broadcast(Text.literal("Signs Will No Longer Spawn!"), false);
        return 0;
    }

    private static int checkSpawnSigns(CommandContext<ServerCommandSource> context) {
        context.getSource().getServer().getPlayerManager()
                .broadcast(Text.literal("Sign Spawnning Is Currently Set To " +
                        SpawnSignsState.spawnSigns), false);
        return 0;
    }

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            state = SpawnSignsState.getServerState(server);
        });

        CommandRegistrationCallback.EVENT.register(((commandDispatcher,
                                                     commandRegistryAccess,
                                                     registrationEnvironment) -> {
            LiteralCommandNode<ServerCommandSource> register = commandDispatcher.register(CommandManager.literal("bbm")
                    .then(CommandManager.literal("doSignSpawning").executes(ModCommands::checkSpawnSigns)
                            .then(CommandManager.literal("true").executes(ModCommands::spawnSignsTrue))
                            .then(CommandManager.literal("false").executes(ModCommands::spawnSignsFalse))));
        }));
    }
}