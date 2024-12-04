package dev.trigam.template.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class TemplateConfig extends MidnightConfig {

    // Categories
    public static final String EXAMPLE = "Example";

    // Example
    @Comment(category = EXAMPLE) public static Comment example_comment;

}
