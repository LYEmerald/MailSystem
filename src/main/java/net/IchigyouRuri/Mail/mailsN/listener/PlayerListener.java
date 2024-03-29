package net.IchigyouRuri.Mail.mailsN.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.IchigyouRuri.Mail.Utils.load.LoadCfg;
import net.IchigyouRuri.Mail.Utils.load.LoadLang;
import net.IchigyouRuri.Mail.Utils.load.LoadMails;
import net.IchigyouRuri.Mail.Main;
import net.IchigyouRuri.Mail.mailsN.math.MailWindowMath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    /**
     * 创建用户邮箱文件
     * @param e 玩家加入事件
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        File pf=new File(Main.getPlugin().getDataFolder()+File.separator+"Players"+File.separator+"Mail",p.getName()+".yml");
        if(!pf.exists()) {
            File fileParents = pf.getParentFile();
            //新建文件夹
            if (!fileParents.exists()) {
                fileParents.mkdirs();
            }
            try {
                pf.createNewFile();
                Config pfs=new Config(pf,Config.YAML);
                pfs.set("Global",new ArrayList<String>());
                pfs.save();
            } catch (IOException es) {
                es.printStackTrace();
                Main.getPlugin().getLogger().info(TextFormat.RED + "[GlobalShopN] 插件内部错误: ERROR-301");
            }
        }else{
            Config pfi=new Config(pf,Config.YAML);
            if("newPlayer".equals(pfi.get("Global"))){
                pfi.set("Global",new ArrayList<String>());
                pfi.save();
            }
            int mailCount=pfi.getKeys(false).size();
            int globalCount=LoadMails.globalMails.getKeys(false).size()-pfi.getStringList("Global").size();
            int count=(mailCount-1)+globalCount;
            if(count>0){
                p.sendMessage(LoadLang.title+LoadLang.mailHas.replaceAll("<count>",(count)+""));
            }
        }

        this.checkUnreadEmail(p);
    }

    /**
     * 检查玩家是否有未读邮件，并弹出
     * @param p 玩家
     */
    public void checkUnreadEmail(Player p){
        File pf=new File(Main.getPlugin().getDataFolder()+File.separator+"Players"+File.separator+"Mail",p.getName()+".yml");
        if(!pf.exists()) {
            return;
        }
        Config pfs=new Config(pf,Config.YAML);
        List<String> hasLookedMails=pfs.getStringList("Global");
        for(String key: LoadMails.globalMails.getKeys(false)){
            if(!hasLookedMails.contains(key)){
                if(LoadMails.globalMails.getBoolean(key+".AutoLook")){
                    //延迟 xxS 后弹出界面
                    Server.getInstance().getScheduler().scheduleDelayedTask(Main.getPlugin(), () -> {
                        p.showFormWindow(MailWindowMath.getGlobalMailListWindow(p));
                    }, LoadCfg.mailAutoLookTick);
                    break;
                }
            }
        }
    }
}
