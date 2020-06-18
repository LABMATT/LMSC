package Tools.Home;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class RegisterHomeTool {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterHomeTool(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerHome() {
        ConfigManger config = new ConfigManger(plugin);




        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("home")) {

            if (!config.isFile(new File("home" + File.separator + "home-Config"))) {

                config.getConfig(new File("home" + File.separator + "home-Config")).set("Max-Homes", 1);
                config.saveConfig();
            }

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Home by LMSC is active on this server.");
            new Send_Home_Command(plugin);
            new Set_Home_Command(plugin);
            new Remove_Home_Command(plugin);
        }

        if(plugin.getConfig().getBoolean("back"))
        {

            plugin.getServer().getPluginManager().registerEvents(new Back_Player_Death(plugin), plugin);
            new Back_Command(plugin);
        }

    }
}
