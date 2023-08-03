package org.main.weiwei.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.main.weiwei.Hu_GET_EnchantedBook;

public class command_all implements CommandExecutor {

    private Hu_GET_EnchantedBook plugin; // 替換為您的主類實例
    private config_message configMessage;
    public command_all(Hu_GET_EnchantedBook plugin) {
        this.plugin = plugin;
        this.configMessage = new config_message(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("缺少参数，请输入正确的指令。");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("非玩家輸入!");
            return true;
        }
        Player player = (Player) sender;

        if (!player.isOp()){
            sender.sendMessage("no_permissions");
            return true;
        }

        //reload 文件
        if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("Hu_GET_EnchantedBook.reload")) {
                player.sendMessage(configMessage.getMessage("no_permissions"));
                return true;
            }

            // 在這裡處理重載配置的邏輯
            plugin.reloadConfig(); // 使用您的主類實例重載配置
            plugin.reloadItemConfig(); //更新item.yml
            player.sendMessage(configMessage.getMessage("reload"));
            return true;
        }
        //獲取物品
        if (args.length == 2 && args[0].equalsIgnoreCase("get")) {
            String itemName = args[1];
            itemStack_item itemStackItem = new itemStack_item(plugin, itemName);
            ItemStack item = itemStackItem.createItemStack();
            if (item != null) {
                player.getInventory().addItem(item);
                player.sendMessage("已获得物品：" + itemName);
            } else {
                player.sendMessage("未找到名称为 " + itemName + " 的物品配置。");
            }
            return true;
        }
        //給予物品
        if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
            String itemName = args[1];
            String PlayerName_String = args[2]; //獲取[2]玩家參數
            Player PlayerName = Bukkit.getPlayerExact(PlayerName_String);//轉換參數

            if (PlayerName != null) {
                itemStack_item itemStackItem = new itemStack_item(plugin, itemName);
                ItemStack item = itemStackItem.createItemStack();
                if (item != null) {
                    PlayerName.getInventory().addItem(item);
                    player.sendMessage("已获得物品：" + itemName);
                } else {
                    player.sendMessage("未找到名称为 " + itemName + " 的物品配置。");
                }
                return true;
            } else {
                player.sendMessage("§c未找到該玩家 或 不存在!");
            }
        }
        return false;
    }
}

