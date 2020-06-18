package Tools.PetProtec;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PetProtecEvent implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    PetProtecEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    private static Map<String, String> damageRepotPre = new HashMap<>();

    @EventHandler
    public void petDeathEvent(EntityDamageEvent event) {
        switch (event.getEntityType().toString()) {
            //If its a wolf
            case "WOLF":
                Entity wolf = event.getEntity();
                Wolf wf = ((Wolf) wolf);

                if (wf.isTamed()) {
                    sendOwnerMessage(wf.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its a horse
            case "HORSE":
                Entity horse = event.getEntity();
                Horse he = ((Horse) horse);

                if (he.isTamed()) {
                    sendOwnerMessage(he.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its a parrot
            case "PARROT":
                Entity parrot = event.getEntity();
                Parrot pt = ((Parrot) parrot);

                if (pt.isTamed()) {
                    sendOwnerMessage(pt.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its a Donkey
            case "DONKEY":
                Entity donkey = event.getEntity();
                Donkey dy = ((Donkey) donkey);

                if (dy.isTamed()) {
                    sendOwnerMessage(dy.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its a mule
            case "MULE":
                Entity mule = event.getEntity();
                Mule me = ((Mule) mule);

                if (me.isTamed()) {
                    sendOwnerMessage(me.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its a llama
            case "LLAMA":
                Entity llama = event.getEntity();
                Llama la = ((Llama) llama);

                if (la.isTamed()) {
                    sendOwnerMessage(la.getOwner(), event);
                    event.setCancelled(true);
                }
                break;
            case "CAT":
                Entity cat = event.getEntity();
                Cat ct = ((Cat) cat);

                if (ct.isTamed()) {
                    sendOwnerMessage(ct.getOwner(), event);
                    event.setCancelled(true);
                }
                break;

            //If its not a tambed mob then check if its got a name tag. if it does then cancel event
            default:
                if (event.getEntity().getCustomName() != null && !event.getEntityType().toString().contains("ENDER_DRAGON") && !event.getEntityType().toString().contains("WITHER")) {
                    event.setCancelled(true);
                }
                break;
        }
    }

    private void sendOwnerMessage(AnimalTamer owner, EntityDamageEvent event) {
        if (plugin.getConfig().getBoolean("petprotecreport")) {

            playerRemoveMap();

            if (owner != null) {
                String stringOwner = owner.getName();

                if (stringOwner != null) {
                    Player plr = plugin.getServer().getPlayer(stringOwner);

                    String cause = event.getCause().toString().toLowerCase().replace("_tick", "").replace("_attack", "");
                    String rawCause = event.getCause().toString().replace("_TICK", "");


                    if (plr != null) {

                        String damgeEventAnimal = plr.getUniqueId() + "-" + event.getEntity().getEntityId();

                        if (damageRepotPre.containsKey(damgeEventAnimal)) {

                            if (!damageRepotPre.get(damgeEventAnimal).contains(rawCause)) {
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "Your " + event.getEntityType().toString().toLowerCase() + " was protected from " + cause + ".");
                                damageRepotPre.put(damgeEventAnimal, rawCause);
                            }
                        } else {
                            plr.sendMessage(ChatColor.LIGHT_PURPLE + "Your " + event.getEntityType().toString().toLowerCase() + " was protected from " + cause + ".");
                            damageRepotPre.put(damgeEventAnimal, rawCause);
                        }

                        System.out.println(damageRepotPre);
                    }
                }
            }
        }
    }

    private void playerRemoveMap() {
        PetProtec_Transport petProtec_transport = new PetProtec_Transport();
        Set<String> removeName = petProtec_transport.getSet();

        Set<String> blacklistRemove = new HashSet<>();

        for (String name : removeName) {

            for (String key : damageRepotPre.keySet()) {

                System.out.println("key is " + key);

                if (key.contains(name)) {

                    System.out.println("key found. removing");
                    blacklistRemove.add(key);
                    petProtec_transport.removeName(name);
                }
            }
        }

        for (String remove : blacklistRemove) {

            damageRepotPre.remove(remove);
        }
    }

}

