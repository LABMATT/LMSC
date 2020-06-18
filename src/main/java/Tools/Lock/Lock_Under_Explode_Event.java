/*
If a block blows up and its under a locked bock then cancel the event.
This is its own class because if a explitoon goes off and the locked item is not in rage then it can brake the block under it.
 */

package Tools.Lock;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Under_Explode_Event implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    public Lock_Under_Explode_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onBrake(BlockExplodeEvent event) {
        Lock_Get_Locked_Items lock = new Lock_Get_Locked_Items(plugin);
        Lock_Manger lockM = new Lock_Manger(plugin);

        Block block = event.getBlock();
        Block aboveBlock = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ()).getBlock();

        if (lockM.isBlockType(aboveBlock.getType().toString())) {
            if (lock.isLocked(aboveBlock)) {
                event.setCancelled(true);
            }
        }
    }
}
