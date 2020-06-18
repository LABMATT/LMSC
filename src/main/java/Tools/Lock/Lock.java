/*
This is called when the player types in /lc or /unlc.
If the /lc command is called then we create a new lock event.
If the /unlc command is called then remove the unlock event form the config.
 */


package Tools.Lock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Lock(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;

        PluginCommand command = plugin.getCommand("lc");
        if (command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Makes sure ony players can use this command.
        if (sender instanceof Player) {
            Player plr = (Player) sender;

            //if the player is locking item.
            if (command.getName().equalsIgnoreCase("lc")) {
                //Creates new lock instance to call apon.


                //Checks if we got arguments from players.
                if (args.length >= 1) {

                    if(args[0].equalsIgnoreCase("?"))
                    {
                        Lock_Check lock_check = new Lock_Check(plugin);
                        lock_check.checkLocked(plr);
                    }
                    else
                    {
                        //Calls the new lock event and passes the player and its arguments in.
                        CreateLock createlockacces = new CreateLock(plugin);
                        if (!createlockacces.NewLock(plr, args)) {
                            sender.sendMessage(ChatColor.RED + "Error locking item.");
                        }
                    }

                } else {

                    Lock_Check lock_check = new Lock_Check(plugin);
                    lock_check.checkLocked(plr);
                }

            } else {
                //if the command is nother lc or unlc then produce this message.
                sender.sendMessage("Unknown command: " + command);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
        }

        return true;
    }
}
