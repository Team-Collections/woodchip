package dev.trigam.woodchip.loot;

import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.context.ContextType;

public class LootContextInit {

    public static final ContextType STRIPPING = LootContextTypes.register("stripping",
        (builder) -> builder
            .require(LootContextParameters.ORIGIN)
            .require(LootContextParameters.TOOL)
            .require(LootContextParameters.THIS_ENTITY)
    );

}
