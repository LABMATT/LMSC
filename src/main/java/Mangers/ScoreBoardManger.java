package Mangers;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreBoardManger {

    private LABMATT_SERVER_CONTROLLER plugin;

    public ScoreBoardManger(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public List<String> getPlrStringList(String team) {
        Logout log = new Logout(plugin);

        List<String> returnplayerList = new ArrayList<>();

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Looking for team <" + team + ">.");

        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();

        String strPlr = Objects.requireNonNull(scoreboard.getTeam(team)).getEntries().toString();

        String[] strPlrArray = strPlr.split(", ");

        //For each player retruned in the strPlrArray get the string, replace all the junk and then
        for (String r : strPlrArray) {
            r = r.replace("[", "");
            r = r.replace("]", "");

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking if this player <" + r + "> is online.");

            if (r.length() != 0) {
                Player plr = Bukkit.getPlayer(r);
                if (plr != null) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> is online. Adding to return array.");
                    returnplayerList.add(plr.getUniqueId().toString());
                }
            }
        }
        return returnplayerList;
    }


    public List<Player> getPlrList(String team) {
        Logout log = new Logout(plugin);

        List<Player> returnplayerList = new ArrayList<>();

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Looking for team <" + team + ">.");

        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();

        String strPlr = Objects.requireNonNull(scoreboard.getTeam(team)).getEntries().toString();

        String[] strPlrArray = strPlr.split(", ");

        //For each player retruned in the strPlrArray get the string, replace all the junk and then
        for (String r : strPlrArray) {
            r = r.replace("[", "");
            r = r.replace("]", "");

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking if this player <" + r + "> is online.");

            if (r.length() != 0) {
                Player plr = Bukkit.getPlayer(r);
                if (plr != null) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> is online. Adding to return array.");
                    returnplayerList.add(plr);
                }
            }
        }
        return returnplayerList;
    }

}
