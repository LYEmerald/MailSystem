package net.IchigyouRuri.Mail;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import net.IchigyouRuri.Mail.Utils.load.*;
import net.IchigyouRuri.Mail.mailsN.listener.MailListener;
import net.IchigyouRuri.Mail.mailsN.math.MailWindowMath;

public class Main extends PluginBase {
    private static Main plugin;
    public static Main getPlugin(){return plugin;}

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.GREEN + "MailSystem Disable");


        this.getLogger().info(TextFormat.GREEN+"线程池关闭...");
    }
    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().info(TextFormat.GREEN + "MailSystem Loading");
        //加载监听器
        this.getServer().getPluginManager().registerEvents(new net.IchigyouRuri.Mail.mailsN.listener.PlayerListener(),this);
        this.getServer().getPluginManager().registerEvents(new MailListener(),this);
        this.getLogger().info(TextFormat.GREEN+"监听器注册完毕!");

        //加载配置项
        LoadCfg.loadCfg();
        LoadLang.loadLang();
        LoadData.loadData();
        LoadMails.loadMails();
        LoadAuction.loadAuctionData();

        //启动线程
        this.getLogger().info(TextFormat.GREEN+"检查线程已启动!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()){
            case "mail":
                ((Player)sender).showFormWindow(MailWindowMath.getMenu((Player)sender));
                return true;
            case "maila":
                ((Player)sender).showFormWindow(MailWindowMath.getGlobalSendMailWindow((Player)sender));
                return true;
        }
        return false;
    }

}
