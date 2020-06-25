package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class WorldCommands implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public WorldCommands(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("addworld");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManger config = new ConfigManger(plugin);

        if(args[0] != null)
        {
            if (command.getName().equalsIgnoreCase("addworld")) {

                switch(args[0])
                {
                    case "add":

                        break;
                    case "remove":

                        break;

                    case "act":

                        break;

                    case "deact":

                        break;

                    default:
                        sender.sendMessage(ChatColor.DARK_RED + "Unknown argument <" + args[0] + ">");
                        return true;
                }

            }
        }


        return false;
    }
}
