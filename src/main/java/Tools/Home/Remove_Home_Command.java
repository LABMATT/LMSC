package Tools.Home;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;


public class Remove_Home_Command implements CommandExecutor {

    //usage in main plugin
    private LABMATT_SERVER_CONTROLLER plugin;

    Remove_Home_Command(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("rmhome")).setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        Remove_Home remove_home = new Remove_Home(plugin);

        if (sender instanceof Player) {
            Player plr = (Player) sender;

            //If the player typed /sethome then call the set home function.
            if (command.getName().equalsIgnoreCase("rmhome")) {
                log.debugOut(getClass().getName(), ChatColor.AQUA, "A player is removing home.");
                remove_home.removeNewHome(plr, args);
                return true;
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Unknown command.");
                return false;
            }
        } else {
            //Tell the server terminal that you must be a users to use this command.
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
            return true;
        }
    }
}