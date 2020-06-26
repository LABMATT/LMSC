package Tools.LMSCWorldManger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class WorldCommands implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    WorldCommands(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("world");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args[0] != null)
        {
            args[0] = args[0].toLowerCase();

            if (command.getName().equalsIgnoreCase("world")) {

                switch(args[0])
                {
                    // If Adding world then go to the add world function.
                    case "add":
                        new AddWorld(plugin, sender, args);
                        return true;

                    // If removing world form server then go to remove function.
                    case "remove":

                        break;

                    // If to activate a world then preform the world activation function.
                    case "load":
                        new LoadWorld(plugin, sender, args);
                        return true;

                    // If to deactivate a world then preform the world activation function.
                    case "unload":
                        new UnloadWorld(plugin, sender, args);
                        return true;

                    case "list":
                        new ListWorlds(plugin, sender, args);
                        return true;

                    case "reload":
                        new ReloadWorlds(plugin, sender, args);
                        return true;

                    case "tp":
                        new TpWorld(plugin, sender, args);
                        return true;

                    case "removetp":
                        new TpWorldRemove(plugin, sender, args);
                        return true;

                    default:
                        sender.sendMessage(ChatColor.DARK_RED + "Unknown argument <" + args[0] + ">");
                        return true;
                }

            }
        }


        return false;
    }
}
