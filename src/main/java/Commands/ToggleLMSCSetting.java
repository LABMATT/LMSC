package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class ToggleLMSCSetting implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public ToggleLMSCSetting(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("LMSCtg").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("lmsctg")) {
            if (sender instanceof Player && !sender.isOp()) {
                sender.sendMessage(ChatColor.DARK_RED + "You must be Administrator to use this command.");
                return true;
            } else {

                plugin.reloadConfig();

                if (args.length == 2) {
                    if (plugin.getConfig().isBoolean(args[0])) {
                        if (args[1].equalsIgnoreCase("true")) {
                            plugin.getConfig().set(args[0], true);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.GREEN + args[0] + " is now true");
                        } else if (args[1].equalsIgnoreCase("false")) {
                            plugin.getConfig().set(args[0], false);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.GREEN + args[0] + " is now false");
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Value must be true or false.");
                            return true;
                        }
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "No setting " + args[0] + " found.");
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
