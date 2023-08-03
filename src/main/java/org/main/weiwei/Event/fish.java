package org.main.weiwei.Event;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.main.weiwei.Hu_GET_EnchantedBook;
import org.main.weiwei.command.config_message;
import org.main.weiwei.command.itemStack_item;

public class fish implements Listener {
    private Hu_GET_EnchantedBook plugin;
    private config_message configMessage;

    public fish(Hu_GET_EnchantedBook plugin) {
        this.plugin = plugin;
        this.configMessage = new config_message(plugin);
    }

    @EventHandler
    public void rod_fish(PlayerFishEvent event) {
        Location xyz = event.getHook().getLocation();
        Player player = event.getPlayer();

        if (Hu_GET_EnchantedBook.Enabled) { //開關
            if (!player.getGameMode().equals(GameMode.CREATIVE)) { //防止創造
                if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
                    if (A_case_Chance.shouldDropItem_tofish(player)) { //倍率
                        itemStack_item itemStackItem = new itemStack_item(plugin, configMessage.getdef_drop("def_drop"));
                        ItemStack item = itemStackItem.createItemStack();
                        xyz.getWorld().dropItem(xyz, item);

                    }
                }
            }
        }
    }
}
