/*
This class detects if a enity trys to open a locked item. if they doo then prevent it.
 */

package Tools.Lock;

import SubActions.Logout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Event_Villeger implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Event_Villeger(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entityInteractLock(EntityInteractEvent event) {
        Logout log = new Logout(plugin);
        Lock_Manger lock_manger = new Lock_Manger(plugin);

        if(lock_manger.isBlockType(event.getBlock().getType().toString()))
        {
            if(lock_manger.isLocked(event.getBlock()))
            {
                event.setCancelled(true);
            }
        }
    }
}
