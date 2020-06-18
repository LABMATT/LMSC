package Tools.Hammer;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class HammerDisconnectEvent implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    HammerDisconnectEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Logout log = new Logout(plugin);
        HammerToggle ht = new HammerToggle(plugin);

        log.debugOut(getClass().getName(), ChatColor.AQUA, "Player <" + event.getPlayer() + "> has disconnected. Removing them form hammer array.");
        ht.disableHammer(event.getPlayer());
    }
}
