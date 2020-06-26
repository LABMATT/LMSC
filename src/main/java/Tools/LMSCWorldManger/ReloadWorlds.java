package Tools.LMSCWorldManger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class ReloadWorlds{

    ReloadWorlds(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender, String[] args) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to reload worlds.");
            new ReloadloadAllWorlds(plugin, sender);
            sender.sendMessage(ChatColor.GREEN + "Finished reloading worlds.");

    }
}
