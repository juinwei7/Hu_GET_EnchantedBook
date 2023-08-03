package org.main.weiwei.Event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.main.weiwei.Hu_GET_EnchantedBook;
import org.main.weiwei.command.config_message;
import org.main.weiwei.command.itemStack_item;

import java.util.UUID;


public class block implements Listener {

    private Hu_GET_EnchantedBook plugin;
    private config_message configMessage;
    public block(Hu_GET_EnchantedBook plugin) {
        this.plugin = plugin;
        this.configMessage = new config_message(plugin);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) { //放置
        Material blockType = event.getBlock().getType();
        Location xyz = event.getBlock().getLocation();
        if (!event.isCancelled()) {
            event.getBlock().setMetadata("placedBy", new FixedMetadataValue(plugin, true));
            if (A_case_Chance.shouldDropItem_toPlace()) {
                itemStack_item itemStackItem = new itemStack_item(plugin, configMessage.getdef_drop("def_drop"));
                ItemStack item = itemStackItem.createItemStack();
                xyz.getWorld().dropItem(xyz, item);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) { //破壞
        Material blockType = event.getBlock().getType();
        Player p = event.getPlayer();
        Location xyz = event.getBlock().getLocation();

        if (Hu_GET_EnchantedBook.Enabled) { //開關
            if (p != null) { //必須是玩家破壞
                if (!p.getGameMode().equals(GameMode.CREATIVE)) { //防止創造
                    if (!event.getBlock().hasMetadata("placedBy")) {
                        //if (isDropBlockType(blockType)) { //使用倍率控制
                        if (A_case_Chance.shouldDropItem_toblock(blockType)) {
                            itemStack_item itemStackItem = new itemStack_item(plugin, configMessage.getdef_drop("def_drop"));
                            ItemStack item = itemStackItem.createItemStack();
                            xyz.getWorld().dropItem(xyz, item);
                        }
                        //}
                    }
                }
            }
        }
    }

    private boolean isDropBlockType(Material blockType) { //方塊過濾
        return blockType == Material.DIAMOND_ORE ||
                blockType == Material.GOLD_ORE ||
                blockType == Material.IRON_ORE ||
                blockType == Material.EMERALD_ORE ||
                blockType == Material.REDSTONE_ORE;
    }
}
