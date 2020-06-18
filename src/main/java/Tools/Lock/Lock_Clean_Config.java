package Tools.Lock;

/*
This FIle is used on startup and by command to get all locked items from config and check to make sure hte block is in its rightfull spot.
 */

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lock_Clean_Config {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Lock_Clean_Config(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void preformClean() {
        setDefultLockType();
        legacyConversion();
    }


    //This gets all data from the old config and converts it to the new data format.
    private void legacyConversion() {
        Lock_Edit_Locked_Items edit = new Lock_Edit_Locked_Items(plugin);
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking for legacy Lock file.");

        if (config.isFile(new File("locked"))) {

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Legacy lock file found.");

            List<String> locItems = config.getConfig(new File("locked")).getStringList("Lock." + "locked");
            List<String> removeOldEntrys = new ArrayList<>();

            for (String focuslock : locItems) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Converting <" + focuslock + "> to new config.");

                String newFocuslock = focuslock.replace("|", " ");
                if (edit.editItem(newFocuslock, true)) {
                    removeOldEntrys.add(focuslock);
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Removed item <" + focuslock + "> from old config.");
                } else {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Was unable to write <" + focuslock + "> to new config. Lock_Edit_Locked_Items retunred false.");
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Failed to convert <" + focuslock + "> to new locking format.");
                }
            }

            locItems.removeAll(removeOldEntrys);


            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Lagacy Cleanup complete! remaining files in old lock config are <" + Arrays.toString(locItems.toArray()) + ">. If you wish to keep remaining items in the legacy file please copy them before shutting down the server. locked.yml will be deleted.");

            config.getConfig(new File("locked"));
            config.deleteFile();


            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Saved Old config.");
        } else {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "No Legacy lock file found.");
        }
    }


    private void setDefultLockType() {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(this.plugin);

        String[] defaultLockItems = new String[]{"IRON_DOOR", "OAK_DOOR", "SPRUCE_DOOR", "BIRCH_DOOR", "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR", "IRON_TRAPDOOR", "OAK_TRAPDOOR", "SPRUCE_TRAPDOOR", "BIRCH_TRAPDOOR", "JUNGLE_TRAPDOOR", "ACACIA_TRAPDOOR", "DARK_OAK_TRAPDOOR", "OAK_FENCE_GATE", "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "JUNGLE_FENCE_GATE", "ACACIA_FENCE_GATE", "DARK_OAK_FENCE_GATE", "OAK_BUTTON", "SPRUCE_BUTTON", "BIRCH_BUTTON", "JUNGLE_BUTTON", "ACACIA_BUTTON", "DARK_OAK_BUTTON", "CHEST", "TRAPPED_CHEST", "ENDER_CHEST", "BARREL", "SHULKER_BOX", "WHITE_SHULKER_BOX", "ORANGE_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "YELLOW_SHULKER_BOX", "LIME_SHULKER_BOX", "PINK_SHULKER_BOX", "GRAY_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX", "CYAN_SHULKER_BOX", "PURPLE_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX", "GREEN_SHULKER_BOX", "RED_SHULKER_BOX", "BLACK_SHULKER_BOX", "WHITE_BED", "ORANGE_BED", "MAGENTA_BED", "LIGHT_BLUE_BED", "YELLOW_BED", "LIME_BED", "PINK_BED", "GRAY_BED", "LIGHT_GRAY_BED", "PURPLE_BED", "CYAN_BED", "BLUE_BED", "BROWN_BED", "GREEN_BED", "RED_BED", "BLACK_BED", "REPEATER", "COMPARATOR", "TNT", "SMITHING_TABLE", "JUKEBOX", "CAMPFIRE", "BREWING_STAND", "STONECUTTER", "GRINDSTONE", "CHIPPED_ANVIL", "LOOM", "BLAST_FURNACE", "ENDER_CHEST", "FURNACE", "DROPPER", "NOTE_BLOCK", "DISPENSER", "HOPPER", "BARREL", "CARTOGRAPHY_TABLE", "SMOKER", "DAMAGED_ANVIL", "ANVIL", "FLETCHING_TABLE", "CAULDRON", "COMPOSTER", "ENCHANTING_TABLE", "BEACON", "BELL", "LECTERN", "HOPPER", "LEVER", "OAK_PRESSURE_PLATE", "STONE_PRESSURE_PLATE", "SPRUCE_PRESSURE_PLATE", "BIRCH_PRESSURE_PLATE", "JUNGLE_PRESSURE_PLATE", "ACACIA_PRESSURE_PLATE", "DARK_OAK_PRESSURE_PLATE", "LIGHT_WEIGHTED_PRESSURE_PLATE", "HEAVY_WEIGHTED_PRESSURE_PLATE", "STONE_BUTTON"};

        if (!config.isFile(new File("lockdata" + File.separator + "BlockTypes"))) {
            config.getConfig(new File("lockdata" + File.separator + "BlockTypes")).set("BlockTypes", defaultLockItems);
            config.saveConfig();
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Lock_Clean_Config: NO LOCKABLE BLOCKS FOUND IN CONFIG. RESTING TO DEFAULT!");
        }
    }
}
