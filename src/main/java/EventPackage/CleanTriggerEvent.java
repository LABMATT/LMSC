package EventPackage;

import Action.LMSCcleanup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class CleanTriggerEvent implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public CleanTriggerEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    private Player plr;

    @EventHandler
    public void onInteract(PlayerJoinEvent event) {
        LMSCcleanup clean = new LMSCcleanup(plugin);

        clean.preformCleanup();
    }
}
