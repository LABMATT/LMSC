/*
This Class is in charge of hammer efffects on diamond pickaxes.
When a player is mining using and diamond pickaxe and they are sneeking they destory a 9 block area face.
 */

package Tools.Hammer;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;


public class EventHammerMiner implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    EventHammerMiner(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    private Location[] surroundingBlocks = new Location[8];

    private Player plr;
    private Block centerBlock;

    @EventHandler
    public void onInteract(BlockBreakEvent event) {

        HammerTransport getPlrFace = new HammerTransport(plugin);
        HammerToggle hamTog = new HammerToggle(plugin);
        Logout log = new Logout(plugin);
        plr = event.getPlayer();
        centerBlock = event.getBlock();

        //Checks if player is not sneaking. If they are then temporally disable hammer.
        if (!event.getPlayer().isSneaking()) {

            //If player is holding axe or shovel and has hammer on then trigger hammer event.
            if ((event.getPlayer().getInventory().getItemInMainHand().toString().contains("_PICKAXE") || event.getPlayer().getInventory().getItemInMainHand().toString().contains("SHOVEL")) && hamTog.plrActiveHammer(plr)) {

                String[] clicked = getPlrFace.getTransportData();

                //Gets the events block.
                String blocLoc = event.getBlock().getX() + "|" + event.getBlock().getY() + "|" + event.getBlock().getZ() + "|" + event.getBlock().getType().toString() + "|" + event.getBlock().getWorld().getName();

                log.debugOut("Hammer", ChatColor.AQUA, "Hammer -------------------");


                //The string array contains 3 parts of the transport list value.
                //Block location : Player name : Face
                String[] confirmed = new String[2];

                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "HammerTransport Array info was received as: " + Arrays.toString(clicked));

                //Confimes that the data in the transport array is the same as the data of the block the player just broke.
                boolean metaMatch = false;


                for (String clickedLoop : clicked) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "HammerTransport Array artifact is " + clickedLoop);

                    if (clickedLoop.toLowerCase().contains(event.getPlayer().getDisplayName().toLowerCase()) && clickedLoop.toLowerCase().contains(blocLoc.toLowerCase())) {
                        confirmed = clickedLoop.split(":");
                        getPlrFace.removeTransportData(clickedLoop);
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Found block broken in transport array. The block info have been split and removed from array.");
                    }
                    if (clickedLoop.toLowerCase().contains(event.getPlayer().getDisplayName().toLowerCase())) {
                        getPlrFace.removeTransportData(clickedLoop);
                    }
                }


                if (confirmed[0] != null && confirmed[1] != null) {

                    log.debugOut(getClass().getName(), ChatColor.AQUA, "Split block info form block array is: " + Arrays.toString(confirmed));

                    //Check if its the right player
                    if (confirmed[1].contains(event.getPlayer().getDisplayName())) {

                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "The event bock location is: " + blocLoc);

                        //If array block location matchs the events block then confime the match for block brake.
                        if (confirmed[0].contains(blocLoc)) {
                            metaMatch = true;
                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Location found in array is the same as the location of the broken block.");
                        } else {
                            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Location found in array is not the same as the location of the broken block.");
                        }

                    }

                    Block block = event.getBlock();

                    if (metaMatch) {

                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player broke a block using a pickaxe and hammer enabled.");

                        String face = confirmed[2];

                        //If the players facing the UP or DOWN face of a block.
                        if (face.contains("UP") || face.contains("DOWN")) {
                            surroundingBlocks[0] = new Location(block.getWorld(), block.getX() - 1, block.getY(), block.getZ() + 1);
                            surroundingBlocks[1] = new Location(block.getWorld(), block.getX() - 1, block.getY(), block.getZ());
                            surroundingBlocks[2] = new Location(block.getWorld(), block.getX() + 1, block.getY(), block.getZ() - 1);

                            surroundingBlocks[3] = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() + 1);
                            surroundingBlocks[4] = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() - 1);

                            surroundingBlocks[5] = new Location(block.getWorld(), block.getX() + 1, block.getY(), block.getZ() + 1);
                            surroundingBlocks[6] = new Location(block.getWorld(), block.getX() + 1, block.getY(), block.getZ());
                            surroundingBlocks[7] = new Location(block.getWorld(), block.getX() - 1, block.getY(), block.getZ() - 1);

                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Block faceing UP or DOWN with locations of surrounding: " + surroundingBlocks[0] + " " + surroundingBlocks[2] + " " + surroundingBlocks[2] + " " + surroundingBlocks[3] + " " + surroundingBlocks[4] + " " + surroundingBlocks[5] + " " + surroundingBlocks[6] + " " + surroundingBlocks[7]);


                            breakBlocks();
                        }

                        //If the players facing NORTH or SOUTH face of a block.
                        if (face.contains("NORTH") || face.contains("SOUTH")) {
                            surroundingBlocks[0] = new Location(block.getWorld(), block.getX() + 1, block.getY() + 1, block.getZ());
                            surroundingBlocks[1] = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ());
                            surroundingBlocks[2] = new Location(block.getWorld(), block.getX() - 1, block.getY() + 1, block.getZ());

                            surroundingBlocks[3] = new Location(block.getWorld(), block.getX() + 1, block.getY(), block.getZ());
                            surroundingBlocks[4] = new Location(block.getWorld(), block.getX() - 1, block.getY(), block.getZ());

                            surroundingBlocks[5] = new Location(block.getWorld(), block.getX() + 1, block.getY() - 1, block.getZ());
                            surroundingBlocks[6] = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ());
                            surroundingBlocks[7] = new Location(block.getWorld(), block.getX() - 1, block.getY() - 1, block.getZ());
                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Block facing NORTH or SOUTH with locations of surrounding: " + surroundingBlocks[0] + " " + surroundingBlocks[2] + " " + surroundingBlocks[2] + " " + surroundingBlocks[3] + " " + surroundingBlocks[4] + " " + surroundingBlocks[5] + " " + surroundingBlocks[6] + " " + surroundingBlocks[7]);


                            breakBlocks();
                        }

                        //If the players facing the WEST or EAST face of the block.
                        if (face.contains("WEST") || face.contains("EAST")) {
                            surroundingBlocks[0] = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ() + 1);
                            surroundingBlocks[1] = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ());
                            surroundingBlocks[2] = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ() - 1);

                            surroundingBlocks[3] = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() + 1);
                            surroundingBlocks[4] = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ() - 1);

                            surroundingBlocks[5] = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ() + 1);
                            surroundingBlocks[6] = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ());
                            surroundingBlocks[7] = new Location(block.getWorld(), block.getX(), block.getY() - 1, block.getZ() - 1);
                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Block facing EAST or WEST with locations of surrounding: " + surroundingBlocks[0] + " " + surroundingBlocks[2] + " " + surroundingBlocks[2] + " " + surroundingBlocks[3] + " " + surroundingBlocks[4] + " " + surroundingBlocks[5] + " " + surroundingBlocks[6] + " " + surroundingBlocks[7]);

                            breakBlocks();
                        }
                    }
                } else {
                    log.debugOut("Hammer", ChatColor.DARK_RED, "Split block loc was null");
                }
            }
        }
    }

    private void breakBlocks() {
        Logout log = new Logout(plugin);
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Braking sounding blocks and changing damage.");

        int hammerDamage = 0;

        for (Location preformBrake : surroundingBlocks) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Attempting to brake block: " + preformBrake.getBlock().getType());

            if (preformBrake.getBlock().getBlockData().getMaterial().getHardness() == centerBlock.getBlockData().getMaterial().getHardness() || preformBrake.getBlock().getType().toString().toLowerCase().contains("ore")) {
                if (preformBrake.getBlock().getType() != Material.BEDROCK && preformBrake.getBlock().getType() != Material.AIR) {
                    preformBrake.getBlock().breakNaturally(plr.getInventory().getItemInMainHand());
                    hammerDamage++;
                }
            }
        }


        //Damage the pick axe
        ItemMeta picMeta = plr.getInventory().getItemInMainHand().getItemMeta();
        assert picMeta != null;
        int dam = ((Damageable) picMeta).getDamage();
        ((Damageable) picMeta).setDamage(dam + hammerDamage);

        log.debugOut(getClass().getName(), ChatColor.AQUA, "Hammer Damage <" + (dam + hammerDamage) + "> ");
        plr.getInventory().getItemInMainHand().setItemMeta(picMeta);
    }
}


