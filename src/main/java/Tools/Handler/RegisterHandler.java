package Tools.Handler;

import Mangers.ConfigManger;
import Tools.Home.*;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class RegisterHandler {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterHandler(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerHandler() {
        ConfigManger config = new ConfigManger(plugin);




        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("handler")) {

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Handler by LMSC is active on this server.");

            plugin.getServer().getPluginManager().registerEvents(new Block_Break_Event(plugin), plugin);


        }
    }
}
