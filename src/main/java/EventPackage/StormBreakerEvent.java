package EventPackage;

import Mangers.ConfigManger;
import Mangers.LMSCsettingsManger;
import Tools.Hammer.HammerTransport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.List;

public class StormBreakerEvent implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    public StormBreakerEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        LMSCsettingsManger setting = new LMSCsettingsManger(plugin);
        HammerTransport tran = new HammerTransport(plugin);
        ConfigManger config = new ConfigManger(plugin);

        try {
            if (event.getPlayer().getInventory().getItemInMainHand().toString().contains("_PICKAXE") || event.getPlayer().getInventory().getItemInMainHand().toString().contains("_SHOVEL")) {
                String blocLoc = event.getClickedBlock().getLocation().getBlockX() + "|" + event.getClickedBlock().getLocation().getBlockY() + "|" + event.getClickedBlock().getLocation().getBlockZ() + "|" + event.getClickedBlock().getType() + "|" + event.getClickedBlock().getWorld().getName();
                tran.addTransportData(blocLoc + ":" + event.getPlayer().getDisplayName() + ":" + event.getBlockFace().toString());
            }
        } catch (NullPointerException e) {

        }

        //Allows use of actions and player events.
        Action action = event.getAction();
        Player plr = event.getPlayer();

        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            if (plr.getInventory().getItemInMainHand().getType().toString().contains("DIAMOND_AXE")) {
                if (plr.getInventory().getItemInMainHand().getItemMeta().toString().toLowerCase().contains("stormbreaker") && plugin.getConfig().getBoolean("StormBreaker")) {

                    boolean prevent = true;
                    try {

                        if (config.getOldConfig("StormBreaker").getString("StormBreaker." + "Bans") == null) {
                            config.getOldConfig("StormBreaker").set("StormBreaker." + "Bans", " ");
                            config.saveConfig();
                        }

                        List<String> banPlayers = config.getOldConfig("Stormbreaker").getStringList("StormBreaker." + "Bans");


                        for (String var : banPlayers) {
                            if (var.toLowerCase().contains(plr.getDisplayName().toLowerCase())) {
                                prevent = false;
                                System.out.println(plr.getName() + " Prevented from using stormbraker.");
                            }

                        }
                    } catch (NullPointerException e) {
                        plugin.reloadConfig();
                        plugin.getConfig().set("StormBreaker." + "Bans", "EnterName");
                        plugin.saveConfig();
                    }


                    if (prevent) {

                        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Player " + plr.getName() + " Used storm breaker.");

                        plr.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 8, 15));
                        try {
                            Location plrTarget = plr.rayTraceBlocks(1000).getHitBlock().getLocation();
                            World world = plr.getWorld();
                            world.strikeLightning(plrTarget);
                        } catch (NullPointerException e) {
                            plr.sendMessage(ChatColor.BLACK + "LIGHTNING CANNOT FIGHT THE SKY MIGHTY ONE");
                        }
                    }

                }

            }
        }
    }
}

