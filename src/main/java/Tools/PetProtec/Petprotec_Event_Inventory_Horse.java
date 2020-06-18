package Tools.PetProtec;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Petprotec_Event_Inventory_Horse implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Petprotec_Event_Inventory_Horse(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entityInteractLock(InventoryOpenEvent event) {

        Player plr = (Player) event.getPlayer();

        AnimalTamer owner = null;

        if (event.getInventory().getHolder() instanceof Horse) {
            Horse horse = (Horse) event.getInventory().getHolder();
            owner = horse.getOwner();
        } else if (event.getInventory().getHolder() instanceof Donkey) {
            Donkey horse = (Donkey) event.getInventory().getHolder();
            owner = horse.getOwner();
        } else if (event.getInventory().getHolder() instanceof Llama) {
            Llama horse = (Llama) event.getInventory().getHolder();
            owner = horse.getOwner();
        }
        else if (event.getInventory().getHolder() instanceof Mule) {
            Mule horse = (Mule) event.getInventory().getHolder();
            owner = horse.getOwner();
        }

        if (owner != null) {

            ConfigManger config = new ConfigManger(plugin);

            if (owner.getUniqueId() != plr.getUniqueId()) {
                if (!event.getPlayer().isOp()) {
                    plr.sendMessage(ChatColor.DARK_RED + "You do not own this animal.");
                    event.setCancelled(true);
                }
                if (event.getPlayer().isOp()) {
                    event.getPlayer().sendMessage(ChatColor.AQUA + "Overridden ownership with administrator privileges. Your Activity will be logged.");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime now = LocalDateTime.now();

                    List<String> overrideConfig = config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).getStringList("overrides");
                    overrideConfig.add("Administrator " + plr.getDisplayName() + " opend inventory of animal Owned by " + owner.getUniqueId());
                    config.getConfig(new File("petProtec" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).set("overrides", overrideConfig);
                    config.saveConfig();
                }
            }
        }
    }
}
