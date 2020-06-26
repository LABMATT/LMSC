package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

class AddWorld {

    AddWorld(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender, String[] args) {
        //usage in main plugin

        ConfigManger config = new ConfigManger(plugin);

            if(sender instanceof Player)
            {
                Player player = (Player) sender;
                if(!player.isOp())
                {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to add world <" + args[1] + "> to server.");

            WorldCreator creator = new WorldCreator(args[1]);
            File worldfile = new File(plugin.getServer().getWorldContainer().getPath() + File.separator + args[1]);

            if (worldfile.exists()) {

                List<World> currentworlds = plugin.getServer().getWorlds();

                for (World currentWorld : currentworlds) {
                    if (currentWorld.getName().equalsIgnoreCase(args[1])) {

                        sender.sendMessage(ChatColor.DARK_RED + "World is already loaded.");
                        return;
                    }
                }

                try {
                    creator.createWorld();

                    List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                    worldList.add(args[1]);
                    config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).set("worlds", worldList);
                    config.saveConfig();

                } catch (Exception e) {
                    sender.sendMessage(ChatColor.DARK_RED + "Error Adding world <" + args[1] + ">");
                    return;
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Could not find world <" + args[1] + ">.");
                return;
            }

            sender.sendMessage(ChatColor.GREEN + "World added successful.");
    }
    }
