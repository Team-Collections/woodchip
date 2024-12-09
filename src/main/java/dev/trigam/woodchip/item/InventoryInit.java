package dev.trigam.woodchip.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class InventoryInit {

    public static void sort () {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.INGREDIENTS ).register( content -> {
            content.addBefore( Items.COAL, ItemInit.OAK_BARK, ItemInit.SPRUCE_BARK, ItemInit.BIRCH_BARK,
                ItemInit.JUNGLE_BARK, ItemInit.ACACIA_BARK, ItemInit.DARK_OAK_BARK, ItemInit.MANGROVE_BARK,
                ItemInit.CHERRY_BARK, ItemInit.PALE_OAK_BARK, ItemInit.BAMBOO_SHEATH,
                ItemInit.CRIMSON_RETICULUM, ItemInit.WARPED_RETICULUM
            );
        });
    }

}
