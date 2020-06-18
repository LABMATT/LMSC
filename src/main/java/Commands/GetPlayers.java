/*
Allows the /players command to display online players.
 */

package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class GetPlayers implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public GetPlayers(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("players").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("players") && (args.length == 0)) {
            try {
                sender.sendMessage(ChatColor.BLUE + "Current online players are: " + plugin.getServer().getOnlinePlayers().toString());
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.DARK_RED + "No players online.");
            }
        } else {
            return false;
        }

        return true;
    }
}
