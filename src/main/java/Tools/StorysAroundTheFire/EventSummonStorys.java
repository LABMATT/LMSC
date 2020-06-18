/*
This class records the location of a players death.
 */

package Tools.StorysAroundTheFire;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class EventSummonStorys implements Listener {

    private final LABMATT_SERVER_CONTROLLER plugin;

    EventSummonStorys(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onInteract(PlayerTeleportEvent event) {


    }
}
