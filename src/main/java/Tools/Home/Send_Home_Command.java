package Tools.Home;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;


public class Send_Home_Command implements CommandExecutor {

    //usage in main plugin
    private LABMATT_SERVER_CONTROLLER plugin;

    Send_Home_Command(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("home")).setExecutor(this);
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        Send_Player_Home accessSendPlayerHome = new Send_Player_Home(plugin);

        //if the command was sent by the terminal then igore it but if it was send by a user then check if its got arges.
        if (sender instanceof Player) {
            Player plr = (Player) sender;

            //If the player typed the home command then send the player home.
            if (command.getName().equalsIgnoreCase("home")) {
                log.debugOut(getClass().getName(), ChatColor.AQUA, "Player " + plr.getDisplayName() + " Trigged home.");
                accessSendPlayerHome.sendPlayerHome(plr, args);
                return true;
            } else {
                //If they dident say home then just say this.
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