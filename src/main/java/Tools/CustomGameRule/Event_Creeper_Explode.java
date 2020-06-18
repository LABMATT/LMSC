package Tools.CustomGameRule;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Event_Creeper_Explode implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Event_Creeper_Explode(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
     public void onCreeperBoom(EntityDamageByEntityEvent event) {
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Entity went boomb");
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Event data was");



    }
}
