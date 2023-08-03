package org.main.weiwei.command;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.main.weiwei.Hu_GET_EnchantedBook;

import java.util.List;
import java.util.Locale;

public class itemStack_item {

    private final Hu_GET_EnchantedBook plugin;
    private final String itemName;

    public itemStack_item(Hu_GET_EnchantedBook plugin, String itemName) {
        this.plugin = plugin;
        this.itemName = itemName;
    }

    public ItemStack createItemStack() {
        ConfigurationSection itemConfig = plugin.getItemConfig().getConfigurationSection(itemName);
        if (itemConfig == null) {
            return null; // 如果在配置中找不到物品名稱，返回null
        }

        String displayName = itemConfig.getString("Name");
        int model = itemConfig.getInt("Model");
        String ID = itemConfig.getString("ID"); //材料+轉大寫
        String material = ID.toUpperCase(Locale.ROOT);
        List<String> lore = itemConfig.getStringList("Lore");

        // 替換訊息中的十六進位色碼
        displayName = ChatColor.translateAlternateColorCodes('&', displayName);
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }

        ItemStack itemStack = new ItemStack(Material.valueOf(material));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setCustomModelData(model);
        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);

        // 在物品上添加NBT標籤
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("hu_ItemName", itemName);
        itemStack = nbtItem.getItem();

        return itemStack;
    }
}
