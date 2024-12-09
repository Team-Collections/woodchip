package dev.trigam.woodchip.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class WoodchipConfig extends MidnightConfig {

    // Categories
    public static final String BARK = "Bark";

    // Example
    @Entry( category = BARK, name = "woodchip.midnightconfig.bark_drops" ) public static boolean barkDrops = true;
    @Entry( category = BARK, name = "woodchip.midnightconfig.can_rebark" ) public static boolean canRebark = true;

}
