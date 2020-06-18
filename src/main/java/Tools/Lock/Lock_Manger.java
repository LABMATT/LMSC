/*
This file is designed to be a main refrence point for all lock functions to be grabed from. insted of including all the functions you can just include lock functions.
 */

package Tools.Lock;

import org.bukkit.Location;
import org.bukkit.block.Block;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Set;

public class Lock_Manger {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Manger(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    //Removes or Adds items to the lock config.
    //False deletes from config
    boolean editItem(String Item, boolean add) {
        Lock_Edit_Locked_Items edit = new Lock_Edit_Locked_Items(plugin);
        return edit.editItem(Item, add);
    }

    //Gets a Set<String> of the players that are able to use that item.
    Set<String> getPlayers(String Item) {
        Lock_Get_Players plrs = new Lock_Get_Players(plugin);
        return plrs.getPlayers(Item);
    }

    //Returns true if player is apart of that locked item. False if they are not.
    boolean isPlayerApartOf(String Item, String PlayerName) {
        Lock_Get_Players plrs = new Lock_Get_Players(plugin);
        return plrs.isPlayerApartOf(Item, PlayerName);
    }


    //Gets A string array of all the locked items in that chunk.
    String[] getLockedItems(Location BlockLoc) {
        Lock_Get_Locked_Items lockedItems = new Lock_Get_Locked_Items(plugin);
        return lockedItems.getLockedItems(BlockLoc);
    }


    //Checks if that block is locked to a player or team.
    Boolean isLocked(Block block) {
        Lock_Get_Locked_Items lockedItems = new Lock_Get_Locked_Items(plugin);
        return lockedItems.isLocked(block);
    }


    //When given block it returns its lock string.
    String getLockItem(Block block) {
        Lock_Get_Locked_Items lockedItems = new Lock_Get_Locked_Items(plugin);
        return lockedItems.getLockItem(block);
    }


    //Gets a String Array of all the blocks that can be locked.
    public String[] GetLockableBlocks() {
        Lock_Get_Block_Types lock_get_block_types = new Lock_Get_Block_Types(plugin);
        return lock_get_block_types.GetLockableBlocks();
    }


    //Reloads the lockablee blocks from the config into ram.
    public void loadLockablesFromConfig() {
        Lock_Get_Block_Types lock_get_block_types = new Lock_Get_Block_Types(plugin);
        lock_get_block_types.loadLockablesFromConfig();
    }


    //Checks if that block type is lockable.
    boolean isBlockType(String Type) {
        Lock_Get_Block_Types lock_get_block_types = new Lock_Get_Block_Types(plugin);
        return lock_get_block_types.isBlockType(Type);
    }

    //Check on all sides for blocks that
    Block getSecondBlock(Block block) {
        Lock_Get_Second_Block lock_get_second_block = new Lock_Get_Second_Block(plugin);
        return lock_get_second_block.getSecondBlock(block);
    }
}