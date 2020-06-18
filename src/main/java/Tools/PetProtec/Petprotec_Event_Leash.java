package Tools.PetProtec;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Petprotec_Event_Leash implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Petprotec_Event_Leash(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void entityInteractLock(PlayerLeashEntityEvent event) {

        Player plr = event.getPlayer();

        AnimalTamer owner = null;

        if (event.getEntity() instanceof Horse) {
            Horse horse = (Horse) event.getEntity();
            owner = horse.getOwner();
        } else if (event.getEntity() instanceof Donkey) {
            Donkey horse = (Donkey) event.getEntity();
            owner = horse.getOwner();
        } else if (event.getEntity() instanceof Llama) {
            Llama horse = (Llama) event.getEntity();
            owner = horse.getOwner();
        }
        else if (event.getEntity() instanceof Mule) {
            Mule horse = (Mule) event.getEntity();
            owner = horse.getOwner();
        } else if(event.getEntity() instanceof Wolf)
        {
            Wolf wolf = (Wolf) event.getEntity();
            owner = wolf.getOwner();
        }else if(event.getEntity() instanceof Parrot)
        {
            Parrot parrot = (Parrot) event.getEntity();
            owner = parrot.getOwner();
        }else if(event.getEntity() instanceof Cat)
        {
             Cat cat = (Cat) event.getEntity();
            owner = cat.getOwner();
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
