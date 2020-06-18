/*
This class records the location of a players death.
 */

package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class EventPlayerTeloport implements Listener {

    private final LABMATT_SERVER_CONTROLLER plugin;

    EventPlayerTeloport(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onInteract(PlayerTeleportEvent event) {

        ConfigManger config = new ConfigManger(plugin);
        Player plr = event.getPlayer();

        World worldname = event.getFrom().getWorld();

        if(worldname != null)
        {
            //Create new Object array to place location values into.
            Object[] plrLocation = new Object[6];


            //Set the location values into the object array.
            plrLocation[0] = event.getFrom().getWorld().getName();
            plrLocation[1] = event.getFrom().getX();
            plrLocation[2] = event.getFrom().getY();
            plrLocation[3] = event.getFrom().getZ();
            plrLocation[4] = event.getFrom().getYaw();
            plrLocation[5] = event.getFrom().getPitch();


                config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + worldname.getName())).set(plr.getUniqueId().toString(), plrLocation);
                config.saveConfig();
        }
    }
}
