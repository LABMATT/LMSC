package Tools.Lock;

import Mangers.ConfigManger;
import org.bukkit.Location;
import org.bukkit.block.Block;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class Lock_Get_Locked_Items {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Lock_Get_Locked_Items(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    static private String[] locked;

    //Gets all the locked Items in that chunk.
    String[] getLockedItems(Location blockLoc) {
       // Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        //log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Getting locked blocks: " + blockLoc);
        List<String> lockedList = config.getConfig(new File("lockdata" + File.separator + "chunks" + File.separator + blockLoc.getBlock().getChunk().toString())).getStringList(blockLoc.getBlock().getWorld().getName() + "." + "locked");

        locked = lockedList.toArray(new String[0]);

        return locked;
    }


    Boolean isLocked(Block block) {
        Location BlockLocation = block.getLocation();
        getLockedItems(BlockLocation);

        String key = (int) BlockLocation.getX() + " " + (int) BlockLocation.getY() + " " + (int) BlockLocation.getZ() + " " + block.getType().toString() + " " + block.getWorld().getName();

        for (String rule : locked) {
            if (rule.contains(key)) {
                return true;
            }
        }
        return false;
    }


    String getLockItem(Block block) {
        Location BlockLocation = block.getLocation();
        getLockedItems(BlockLocation);

        String key = (int) BlockLocation.getX() + " " + (int) BlockLocation.getY() + " " + (int) BlockLocation.getZ() + " " + block.getType().toString() + " " + block.getWorld().getName();

        for (String rule : locked) {
            if (rule.contains(key)) {
                return rule;
            }
        }
        return null;
    }
}

