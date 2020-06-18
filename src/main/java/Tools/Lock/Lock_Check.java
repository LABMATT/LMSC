package Tools.Lock;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.*;

class Lock_Check {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Check(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    void checkLocked(Player plr) {

        Logout log = new Logout(plugin);

        if (plr != null) {

            Lock_Manger lockm = new Lock_Manger(plugin);
            Block plrTarget = plr.getTargetBlockExact(10);

            if (plrTarget != null) {

                if (lockm.isBlockType(plrTarget.getType().toString())) {

                    if (lockm.isLocked(plrTarget)) {
                        String locked = lockm.getLockItem(plrTarget);
                        Set<String> players = lockm.getPlayers(locked);

                        if (plr.isOp()) {

                            List<String> playerList = new ArrayList<>();

                            for (String currentPlaryer : players) {

                                Player plrGot = null;

                                try {
                                    plrGot = plugin.getServer().getPlayer(UUID.fromString(currentPlaryer));
                                } catch (IllegalArgumentException ignored)
                                {

                                }

                                if (plrGot != null) {
                                    playerList.add(plrGot.getDisplayName());
                                }
                            }

                            plr.sendMessage(ChatColor.GREEN + "Block Is Locked To online players <" + Arrays.toString(playerList.toArray()) + ">.");

                        } else if (players.contains(plr.getDisplayName())) {
                            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Player NOT OP!");

                            plr.sendMessage(ChatColor.GREEN + "This Item Is Locked To You.");
                        } else {
                            plr.sendMessage(ChatColor.YELLOW + "This item is already locked by another Player or Faction.");
                        }
                    } else {
                        plr.sendMessage(ChatColor.GREEN + "This Item Is Not Locked.");
                    }


                } else {
                    plr.sendMessage(ChatColor.DARK_RED + "Block Type Can Not Be Locked.");
                }
            } else {
                plr.sendMessage(ChatColor.YELLOW + "You must be looking at a block to see if its locked.");
            }

        }


    }
}
