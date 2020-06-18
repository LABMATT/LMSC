/*
Edit locked items is designed to write and remove the locked aspects to the configs.
 */

package Tools.Lock;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

class Lock_Edit_Locked_Items {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Edit_Locked_Items(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }


    //Adds or Removes locked items form the config. If true then write file else remove the name given.
    boolean editItem(String item, boolean Write) {
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);
        Lock_Raw_Data raw = new Lock_Raw_Data();

        Location blockloc = raw.getData(item);

        if(blockloc == null)
        {
           return false;
        }

        List<String> lockedList = config.getConfig(new File("lockdata" + File.separator + "chunks" + File.separator + blockloc.getBlock().getChunk().toString())).getStringList(blockloc.getBlock().getWorld().getName() + "." + "locked");

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Editing item <" + item + ">.");

        //If Write is true then add an item. if it is false then remove and item from lock file.
        if (Write) {
            try {
                lockedList.add(item);
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Written item <" + item + "> to config.");
            } catch (NullPointerException e) {
                log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "There was an error WRITING item <" + item + "> to the config!");
                return false;
            }
        } else {
            try {
                lockedList.remove(item);
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Removed item <" + item + "> from config.");
            } catch (NullPointerException e) {
                log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "There was an error REMOVING item <" + item + "> from the config!");
                return false;
            }
        }
        config.getConfig(new File("lockdata" + File.separator + "chunks" + File.separator + blockloc.getBlock().getChunk().toString())).set(blockloc.getBlock().getWorld().getName() + "." + "locked", lockedList);
        config.saveConfig();
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Finished editing <" + item + ">.");

        return true;
    }
}

