package Tools.TreeChop;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class treeChop_Event implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    treeChop_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void blockBrake(BlockBreakEvent event) {
        Logout log = new Logout(plugin);
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player broke block");

        if (event.getBlock().toString().contains("_LOG")) {
            Player plr = event.getPlayer();
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player Minded log.");


            if (plr.getInventory().getItemInMainHand().getType().toString().contains("_AXE")) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player Was holing an axe.");


                if (event.getBlock().getBlockData().toString().contains("axis=y")) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Log was in y postion.");


                    switch (event.getBlock().getType()) {
                        case OAK_LOG:
                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Is an Oak tree.");
                            OakTree oak = new OakTree(plugin);
                            oak.destoryTree(event.getBlock());
                            break;
                        case SPRUCE_LOG:

                            break;
                        case JUNGLE_LOG:

                            break;

                    }
                }
            }
        }
    }
}
