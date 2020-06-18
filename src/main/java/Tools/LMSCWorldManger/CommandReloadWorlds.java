package Tools.LMSCWorldManger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class CommandReloadWorlds implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public CommandReloadWorlds(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("reloadWorlds");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("reloadWorlds")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to reload worlds.");
            new ReloadloadAllWorlds(plugin, sender);
            sender.sendMessage(ChatColor.GREEN + "Finished reloading worlds.");
            return true;
        }
        return false;
    }
}
