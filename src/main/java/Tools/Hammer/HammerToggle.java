package Tools.Hammer;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static Tools.Hammer.RegisterHammer.bar;

public class HammerToggle implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    HammerToggle(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("ham")).setExecutor(this);
    }

    private static Set<String> plrHammerToggle = new LinkedHashSet<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);

        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("ham")) {
                //Converts sender to player.
                Player plr = (Player) sender;

                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player " + plr.getDisplayName() + " toggled hammer.");

                String alreadyPlayer = "";

                //Gets all the players from the plrHammertoggle and loads them into a string called prePlayers. If the players UUID is found to be the same as the events players UUID then set already player to this prePlayers.
                for (String prePlayers : plrHammerToggle) {
                    if (prePlayers.contains(plr.getUniqueId().toString())) {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Already a player.");
                        alreadyPlayer = prePlayers;
                    }
                }

                if (alreadyPlayer.contains(plr.getUniqueId().toString() + ":true")) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Toggled to false.");
                    plrHammerToggle.remove(plr.getUniqueId().toString() + ":true");
                    plrHammerToggle.add(plr.getUniqueId().toString() + ":false");
                    plr.sendMessage(ChatColor.GREEN + "Hammer is now off.");
                    bar.removePlayer(plr);
                } else if (alreadyPlayer.contains(plr.getUniqueId().toString() + ":false")) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Toggled to true.");
                    plrHammerToggle.remove(plr.getUniqueId().toString() + ":false");
                    plrHammerToggle.add(plr.getUniqueId().toString() + ":true");
                    plr.sendMessage(ChatColor.GREEN + "Hammer is now on.");
                    bar.addPlayer(plr);
                } else {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "No player found. Adding player to Set and activateing hammer.");
                    plrHammerToggle.add(plr.getUniqueId().toString() + ":true");
                    plr.sendMessage(ChatColor.GREEN + "Hammer is now on.");
                    bar.addPlayer(plr);
                }

            } else {
                return false;
            }

        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
        }

        return true;
    }


    //This function is used by the Hammer event to read if the player has hammer enabled.
    Boolean plrActiveHammer(Player plr) {
        String alreadyPlayer = "";

        for (String prePlayers : plrHammerToggle) {
            if (prePlayers.contains(plr.getUniqueId().toString())) {
                alreadyPlayer = prePlayers;
            }
        }

        if (alreadyPlayer.contains(plr.getUniqueId().toString() + ":true")) {
            return true;
        } else if (alreadyPlayer.contains(plr.getUniqueId().toString() + ":false")) {
            return false;
        }

        return false;
    }

    //Uses player pramter to get the players uuid. Cycles thought the plrHammerToggle set and if it find the player then removes that occurrence from the set.
    void disableHammer(Player plr) {
        for (String player : plrHammerToggle) {
            if (player.contains(plr.getUniqueId().toString())) {
                plrHammerToggle.remove(player);
                break;
            }
        }
    }
}

