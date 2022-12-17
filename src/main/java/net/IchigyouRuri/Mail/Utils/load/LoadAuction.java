package net.IchigyouRuri.Mail.Utils.load;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.IchigyouRuri.Mail.Main;
import java.io.File;

/**
 * @author Luckily_Baby
 * @date 2020/5/21 15:17
 */
public class LoadAuction {
    public static Config autionData;
    public static void loadAuctionData(){
        if(!new File(Main.getPlugin().getDataFolder(), "auction.yml").exists()) {
            Main.getPlugin().getLogger().info(TextFormat.BLUE+"未找到auction.yml，正在创建...");
            Main.getPlugin().saveResource("auction.yml");
            autionData =new Config(new File(Main.getPlugin().getDataFolder(), "auction.yml"), Config.YAML);
            Main.getPlugin().getLogger().info(TextFormat.GREEN+"auction.yml创建完成!");
        }else {
            autionData = new Config(new File(Main.getPlugin().getDataFolder(), "auction.yml"), Config.YAML);
            Main.getPlugin().getLogger().info(TextFormat.YELLOW+"auction.yml已找到!");
        }
        Main.getPlugin().getLogger().info(TextFormat.GREEN+"auction.yml加载完成!");
    }
}
