package Tools.Lock;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class Lock_Get_Players {


    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Get_Players(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    Set<String> getPlayers(String item) {
        Set<String> returnSet = new HashSet<>();
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Getting Owners of item <" + item + ">.");

        String ownerArray = item.substring(item.indexOf("[") + 1, item.indexOf("]"));

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Locked Owners of item are <" + ownerArray + ">.");

        if (item.contains(" Team ")) {

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Is locked to teams.");

            String[] teamArray = ownerArray.split(", ");

            Scoreboard scoreboard;

            ScoreboardManager manager = Bukkit.getScoreboardManager();

            if (manager != null) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Got scoreboard manger.");
                scoreboard = manager.getMainScoreboard();

                for (String currentTeam : teamArray) {
                    Team retrivedTeam = scoreboard.getTeam(currentTeam);

                    if (retrivedTeam != null) {
                        Set<String> teamPlayers = retrivedTeam.getEntries();

                        if (teamPlayers.size() > 0) {
                            returnSet.addAll(teamPlayers);
                        }
                    }
                }

                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Finished grabbing teams.");
            }
        } else if (item.contains(" Player ")) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Item is locked to players.");

            String[] playerArray = ownerArray.split(", ");

            for (String currentPlayer : playerArray) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "currentPlayer is <" + currentPlayer + ">.");
                if (currentPlayer.length() > 3) {
                    if (plugin.getServer().getPlayer(UUID.fromString(currentPlayer)) != null) {
                        returnSet.add(currentPlayer);
                    }
                }
            }
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Finished getting players.");
        }


        return returnSet;
    }

//#############################################################################################################

    boolean isPlayerApartOf(String item, String PlayerName) {
        Set<UUID> returnSet = new HashSet<>();
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking if player is a part of item  <" + item + ">.");

        String ownerArray = item.substring(item.indexOf("[") + 1, item.indexOf("]"));

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Locked Owners of item are <" + ownerArray + ">.");


        if (item.contains(" Team ")) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Is locked to teams.");

            String[] teamArray = ownerArray.split(", ");

            Scoreboard scoreboard;

            ScoreboardManager manager = Bukkit.getScoreboardManager();

            if (manager != null) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Got scoreboard manger.");
                scoreboard = manager.getMainScoreboard();

                for (String currentTeam : teamArray) {
                    Team retrivedTeam = scoreboard.getTeam(currentTeam);

                    if (retrivedTeam != null) {
                        Set<String> teamPlayers = retrivedTeam.getEntries();

                        if (teamPlayers.size() > 0) {

                            for (String currentPlayer : teamPlayers) {
                                Player playerName = plugin.getServer().getPlayer(currentPlayer);

                                if (playerName != null) {
                                    returnSet.add(playerName.getUniqueId());
                                }
                            }


                        }
                    }
                }
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Finished grabbing teams.");
            }


        } else if (item.contains(" Player ")) {

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Item is locked to players.");

            String[] playerArray = ownerArray.split(", ");

            for (String currentPlayer : playerArray) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Now trying string <" + currentPlayer + ">.");
                if (currentPlayer.length() > 3) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "currentPlayer is <" + currentPlayer + ">.");
                    Player playerGetter = plugin.getServer().getPlayer(UUID.fromString(currentPlayer));

                    if (playerGetter != null) {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "adding <" + playerGetter.getDisplayName() + ">. to vaild players");
                        returnSet.add(playerGetter.getUniqueId());
                    }

                }
            }
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Finished getting players.");
        }

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Return set was <" + Arrays.toString(returnSet.toArray(new UUID[0])) + ">.");

        if (PlayerName.length() > 3) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking if player <" + PlayerName + "> is an owner.");
            Player imputedPlayer = plugin.getServer().getPlayer(PlayerName);

            if (imputedPlayer != null) {
                log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Input was not null.");
                String inputedPlayerDisplayName = imputedPlayer.getDisplayName();

                for (UUID currentPlayer : returnSet) {
                    Player setPlayer = plugin.getServer().getPlayer(currentPlayer);

                    if (setPlayer != null) {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Set player not null.");
                        String setplayerDisplayname = setPlayer.getDisplayName();
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Looking for <" + PlayerName + ">. Found: <" + setplayerDisplayname + ">.");

                        if (setplayerDisplayname.equals(inputedPlayerDisplayName)) {
                            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Correct player found!.");
                            return true;
                        }
                    }
                }
            } else {
                log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Inputted players was null.");
            }
        }
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "No matching players found");
        return false;
    }

}
