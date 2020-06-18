/*
When a explosion happens we check if a lockable block is in radias and not locked.
If there is one then cancel it.
If not then then blow up.
 */

package Tools.Lock;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.List;

public class Lock_Explosion_Damage implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Explosion_Damage(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(EntityExplodeEvent event) {

        Lock_Manger lockM = new Lock_Manger(plugin);

        List<Block> blockList = event.blockList();

        for (Block currentBlock : blockList) {
            if (lockM.isBlockType(currentBlock.getType().toString())) {
                if (lockM.isLocked(currentBlock)) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}
