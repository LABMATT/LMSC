/*
This class records the location of a players death.
 */

package Tools.LMSCWorldManger.InventoryManger;

import Mangers.ConfigManger;
import Tools.LMSCWorldManger.InvConfigUpdate;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class EventPlayerChangeWorlds implements Listener {

    private final LABMATT_SERVER_CONTROLLER plugin;

    public EventPlayerChangeWorlds(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {

        System.out.println("Trigged world spot evnet");

        ConfigManger config = new ConfigManger(plugin);
        Player plr = event.getPlayer();
        World worldFrom = event.getFrom();
        World worldTo = event.getPlayer().getWorld();

        String fromInv = config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).getString(worldFrom.getName());
        String toInv = config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).getString(worldTo.getName());

        new InvConfigUpdate(plugin);

        if(fromInv != null)
        {
            if(toInv != null)
            {
                if(!fromInv.equalsIgnoreCase(toInv))
                {
                    config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + fromInv)).set(plr.getUniqueId().toString(), plr.getInventory());
                    config.saveConfig();


                    config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "test")).set(plr.getUniqueId().toString() + "-a", plr.getInventory().getArmorContents());
                    config.saveConfig();

                    config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "test")).set(plr.getUniqueId().toString() + "-b", plr.getInventory().getContents());
                    config.saveConfig();

                    plr.getInventory().clear();

                    //plr.getInventory().setContents(config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "test")).getStringList(plr.getUniqueId().toString() + "-a"));
                    config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "test")).getStringList(plr.getUniqueId().toString() + "-b");


                    config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + fromInv)).set(plr.getUniqueId().toString(), plr.getInventory());
                    config.saveConfig();
                }
            }else {
                config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).set(worldTo.getName(), "default");
                config.saveConfig();
            }
        } else
        {
            config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).set(worldFrom.getName(), "default");
            config.saveConfig();
        }
    }
}
