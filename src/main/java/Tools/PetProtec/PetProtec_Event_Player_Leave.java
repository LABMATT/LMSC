package Tools.PetProtec;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class PetProtec_Event_Player_Leave implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    PetProtec_Event_Player_Leave(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerLogOff(PlayerQuitEvent event) {

        PetProtec_Transport petProtec_transport = new PetProtec_Transport();

        petProtec_transport.addName(event.getPlayer().getUniqueId().toString());
    }
}
