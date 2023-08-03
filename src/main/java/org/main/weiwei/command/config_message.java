package org.main.weiwei.command;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.main.weiwei.Hu_GET_EnchantedBook;

public class config_message {
    private Hu_GET_EnchantedBook plugin; // 替換為您的主類實例

    public config_message(Hu_GET_EnchantedBook plugin) {
        this.plugin = plugin;
    }

    public String getMessage(String messageKey) {
        FileConfiguration config = plugin.getConfig();
        String message = config.getString("message." + messageKey);
        if (message != null) {
            // 將帶有颜色代码的文本转换为可显示的颜色文本
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        return message;
    }
    public String getdef_drop(String def_drop) {
        FileConfiguration config = plugin.getConfig();
        String message = config.getString("def_drop");
        return message;
    }

    // 其他方法和插件代碼...
}