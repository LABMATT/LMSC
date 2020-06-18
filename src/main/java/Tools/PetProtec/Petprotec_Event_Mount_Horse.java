package Tools.PetProtec;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Petprotec_Event_Mount_Horse implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Petprotec_Event_Mount_Horse(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entityInteractLock(VehicleEnterEvent event) {

        if (event.getEntered() instanceof Player) {
            Player plr = (Player) event.getEntered();

            AnimalTamer owner = null;

            if (event.getVehicle() instanceof Horse) {
                Horse horse = (Horse) event.getVehicle();
                owner = horse.getOwner();
            } else if (event.getVehicle() instanceof Donkey) {
                Donkey horse = (Donkey) event.getVehicle();
                owner = horse.getOwner();
            } else if (event.getVehicle() instanceof Llama) {
                Llama horse = (Llama) event.getVehicle();
                owner = horse.getOwner();
            }else if (event.getVehicle() instanceof Mule) {
                Mule horse = (Mule) event.getVehicle();
                owner = horse.getOwner();
            }

            if (owner != null) {

                ConfigManger config = new ConfigManger(plugin);

                if (owner.getUniqueId() != plr.getUniqueId()) {
                    if (!plr.isOp()) {
                        plr.sendMessage(ChatColor.DARK_RED + "You do not own this animal.");
                        event.setCancelled(true);
                    }
                    if (plr.isOp()) {
                        plr.sendMessage(ChatColor.AQUA + "Overridden ownership with administrator privileges. Your Activity will be logged.");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDateTime now = LocalDateTime.now();

                        List<String> overrideConfig = config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).getStringList("overrides");
                        overrideConfig.add("Administrator " + plr.getDisplayName() + " mounted animal Owned by " + owner.getUniqueId());
                        config.getConfig(new File("petProtec" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).set("overrides", overrideConfig);
                        config.saveConfig();
                    }
                }
            }
        }
    }
}
