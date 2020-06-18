package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class ReloadloadAllWorlds {

    //usage in main plugin

    public ReloadloadAllWorlds(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender) {

        ConfigManger config = new ConfigManger(plugin);

        if(sender == null)
        {
            sender = plugin.getServer().getConsoleSender();
        }

        sender.sendMessage(ChatColor.GREEN + "Loading Words.");

        List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
        List<World> currentworlds = plugin.getServer().getWorlds();


        for (String world : worldList) {

            sender.sendMessage(ChatColor.GREEN + "Attempting to load world <" + world + ">.");

            File worldfile = new File(plugin.getServer().getWorldContainer().getPath() + File.separator + world);

            if (worldfile.exists()) {

                boolean isloaded = false;

                for (World currentWorld : currentworlds) {
                    if (currentWorld.getName().equalsIgnoreCase(world)) {

                        sender.sendMessage(ChatColor.DARK_RED + "World <" + world + "> is already loaded.");
                        isloaded = true;
                        break;
                    }
                }

                if (!isloaded) {
                    try {

                        WorldCreator creator = new WorldCreator(world);
                        creator.createWorld();

                        sender.sendMessage(ChatColor.GREEN + "World <" + world + "> is loaded.");
                    } catch (Exception e) {

                        sender.sendMessage(ChatColor.DARK_RED + "Error Adding world <" + world + ">");
                    }
                }


            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Could not find world <" + world + ">.");
            }
        }

        new InvConfigUpdate(plugin);
    }
}
