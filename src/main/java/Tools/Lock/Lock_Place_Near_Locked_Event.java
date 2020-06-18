/*
This class prevents players that are not the owner of the block from placing blocks near a locked block.
 */

package Tools.Lock;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Lock_Place_Near_Locked_Event implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    public Lock_Place_Near_Locked_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Lock_Get_Block_Types locblc = new Lock_Get_Block_Types(plugin);

        Logout log = new Logout(plugin);

        String[] locabalblocks = locblc.GetLockableBlocks();


        Location up = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY() + 1, event.getBlock().getLocation().getZ());
        Location down = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY() - 1, event.getBlock().getLocation().getZ());

        Location Xplus = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX() + 1, event.getBlock().getLocation().getY(), event.getBlock().getLocation().getZ());
        Location Xminus = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX() - 1, event.getBlock().getLocation().getY(), event.getBlock().getLocation().getZ());

        Location Zplus = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY(), event.getBlock().getLocation().getZ() + 1);
        Location Zminus = new Location(event.getBlock().getLocation().getWorld(), event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY(), event.getBlock().getLocation().getZ() - 1);

        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc up is <" + up + "> Block <" + up.getBlock().getType().toString() + ">");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc down is " + down + "> Block <" + down.getBlock().getType().toString() + ">");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc Xplus is " + Xplus + "> Block <" + Xplus.getBlock().getType().toString() + ">");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc Xminus is " + Xminus + "> Block <" + Xminus.getBlock().getType().toString() + ">");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc Zplus is " + Zplus + "> Block <" + Zplus.getBlock().getType().toString() + ">");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Loc Zminus is " + Zminus + "> Block <" + Zminus.getBlock().getType().toString() + ">");

        //Check if the blocks sourrounding it are locked.
        if (Arrays.toString(locabalblocks).contains(up.getBlock().getType().toString())) {
            checkOwnership(up, event);
        }
        if (Arrays.toString(locabalblocks).contains(down.getBlock().getType().toString())) {
            checkOwnership(down, event);
        }
        if (Arrays.toString(locabalblocks).contains(Xplus.getBlock().getType().toString())) {
            checkOwnership(Xplus, event);
        }
        if (Arrays.toString(locabalblocks).contains(Xminus.getBlock().getType().toString())) {
            checkOwnership(Xminus, event);
        }
        if (Arrays.toString(locabalblocks).contains(Zplus.getBlock().getType().toString())) {
            checkOwnership(Zplus, event);
        }
        if (Arrays.toString(locabalblocks).contains(Zminus.getBlock().getType().toString())) {
            checkOwnership(Zminus, event);
        }


    }

    private void checkOwnership(Location localLoc, BlockPlaceEvent event) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);
        Lock_Get_Players lockGetPlayers = new Lock_Get_Players(plugin);
        Lock_Create_Override lock_create_override = new Lock_Create_Override(plugin);

        List<String> lockedList = config.getConfig(new File("lockdata" + File.separator + "chunks" + File.separator + localLoc.getBlock().getChunk().toString())).getStringList(localLoc.getBlock().getWorld().getName() + "." + "locked");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "IN check onwership");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Local loc" + localLoc);

        World world = localLoc.getWorld();

        String checklocked = "";

        if (world != null) {
            checklocked = localLoc.getBlock().getX() + " " + localLoc.getBlock().getY() + " " + localLoc.getBlock().getZ() + " " + localLoc.getBlock().getType().toString() + " " + world.getName();

        } else {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "There was a fatel error in the locking system :(");
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "There was a fatel error in the locking system :(. Lock_Events was unable to get the world of a chest after a player clicked on it.");
            event.setCancelled(true);
        }

        for (String locItem : lockedList) {

            if (locItem.contains(checklocked)) {

                if (lockGetPlayers.isPlayerApartOf(locItem, event.getPlayer().getDisplayName())) {
                    event.setCancelled(false);
                    break;

                } else {
                    if (!event.getPlayer().isOp()) {

                        plugin.getServer().getConsoleSender().sendMessage("Player" + event.getPlayer().getDisplayName() + " was just blocked form placing a block surrounding " + locItem + ".");
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "This Item is locked by another Player or Faction.");
                    }
                    if (event.getPlayer().isOp()) {

                        event.getPlayer().sendMessage(ChatColor.AQUA + "Overridden lock with administrator privileges. Your Activity will be logged.");

                        lock_create_override.createOveride(event.getPlayer(), "Administrator " + event.getPlayer().getDisplayName() + " placed blocks surrounding locked item " + locItem + ".");

                        break;
                    }
                }
            }
        }

    }
}
