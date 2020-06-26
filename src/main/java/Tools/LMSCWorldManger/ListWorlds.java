package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class ListWorlds {

ListWorlds(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender, String[] args) {

        ConfigManger config = new ConfigManger(plugin);

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Servers Loaded Worlds:");

            List<World> serverWorlds = plugin.getServer().getWorlds();

            for (World world : serverWorlds) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + " - " + world.getName());
            }


            List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");

            sender.sendMessage(ChatColor.GREEN + " ");
            sender.sendMessage(ChatColor.GREEN + "LMSC Active Worlds:");

            for(String world : worldList )
            {
                sender.sendMessage(ChatColor.BLUE + " - " + world);
            }


            List<String> nonactiveworldList = config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).getStringList("worlds");

            sender.sendMessage(ChatColor.GREEN + " ");
            sender.sendMessage(ChatColor.GREEN + "LMSC Non-Active Worlds:");

            for(String world : nonactiveworldList)
            {
                sender.sendMessage(ChatColor.RED + " - " + world);
            }

    }
}

