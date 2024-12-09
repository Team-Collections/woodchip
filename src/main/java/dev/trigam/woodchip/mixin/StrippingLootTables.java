package dev.trigam.woodchip.mixin;

import dev.trigam.woodchip.config.WoodchipConfig;
import dev.trigam.woodchip.loot.LootContextInit;
import dev.trigam.woodchip.loot.LootTableInit;
import dev.trigam.woodchip.registry.TagInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(AxeItem.class)
public class StrippingLootTables {

    @Inject(
        method = "useOnBlock",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/AxeItem;tryStrip(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/BlockState;)Ljava/util/Optional;"
        )
    )
    public void generateLootOnStrip ( ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir ) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState state = world.getBlockState( blockPos );
        PlayerEntity player = context.getPlayer();

        if ( state.isIn( TagInit.DROPS_BARK ) && !WoodchipConfig.barkDrops ) return;

        if ( world instanceof ServerWorld serverWorld ) {
            boolean tileDrops = serverWorld.getGameRules().getBoolean( GameRules.DO_TILE_DROPS );
            if ( !tileDrops || ( player != null && player.isCreative() ) ) return;

            List<ItemStack> lootedItems = generateLoot( serverWorld, state, context );
            // Drop loot
            for ( ItemStack item : lootedItems ) {
                Block.dropStack( serverWorld, blockPos, item );
            }
        }
    }

    @Unique
    public List<ItemStack> generateLoot ( ServerWorld world, BlockState state, ItemUsageContext context ) {
        // Get loot table
        Optional<RegistryKey<LootTable>> lootTableKey = LootTableInit.getLootTable(
            Registries.BLOCK.getId( state.getBlock() ),
            "stripping/"
        );
        if ( lootTableKey.isEmpty() ) return List.of();
        LootTable lootTable = world.getServer().getReloadableRegistries().getLootTable( lootTableKey.get() );

        // Build loot context
        LootWorldContext.Builder lootContextBuilder = new LootWorldContext.Builder( world )
            .add( LootContextParameters.ORIGIN, context.getBlockPos().toCenterPos() )
            .add( LootContextParameters.TOOL, context.getStack() )
            .add( LootContextParameters.THIS_ENTITY, context.getPlayer() );
        LootWorldContext lootContext = lootContextBuilder.build( LootContextInit.STRIPPING );

        return lootTable.generateLoot( lootContext );
    }

}
