package Tools.CommandBlockFinder;

//allows crafting of spawners and name tags

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class RegisterCBF {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterCBF(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerCommandBlockFinder() {
        Logout log = new Logout(plugin);
        //ConfigManger config = new ConfigManger(plugin);

        plugin.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Command Block Finder READYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY.");


        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("cbf")) {

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Command Block Finder by LABMATT_SERVER_CONTROLLER is active on this server.");
            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Command Block Finder by LABMATT_SERVER_CONTROLLER is active on this server.");

            new FindCommandBloacksCommand(plugin);
            plugin.getServer().getPluginManager().registerEvents((Listener) new LogCommandBlockEvent(plugin), plugin);
        }

    }

}
