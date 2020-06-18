/*
This file is responsable for creating the lock format to be written to the file.

 */

package Tools.Lock;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


class CreateLock {

    private LABMATT_SERVER_CONTROLLER plugin;

    CreateLock(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    boolean NewLock(Player plr, String[] args) {

        Lock_Edit_Locked_Items edititem = new Lock_Edit_Locked_Items(plugin);
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);
        Block plrTarget = plr.getTargetBlockExact(10);
        Lock_Manger lockm = new Lock_Manger(plugin);

        if (plrTarget != null) {

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player is now locking an item.");

            String blockLockMeta = (int) plrTarget.getLocation().getX() + " " + (int) plrTarget.getLocation().getY() + " " + (int) plrTarget.getLocation().getZ() + " " + plrTarget.getType().toString() + " " + plrTarget.getWorld().getName() + " ";

            List<String> lockedList = config.getConfig(new File("lockdata" + File.separator + "chunks" + File.separator + plrTarget.getChunk().toString())).getStringList(plrTarget.getWorld().getName() + "." + "locked");

            for (String currentCheck : lockedList) {
                if (currentCheck.contains(blockLockMeta)) {
                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "This item is already locked by another Player or Faction.");
                    return true;
                }
            }

            //If the block is confimed to be a valid block then we can get all the blocks info to lock it. To lock some somthing we add it config to the config.
            if (lockm.isBlockType(plrTarget.getType().toString())) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Is a lockable type.");

                String faction;

                //Object type that stores the info of a chest.
                //1 X axis
                //2 Y axis
                //3 Z axis
                //4 Block type
                //5 plr world the block is in
                //6 the players that can open it. Team or players

                if (args[0].equalsIgnoreCase("t")) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player is locking to a team.");

                    String[] teams = new String[args.length - 1];

                    if (args.length == 1) {

                        plr.sendMessage(ChatColor.RED + "Please enter team.");
                        return true;
                    }
                    else
                    {
                        for (int checkTeams = 1; checkTeams < args.length; checkTeams++) {

                            try {
                                Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();

                                if (Objects.requireNonNull(scoreboard.getTeam(args[checkTeams])).getName().equals(args[checkTeams])) {

                                    teams[checkTeams - 1] = Objects.requireNonNull(scoreboard.getTeam(args[checkTeams])).getName();
                                } else {
                                    plr.sendMessage(ChatColor.RED + "No team by the name of " + args[checkTeams]);
                                    return true;
                                }

                            } catch (NullPointerException e) {
                                plr.sendMessage(ChatColor.RED + "No scoreboard found. Type /team to make a team.");
                                return true;
                            }
                        }
                        faction = "Team " + Arrays.toString(teams);
                    }

                } else {
                    //The string needed to store players that that lock will allow though;
                    String[] lockPlayers = new String[args.length];

                    //Goes though all arugments and grabs the player names if there
                    for (int getplayers = 0; getplayers < args.length; getplayers++) {

                        //Try get the players dispaly name if there online.
                        try {
                            if (Objects.requireNonNull(Bukkit.getPlayer(args[getplayers])).getDisplayName().equalsIgnoreCase(args[getplayers])) {

                                //Grab there UUID and save it to lockplayers array.
                                lockPlayers[getplayers] = Objects.requireNonNull(Bukkit.getPlayer(args[getplayers])).getUniqueId().toString();
                            }

                            //Catch an exception if the players name can not be found.
                        } catch (NullPointerException e) {
                            //Send the error message to the player
                            plr.sendMessage(ChatColor.RED + "No Player by the name of " + args[getplayers] + " found online.");
                            return true;
                        }

                    }

                    //Set the block info to the list of players
                    faction = "Player " + Arrays.toString(lockPlayers);
                }

                if (!faction.isEmpty()) {
                    String LocInfo = blockLockMeta.concat(faction);
                    addSecondBlock(plrTarget, plr, faction);

                    if (edititem.editItem(LocInfo, true)) {
                        plr.sendMessage(ChatColor.GREEN + "Locked.");
                    } else {
                        plr.sendMessage(ChatColor.DARK_RED + "Error Locking Item.");
                        return true;
                    }
                } else {
                    plr.sendMessage(ChatColor.DARK_RED + "Error Locking Item.");
                    return false;
                }
            } else {
                plr.sendMessage(ChatColor.DARK_RED + "Block Type Not Lockable.");
                return true;
            }

            return true;
        }
        return true;
    }


    //End of locking first part
//###########################################################################################################################################################################################################################################################################################################3


    private void addSecondBlock(Block plrTarget, Player plr, String faction) {
        Lock_Manger lockm = new Lock_Manger(plugin);

        Block secondBlock = lockm.getSecondBlock(plrTarget);

        if (secondBlock != null) {
            String secondBlockMeta = (int) secondBlock.getLocation().getX() + " " + (int) secondBlock.getLocation().getY() + " " + (int) secondBlock.getLocation().getZ() + " " + secondBlock.getType().toString() + " " + secondBlock.getWorld().getName() + " " + faction;
            if (!lockm.isLocked(secondBlock)) {
                if (!lockm.editItem(secondBlockMeta, true)) {
                    plr.sendMessage(ChatColor.YELLOW + "Error Locking Second Half Of Item.");
                }
            } else {
                plr.sendMessage(ChatColor.YELLOW + "The Second Half Of This Item Is Already Locked.");
            }
        }
    }
}
