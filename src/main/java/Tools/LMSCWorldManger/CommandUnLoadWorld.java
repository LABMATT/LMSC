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

public class CommandUnLoadWorld implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public CommandUnLoadWorld(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("unloadWorld");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManger config = new ConfigManger(plugin);

        if (command.getName().equalsIgnoreCase("unloadWorld") && args.length != 0) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to unload world <" + args[0] + "> from server.");

            List<World> currentworlds = plugin.getServer().getWorlds();

            boolean removed = false;

            for (World world : currentworlds) {

                if (world.getName().equalsIgnoreCase(args[0])) {

                    plugin.getServer().unloadWorld(world, true);

                    List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");

                    boolean isactive = false;

                    for(String worldname : worldList)
                    {
                        if(worldname.equalsIgnoreCase(world.getName()))
                        {
                            isactive = true;
                            break;
                        }
                    }

                    if (!isactive) {
                        sender.sendMessage(ChatColor.YELLOW + "The world <" + args[0] + "> was not in the active worlds config but was unloaded anyway.");
                    } else {
                        worldList.remove(world.getName());
                        config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).set("worlds", worldList);
                        config.saveConfig();

                        List<String> noactiveworldList = config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).getStringList("worlds");
                        noactiveworldList.add(world.getName());
                        config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).set("worlds", noactiveworldList);
                        config.saveConfig();

                    }

                    removed = true;
                    break;
                }
            }

            if (!removed) {

                sender.sendMessage(ChatColor.DARK_RED + "No world <" + args[0] + "> was loaded.");
                return true;
            } else {

                sender.sendMessage(ChatColor.GREEN + "World <" + args[0] + "> unloaded. World is now listed as non active.");
                return true;
            }
        }

        return false;
    }
}
