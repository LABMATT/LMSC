package Tools.PetProtec;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class UnTameEvent implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;
    private Player plr;

    UnTameEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    //Checks if a player has damged a mob. If they have then see if there holding a stick named "untag". If they are and there op then unname tag the mob.
    @EventHandler
    public void unTagEntityEvent(EntityDamageByEntityEvent event) {
        {
            if (event.getDamager() instanceof Player) {
                plr = ((Player) event.getDamager()).getPlayer();
                assert plr != null;
                if (plr.isOp()) {
                    if (plr.getInventory().getItemInMainHand().getType() == Material.STICK) {
                        if (Objects.requireNonNull(plr.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().toLowerCase().contains("untame")) {
                            switch (event.getEntityType().toString()) {
                                //If its a wolf
                                case "WOLF":
                                    Entity wolf = event.getEntity();
                                    Wolf wf = ((Wolf) wolf);

                                    if (wf.isTamed()) {
                                        sendOwnerMessage(wf.getOwner(), event);
                                        wf.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;

                                //If its a horse
                                case "HORSE":
                                    Entity horse = event.getEntity();
                                    Horse he = ((Horse) horse);

                                    if (he.isTamed()) {
                                        sendOwnerMessage(he.getOwner(), event);
                                        he.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;

                                //If its a parrot
                                case "PARROT":
                                    Entity parrot = event.getEntity();
                                    Parrot pt = ((Parrot) parrot);

                                    if (pt.isTamed()) {
                                        sendOwnerMessage(pt.getOwner(), event);
                                        pt.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;

                                //If its a Donkey
                                case "DONKEY":
                                    Entity donkey = event.getEntity();
                                    Donkey dy = ((Donkey) donkey);

                                    if (dy.isTamed()) {
                                        sendOwnerMessage(dy.getOwner(), event);
                                        dy.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;

                                //If its a mule
                                case "MULE":
                                    Entity mule = event.getEntity();
                                    Mule me = ((Mule) mule);

                                    if (me.isTamed()) {
                                        sendOwnerMessage(me.getOwner(), event);
                                        me.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;

                                //If its a llama
                                case "LLAMA":
                                    Entity llama = event.getEntity();
                                    Llama la = ((Llama) llama);

                                    if (la.isTamed()) {
                                        sendOwnerMessage(la.getOwner(), event);
                                        la.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;
                                case "CAT":
                                    Entity cat = event.getEntity();
                                    Cat ct = ((Cat) cat);

                                    if (ct.isTamed()) {
                                        sendOwnerMessage(ct.getOwner(), event);
                                        ct.setOwner(null);
                                        event.setCancelled(true);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void sendOwnerMessage(AnimalTamer owner, EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("petprotecuntame")) {

            if (owner.getName() != null) {
                plugin.getServer().getPlayer(owner.getName()).sendMessage(ChatColor.LIGHT_PURPLE + "Your " + event.getEntityType().toString().toLowerCase() + " was untamed by " + plr.getDisplayName());
            }
        }
    }
}
