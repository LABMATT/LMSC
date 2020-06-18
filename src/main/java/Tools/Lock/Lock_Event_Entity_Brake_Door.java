/*
This class prevents zombies form braking down locked doors.
 */

package Tools.Lock;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Event_Entity_Brake_Door implements Listener {


    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Event_Entity_Brake_Door(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void plateUsed(EntityBreakDoorEvent event) {
        Lock_Manger lock_manger = new Lock_Manger(plugin);

        Block clickedBlock = event.getBlock();

        if (lock_manger.isBlockType(clickedBlock.getType().toString())) {

            if (lock_manger.isLocked(clickedBlock)) {

                event.setCancelled(true);
            }
        }
    }
}
