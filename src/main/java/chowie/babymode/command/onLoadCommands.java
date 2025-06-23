package chowie.babymode.command;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
public class onLoadCommands {
    private static boolean ran = false;
    private static final String commandFeedback = "gamerule sendCommandFeedback false";
    private static final String gameruleTrue = "gamerule keepInventory true";

    public static void register() {
        // used ai to look up the syntax for line 11
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            if (!ran) {
                server.getCommandManager().executeWithPrefix(server.getCommandSource(), commandFeedback);
                server.getCommandManager().executeWithPrefix(server.getCommandSource(), gameruleTrue);
                ran = true;
            }
        });
    }
}
