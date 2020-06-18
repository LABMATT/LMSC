package Tools.Lock;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Lock_Create_Override {

    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Create_Override(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    void createOveride(Player plr, String message) {

        ConfigManger config = new ConfigManger(plugin);

        plr.sendMessage(ChatColor.AQUA + "Overridden lock with administrator privileges. Your Activity will be logged.");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        List<String> overrideConfig = config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).getStringList("overrides");
        overrideConfig.add(message);
        config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).set("overrides", overrideConfig);
        config.saveConfig();
    }
}

