package chowie.babymode;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Babymode implements ModInitializer {
	public static final String MOD_ID = "babymode";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing baby mode");
	}
}

// json files don't add comments so this is where they'll be:

/* in wooden_pickaxe.json it gave me the
"levels": {
          "minecraft:efficiency": 10,
          "minecraft:unbreaking": 10
as I didn't know what the syntax was for enchants


 */