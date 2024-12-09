package dev.trigam.woodchip.registry;

import dev.trigam.woodchip.Woodchip;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class TagInit {

    public static final TagKey<Block> DROPS_BARK = TagKey.of(
        RegistryKeys.BLOCK, Woodchip.id( "drops_bark" )
    );

}
