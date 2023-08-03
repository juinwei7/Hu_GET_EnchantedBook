package org.main.weiwei.Event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.main.weiwei.Hu_GET_EnchantedBook;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Type;
import java.util.Random;


public class A_case_Chance {
    //#####  方塊
    public static boolean shouldDropItem_toblock(Material type) {
        double mat = 0.015;
        if(type == Material.SPAWNER || type == Material.BOOKSHELF){mat=0.8;};
        if(type == Material.ANDESITE || type == Material.DIORITE || type == Material.GRANITE){mat=0.01;}; //閃長岩, 安山岩, 花崗岩
        if(type == Material.DIRT || type == Material.COARSE_DIRT){mat=0.34405;}; //泥土
        if(type == Material.GRASS_BLOCK || type == Material.SAND){mat=0.42621;}; //草地 沙子
        if(type == Material.PACKED_ICE){mat=0.917873;}; //冰磚
        if(type == Material.OAK_LOG || type == Material.SPRUCE_LOG ||
        type == Material.BIRCH_LOG ||type == Material.JUNGLE_LOG || type == Material.ACACIA_LOG){mat=0.841756;}; //原木
        if(type == Material.PRISMARINE || type == Material.MUSHROOM_STEM){mat=0.84995;}; //海磷石磚 蘑菇柄
        if(type == Material.COAL_ORE){mat=0.835940;}; //煤礦
        if(type == Material.IRON_ORE){mat=0.871279;}; //鐵礦
        if(type == Material.REDSTONE_ORE){mat=0.904853;}; //紅石礦
        if(type == Material.GOLD_ORE){mat=0.914658;}; //金礦
        if(type == Material.EMERALD_ORE){mat=0.919930;}; //綠寶石礦
        if(type == Material.DIAMOND_ORE){mat=0.918166;}; //鑽石礦
        if(type == Material.DIAMOND_ORE){mat=0;}; //甘蔗

        int dropChancePercentage = (int) (Hu_GET_EnchantedBook.magnification_block*10000*mat);
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;

        return randomNumber <= dropChancePercentage;
    }
    public static boolean shouldDropItem_toPlace() { //放置方塊*0.08
        int dropChancePercentage = (int) (Hu_GET_EnchantedBook.magnification_kill*1000*0.08);

        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;

        return randomNumber <= dropChancePercentage;
    }
    //#####  釣魚
    public static boolean shouldDropItem_tofish(Player player) { //釣魚

        double mat = 1;
        if (player.hasPermission("autofishing.use")){mat=0.01;};
        int dropChancePercentage = (int) (Hu_GET_EnchantedBook.magnification_fish*10000*mat);
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;
        return randomNumber <= dropChancePercentage;
    }
    //#####  擊殺
    public static boolean shouldDropItem_tokill(EntityType entity) { //擊殺
        double mat = 0.15 ;
        EntityType[] ETLIST = {EntityType.PIG,EntityType.ALLAY,EntityType.BAT,EntityType.SQUID,EntityType.GLOW_SQUID,EntityType.HORSE,EntityType.VILLAGER};
        for (EntityType type : ETLIST) {
            if (entity == type) {
                mat = 0.25;
                break;
            }
        }
        if (entity == EntityType.ENDER_DRAGON){mat=1.0;}; //終界龍
        if (entity == EntityType.WITHER){mat=0.85;}; //凋零王
        if (entity == EntityType.GHAST|| entity == EntityType.WARDEN){mat=0.6;}; //地域幽靈 伏守者
        if (entity == EntityType.WITHER_SKELETON){mat=0.25;}; //凋零骷髏
        if (entity == EntityType.RAVAGER){mat=0.2;}; //劫毀獸
        if (entity == EntityType.VEX){mat=0.25;}; //腦鬼

        int dropChancePercentage = (int) (Hu_GET_EnchantedBook.magnification_kill*10000*mat);
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;

        return randomNumber <= dropChancePercentage;
    }
}
