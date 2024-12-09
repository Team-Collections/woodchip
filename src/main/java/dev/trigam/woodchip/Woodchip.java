package dev.trigam.woodchip;

import dev.trigam.woodchip.config.WoodchipConfig;
import dev.trigam.woodchip.item.InventoryInit;
import dev.trigam.woodchip.item.ItemInit;
import dev.trigam.woodchip.loot.LootTableInit;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Woodchip implements ModInitializer {
	public static final String MOD_ID = "woodchip";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id (String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID, WoodchipConfig.class);

		ItemInit.register();
		InventoryInit.sort();

		LootTableInit.register();
	}
}