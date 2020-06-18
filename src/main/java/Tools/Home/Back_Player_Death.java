/*
This class records the location of a players death.
 */

package Tools.Home;

import Mangers.ConfigManger;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class Back_Player_Death implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    Back_Player_Death(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onInteract(PlayerDeathEvent event) {

        ConfigManger config = new ConfigManger(plugin);
        Player plr = event.getEntity();

        World worldname = event.getEntity().getLocation().getWorld();

        if(worldname != null)
        {

            String deathLoc = "" + worldname.getName() + " " + (int) event.getEntity().getLocation().getX() + " " + (int) event.getEntity().getLocation().getY() + " " + (int) event.getEntity().getLocation().getZ();

            config.getConfig(new File("home" + File.separator + "back" + File.separator + "deaths")).set("Location." + plr.getUniqueId().toString(), deathLoc);
            config.saveConfig();
        }
    }
}
