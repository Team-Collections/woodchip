package dev.trigam.woodchip.item;

import dev.trigam.woodchip.Woodchip;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.Map;
import java.util.function.Function;

public class ItemInit {

    // Bark
    public static final Item OAK_BARK = registerItem( "oak_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG,
            Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD
        )),
        new Item.Settings()
    );
    public static final Item BIRCH_BARK = registerItem( "birch_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG,
            Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD
        )),
        new Item.Settings()
    );
    public static final Item SPRUCE_BARK = registerItem( "spruce_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG,
            Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD
        )),
        new Item.Settings()
    );
    public static final Item JUNGLE_BARK = registerItem( "jungle_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG,
            Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD
        )),
        new Item.Settings()
    );
    public static final Item ACACIA_BARK = registerItem( "acacia_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG,
            Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD
        )),
        new Item.Settings()
    );
    public static final Item DARK_OAK_BARK = registerItem( "dark_oak_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG,
            Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD
        )),
        new Item.Settings()
    );
    public static final Item MANGROVE_BARK = registerItem( "mangrove_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG,
            Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD
        )),
        new Item.Settings()
    );
    public static final Item CHERRY_BARK = registerItem( "cherry_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_LOG,
            Blocks.STRIPPED_CHERRY_WOOD, Blocks.CHERRY_WOOD
        )),
        new Item.Settings()
    );
    public static final Item PALE_OAK_BARK = registerItem( "pale_oak_bark",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_PALE_OAK_LOG, Blocks.PALE_OAK_LOG,
            Blocks.STRIPPED_PALE_OAK_WOOD, Blocks.PALE_OAK_WOOD
        )),
        new Item.Settings()
    );
    public static final Item BAMBOO_SHEATH = registerItem( "bamboo_sheath",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK
        )),
        new Item.Settings()
    );
    public static final Item CRIMSON_RETICULUM = registerItem( "crimson_reticulum",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM,
            Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE
        )),
        new Item.Settings()
    );
    public static final Item WARPED_RETICULUM = registerItem( "warped_reticulum",
        settings -> new BarkItem( settings, Map.of(
            Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM,
            Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE
        )),
        new Item.Settings()
    );

    public static Item registerItem ( String name, Function<Item.Settings, Item> constructor, Item.Settings settings ) {
        RegistryKey<Item> key = RegistryKey.of( RegistryKeys.ITEM, Woodchip.id(name) );
        return Registry.register(
            Registries.ITEM, key,
            constructor.apply( settings.useItemPrefixedTranslationKey().registryKey( key ) )
        );
    }

    public static void register () {

    }

}
