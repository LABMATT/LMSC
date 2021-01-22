package Tools.CommandBlockFinder;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindCommandBloacksCommand implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public FindCommandBloacksCommand(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("fcb").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        plugin.reloadConfig();

        if (!plugin.getConfig().getBoolean("fcb")) {
            sender.sendMessage(ChatColor.DARK_RED + "Finding Command Blocks is not enabled on this server.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("fcb") && plugin.getConfig().getBoolean("fcb")) {

            //Checks if we got arguments from players.
            if (args.length == 0) {

                if(sender.isOp())
                {
                    List<String> cmbl = config.getOldConfig("fcb").getStringList("loc");

                    for (String blockloc : cmbl)
                    {
                        sender.sendMessage(ChatColor.GREEN + blockloc);
                    }

                } else
                {
                    sender.sendMessage(ChatColor.RED + "You Must be OP to use this command.");
                }

            } else return false;
        } else return false;

        return true;
    }
}
