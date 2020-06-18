/*
This class prevents blocks that are locked from been broken by players that are not there owner.

 */

package Tools.Lock;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Break_Event implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Break_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onInteract(BlockBreakEvent event) {

        Block block = event.getBlock();
        Lock_Get_Block_Types lockables = new Lock_Get_Block_Types(plugin);
        Lock_Get_Locked_Items getLocked = new Lock_Get_Locked_Items(plugin);
        Lock_Get_Players lockGetPlayers = new Lock_Get_Players(plugin);
        Lock_Create_Override lock_create_override = new Lock_Create_Override(plugin);

        if (lockables.isBlockType(event.getBlock().getType().toString())) {

            if (getLocked.isLocked(block)) {
                String item = getLocked.getLockItem(block);

                if (!lockGetPlayers.isPlayerApartOf(item, event.getPlayer().getDisplayName())) {
                    ConfigManger config = new ConfigManger(plugin);

                    //If the player is op then brodcast a server wide message saying they frocly opned a chest.
                    if (!event.getPlayer().isOp()) {

                        plugin.getServer().getConsoleSender().sendMessage("Player" + event.getPlayer().getDisplayName() + " was just blocked from breaking item " + item + ".");
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "This Item is locked by another Player or Faction.");
                    } else if (event.getPlayer().isOp()) {

                        event.getPlayer().sendMessage(ChatColor.AQUA + "Overridden lock with administrator privileges. Your Activity will be logged.");

                        lock_create_override.createOveride(event.getPlayer(), "Administrator " + event.getPlayer().getDisplayName() + " broke locked item " + item + ".");

                        Lock_Manger lock_manger = new Lock_Manger(plugin);
                        lock_manger.editItem(item, false);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Item unlocked.");
                    }
                } else {
                    Lock_Manger lock_manger = new Lock_Manger(plugin);
                    lock_manger.editItem(item, false);

                    if(block.getType().toString().toLowerCase().contains("door") || block.getType().toString().toLowerCase().contains("bed"))
                    {
                        lock_manger.editItem(lock_manger.getLockItem(lock_manger.getSecondBlock(block)), false);
                    }

                    event.getPlayer().sendMessage(ChatColor.GREEN + "Item unlocked.");
                }
            }
        }
    }
}


