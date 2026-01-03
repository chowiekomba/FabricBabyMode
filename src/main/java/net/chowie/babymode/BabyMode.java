package net.chowie.babymode;

import net.chowie.babymode.command.EndSwaddleCommands;
import net.chowie.babymode.command.NetherSwaddleCommands;
import net.chowie.babymode.command.swaddleCommands;
import net.chowie.babymode.command.onLoadCommands;
import net.chowie.babymode.item.ModItems;
import net.chowie.babymode.util.consoleCommands.ModCommands;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BabyMode implements ModInitializer {
	public static final String MOD_ID = "babymode";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// runs every tick
		swaddleCommands.register();
		NetherSwaddleCommands.register();
		EndSwaddleCommands.register();
		// only runs on server start
		onLoadCommands.register();
		ModItems.registerModItems();
		ModCommands.register();

		LOGGER.info("Initializing baby mode");
	}
}

// json files don't add comments so this is where they'll be:

/* in wooden_pickaxe.json ai gave me the
"levels": {
          "minecraft:efficiency": 10,
          "minecraft:unbreaking": 10
as I didn't know what the syntax was for enchants

I also made the first couple of ores, but it got tiring so I got ai to make the rest in stone.json

 */