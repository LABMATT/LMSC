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

public class AddWorld implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public AddWorld(LABMATT_SERVER_CONTROLLER plugin) {
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


        if (command.getName().equalsIgnoreCase("addworld") && args.length != 0) {

            if(sender instanceof Player)
            {
                Player player = (Player) sender;
                if(!player.isOp())
                {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to add world <" + args[0] + "> to server.");

            WorldCreator creator = new WorldCreator(args[0]);
            File worldfile = new File(plugin.getServer().getWorldContainer().getPath() + File.separator + args[0]);

            if (worldfile.exists()) {

                List<World> currentworlds = plugin.getServer().getWorlds();

                for (World currentWorld : currentworlds) {
                    if (currentWorld.getName().equalsIgnoreCase(args[0])) {

                        sender.sendMessage(ChatColor.DARK_RED + "World is already loaded.");
                        return true;
                    }
                }

                try {
                    creator.createWorld();

                    List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                    worldList.add(args[0]);
                    config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).set("worlds", worldList);
                    config.saveConfig();

                } catch (Exception e) {
                    sender.sendMessage(ChatColor.DARK_RED + "Error Adding world <" + args[0] + ">");
                    return true;
                }

            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Could not find world <" + args[0] + ">.");
                return true;
            }

            sender.sendMessage(ChatColor.GREEN + "World added successful.");
            return true;
        }

        return false;
    }
}
