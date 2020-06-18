/*
Gets a players last death location from the config and then teloprts them to it.
 */
package Tools.Home;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Objects;


public class Back_Command implements CommandExecutor {

    //usage in main plugin
    private LABMATT_SERVER_CONTROLLER plugin;

    Back_Command(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("back")).setExecutor(this);
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        //if the command was sent by the terminal then igore it but if it was send by a user then check if its got arges.
        if (sender instanceof Player) {

            Player plr = (Player) sender;

            //If the player typed the back command then send the player to death point.
            if (command.getName().equalsIgnoreCase("back") && args.length == 0) {

                log.debugOut(getClass().getName(), ChatColor.AQUA, "Player " + plr.getDisplayName() + " is returing to death.");

                String plrDeath = config.getConfig(new File("home" + File.separator + "back" + File.separator + "deaths")).getString("Location." + plr.getUniqueId().toString());

                if(plrDeath != null)
                {

                    String[] death = plrDeath.split(" ");

                    try {

                        World world = plugin.getServer().getWorld(death[0]);

                        if (world != null) {

                            plr.teleport(new Location(world, Integer.parseInt(death[1]), Integer.parseInt(death[2]), Integer.parseInt(death[3]), plr.getLocation().getYaw(), plr.getLocation().getPitch()));
                        }
                        else {

                            throw new NumberFormatException("World was null.");
                        }

                    } catch (NumberFormatException e)
                    {

                        sender.sendMessage(ChatColor.RED + "Error sending you to your last death point.");
                        log.debugOut(getClass().getName(), ChatColor.AQUA, "Player failed to return to death point becuse <" + e.getMessage() + ">.");
                        return true;
                    }

                } else
                {

                    sender.sendMessage(ChatColor.RED + "You have not died yet.");
                    return true;
                }

                return true;
            } else {

                //If they dident say home then just say this.
                sender.sendMessage(ChatColor.RED + "Incorrect syntax. Please use: ");
                return false;
            }
        } else {

            //Tell the server terminal that you must be a users to use this command.
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
    }
}