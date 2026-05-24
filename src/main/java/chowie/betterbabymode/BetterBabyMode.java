package chowie.betterbabymode;

import chowie.betterbabymode.util.LogTimer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterBabyMode implements ModInitializer {
	public static final String MOD_ID = "better-baby-mode";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering " + MOD_ID);

		LogTimer.register();
	}
}