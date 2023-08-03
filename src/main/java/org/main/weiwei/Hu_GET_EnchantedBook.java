package org.main.weiwei;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.main.weiwei.Event.block;
import org.main.weiwei.Event.fish;
import org.main.weiwei.Event.kill;
import org.main.weiwei.command.command_all;
import org.main.weiwei.command.config_message;

import java.io.File;

public final class Hu_GET_EnchantedBook extends JavaPlugin {

    private FileConfiguration itemConfig;
    private File itemConfigFile;

    public static double magnification_block, magnification_fish, magnification_kill;
    public static boolean Enabled;

    @Override
    public void onEnable() {
        // 加载配置文件
        loadItemConfig();

        getConfig().options().copyDefaults(true);
        saveConfig();

        //註冊事件
        block blockListener = new block(this);
        getServer().getPluginManager().registerEvents(blockListener, this);
        kill killListener = new kill(this);
        getServer().getPluginManager().registerEvents(killListener,this);
        fish fishListener = new fish(this);
        getServer().getPluginManager().registerEvents(fishListener,this);


        getLogger().info("Plugin is now enabled.");

        this.getCommand("getenbook").setExecutor(new command_all(this));

        magnification_block = getConfig().getDouble("magnification_block");
        magnification_fish = getConfig().getDouble("magnification_fish");
        magnification_kill = getConfig().getDouble("magnification_kill");

        Enabled = getConfig().getBoolean("Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadItemConfig() {
        if (itemConfigFile == null) {
            itemConfigFile = new File(getDataFolder(), "Item.yml");
        }
        itemConfig = YamlConfiguration.loadConfiguration(itemConfigFile);

        // 检查插件资源中是否存在默认的 Item.yml 文件，如果不存在，则将默认的 Item.yml 文件保存到插件目录中
    }

    public FileConfiguration getItemConfig() {
        if (itemConfig == null) {
            reloadItemConfig();
        }
        return itemConfig;
    }

    private void loadItemConfig() {
        itemConfigFile = new File(getDataFolder(), "Item.yml");
        if (!itemConfigFile.exists()) {
            saveResource("Item.yml", false);
        }
        itemConfig = YamlConfiguration.loadConfiguration(itemConfigFile);
    }
    //只換
    public String translateColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}