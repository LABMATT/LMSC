package Tools.AdminTools;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Last_Seen implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Last_Seen(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;

        PluginCommand command = plugin.getCommand("seen");
        if (command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ConfigManger configManger = new ConfigManger(plugin);

        if (command.getName().equalsIgnoreCase("seen")) {
            if (args.length > 0) {
                Player plr = plugin.getServer().getPlayer(args[0]);

                if (plr != null) {
                    sender.sendMessage(ChatColor.GREEN + "Player <" + plr.getDisplayName() + "> last played <" + plr.getLastPlayed() + ">.");
                    return true;
                } else
                {
                    sender.sendMessage(ChatColor.YELLOW + "Could not find player <" + args[0] + ">.");
                    return true;
                }
            }
            {
                return false;
            }
        } else
        {
            sender.sendMessage(ChatColor.DARK_RED + "Unknown command.");
            return true;
        }
    }
}
