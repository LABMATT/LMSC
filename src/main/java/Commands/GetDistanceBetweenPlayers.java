package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class GetDistanceBetweenPlayers implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public GetDistanceBetweenPlayers(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("dis")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("dis")) {
            try {
                if (!(sender instanceof Player) || sender.isOp()) {
                    if (args.length == 2) {
                        Player plr1;
                        Player plr2;


                        try {
                            plr1 = plugin.getServer().getPlayer(args[0]);
                            if (plr1 == null) {
                                throw new NullPointerException("Player1 is null");
                            }
                        } catch (NullPointerException e) {
                            sender.sendMessage(ChatColor.DARK_RED + "Unable to find player " + args[0]);
                            return true;
                        }

                        try {
                            plr2 = plugin.getServer().getPlayer(args[1]);
                            if (plr2 == null) {
                                throw new NullPointerException("Player2 is null");
                            }
                        } catch (NullPointerException e) {
                            sender.sendMessage(ChatColor.DARK_RED + "Unable to find player " + args[1]);
                            return true;
                        }

                        try {
                            sender.sendMessage(ChatColor.GREEN + "Distance between players: " + ((int) plr1.getLocation().distance(plr2.getLocation())) + " Blocks.");
                        } catch (NullPointerException e) {
                            sender.sendMessage(ChatColor.DARK_RED + "Unable to get distance.");
                        }
                    } else {
                        return false;
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be an Administrator to use this command.");
                }
                return true;
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.DARK_RED + "There was a fatel error getting the dstiace beteen players.");
            }
        }
        return true;
    }
}
