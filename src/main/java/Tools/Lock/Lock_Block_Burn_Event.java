/*
If a block burns with a locked block on top of it then cancel the event.
 */

package Tools.Lock;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Block_Burn_Event implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Block_Burn_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBrake(BlockBurnEvent event) {
        Lock_Manger lockM = new Lock_Manger(plugin);
        Logout log = new Logout(plugin);

        Block block = event.getBlock();
        Block aboveBlock = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ()).getBlock();

        log.debugOut(getClass().getName(), ChatColor.AQUA, "Block burned: " + block.getType().toString() + ". Block above was: " + aboveBlock.getType().toString());

        if (lockM.isBlockType(aboveBlock.getType().toString())) {
            if (lockM.isLocked(aboveBlock)) {
                log.debugOut(getClass().getName(), ChatColor.AQUA, "Prevented block form burning because there was a locked block ontop of it");
                event.setCancelled(true);
            }
        }
    }
}
