package net.IchigyouRuri.Mail.Utils.load;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.IchigyouRuri.Mail.Main;

import java.io.File;

/**
 * @author Luckily_Baby
 * @date 2020/5/5 16:32
 */
public class LoadData {
    public static Config shopData;
    public static void loadData(){
        if(!new File(Main.getPlugin().getDataFolder(), "shopdara.yml").exists()) {
            Main.getPlugin().getLogger().info(TextFormat.BLUE+"未找到shopdara.yml，正在创建...");
            Main.getPlugin().saveResource("shopdara.yml");
            shopData=new Config(new File(Main.getPlugin().getDataFolder(), "shopdara.yml"), Config.YAML);
            Main.getPlugin().getLogger().info(TextFormat.GREEN+"shopdara.yml创建完成!");
        }else {
            shopData = new Config(new File(Main.getPlugin().getDataFolder(), "shopdara.yml"), Config.YAML);
            Main.getPlugin().getLogger().info(TextFormat.YELLOW+"shopdara.yml已找到!");
        }
        Main.getPlugin().getLogger().info(TextFormat.GREEN+"shopdara.yml加载完成!");
    }
}
