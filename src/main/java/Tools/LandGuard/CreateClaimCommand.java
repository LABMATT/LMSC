package Tools.LandGuard;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


/*
If the player types /claim Labmatt then it sets the save point for the player.
If the player types /claim t sab then it sets the first point and claims the land under a team name.
If the player types /claim exit then it cancels the claim command and erases the progress.
 */


public class CreateClaimCommand implements CommandExecutor {
    private LABMATT_SERVER_CONTROLLER plugin;

    CreateClaimCommand(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
        Objects.requireNonNull(plugin.getCommand("claim")).setExecutor(this);
    }

    //Has map storing possible points.
    private static HashMap<String, Integer[]> point = new HashMap<>();

    private Integer[] setPreValues = {null, null, null, null};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        RequestCreateClaim createClaim = new RequestCreateClaim(plugin);
        Logout log = new Logout(plugin);

        if (sender instanceof Player) {
            Player plr = (Player) sender;

            if (command.getName().equalsIgnoreCase("claim")) {

                /*
                Args[0] = exit or Land Name
                Args[1] = t or player name
                Args[2] = Team Name
                 */

                if (args.length > 0) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> trigged a claim event.");
                    // This thingo returness array overflow because args is not long enough
                    if (!args[0].equalsIgnoreCase("exit")) {


                        if (!point.containsKey(plr.getUniqueId().toString())) {
                            point.put(plr.getUniqueId().toString(), setPreValues);
                        }

                        Integer[] hashPoints = point.get(plr.getUniqueId().toString());

                        if (hashPoints[0] == null || hashPoints[1] == null) {
                            hashPoints[0] = plr.getLocation().getBlockX();
                            hashPoints[1] = plr.getLocation().getBlockZ();
                            point.replace(plr.getUniqueId().toString(), hashPoints);
                        } else if (hashPoints[2] == null || hashPoints[3] == null) {
                            hashPoints[2] = plr.getLocation().getBlockX();
                            hashPoints[3] = plr.getLocation().getBlockZ();
                            point.replace(plr.getUniqueId().toString(), hashPoints);

                            createClaim.createClaim(point.get(plr.getUniqueId().toString()), args, plr.getWorld());
                        }

                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> Your hasmap is " + Arrays.toString(point.get(plr.getUniqueId().toString())));


                        plr.sendMessage(ChatColor.GREEN + "Your hasmap is " + Arrays.toString(point.get(plr.getUniqueId().toString())));
                    } else {
                        point.remove(plr.getUniqueId().toString());
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> Canceled a a claim event.");
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
            return true;
        }

        return false;
    }


}
