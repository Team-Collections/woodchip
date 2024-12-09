package dev.trigam.woodchip.item;

import dev.trigam.woodchip.config.WoodchipConfig;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

public class BarkItem extends Item {

    Map<Block, Block> converts;

    public BarkItem ( Item.Settings settings, Map<Block, Block> converts ) {
        super( settings );
        this.converts = converts;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        if ( !WoodchipConfig.canRebark ) return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        ItemStack itemStack = context.getStack();
        PlayerEntity player = context.getPlayer();
        Random random = world.getRandom();

        Block convertsTo = this.converts.get( blockState.getBlock() );
        if ( convertsTo != null ) {
            // Use Item
            if ( player instanceof ServerPlayerEntity ) {
                Criteria.ITEM_USED_ON_BLOCK.trigger(
                    (ServerPlayerEntity) player, blockPos, itemStack
                );
            }

            BlockState replaceWith = convertsTo.getDefaultState();
            if (convertsTo instanceof PillarBlock) {
                replaceWith = convertsTo.getDefaultState()
                    .with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS));
            }

            itemStack.decrement(1);
            world.setBlockState( blockPos, replaceWith, 11 );
            world.emitGameEvent( GameEvent.BLOCK_CHANGE, blockPos,
                GameEvent.Emitter.of( player, replaceWith )
            );
            world.playSoundAtBlockCenter(
                blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS,
                1F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, true
            );
            return ActionResult.SUCCESS;
        } else return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }

}
