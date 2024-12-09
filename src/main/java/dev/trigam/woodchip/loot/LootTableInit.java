package dev.trigam.woodchip.loot;

import dev.trigam.woodchip.Woodchip;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class LootTableInit {

    public static RegistryKey<LootTable> register ( String path ) {
        RegistryKey<LootTable> key = RegistryKey.of( RegistryKeys.LOOT_TABLE, Woodchip.id( path ) );
        return LootTables.registerLootTable( key );
    }

    public static Optional<RegistryKey<LootTable>> getLootTable ( Identifier id, String path ) {
        return Optional.of( RegistryKey.of(
            RegistryKeys.LOOT_TABLE,
            Woodchip.id( id.getPath() ).withPrefixedPath( path )
        ));
    }

    public static void register () {}

}
