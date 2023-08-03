package org.main.weiwei.Event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.main.weiwei.Hu_GET_EnchantedBook;
import org.main.weiwei.command.config_message;
import org.main.weiwei.command.itemStack_item;

public class kill implements Listener {

    private Hu_GET_EnchantedBook plugin;
    private config_message configMessage;
    public kill(Hu_GET_EnchantedBook plugin) {
        this.plugin = plugin;
        this.configMessage = new config_message(plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        EntityType ET = entity.getType();
        Location xyz = event.getEntity().getLocation();
        boolean killedByPlayer = isKilledByPlayer(entity);


        if (Hu_GET_EnchantedBook.Enabled) { //開關
            if (killedByPlayer) {
                if (A_case_Chance.shouldDropItem_tokill(ET)) { //倍率
                    itemStack_item itemStackItem = new itemStack_item(plugin, configMessage.getdef_drop("def_drop"));
                    ItemStack item = itemStackItem.createItemStack();
                    xyz.getWorld().dropItem(xyz, item);
                }
            }
        }
    }

    private boolean isKilledByPlayer(Entity entity) { //判定是否為玩家殺死
        EntityDamageEvent damageEvent = entity.getLastDamageCause();

        if (damageEvent != null && damageEvent instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent) damageEvent;
            Entity damager = damageByEntityEvent.getDamager();

            return (damager instanceof Player);
        }

        return false;
    }
}