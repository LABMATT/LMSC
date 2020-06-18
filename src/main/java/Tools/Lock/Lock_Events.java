/*
On player interaction with an item that can be locked.
Check if the item is locked.
If it is then check if the player has ownership of it. If they dont then block them from using it.
 */

package Tools.Lock;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Events implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Events(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        //Allows use of actions and player events.
        Lock_Manger lock_manger = new Lock_Manger(plugin);
        Lock_Create_Override lock_create_override = new Lock_Create_Override(plugin);

        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null) {

            if (lock_manger.isBlockType(clickedBlock.getType().toString())) {

                if (lock_manger.isLocked(clickedBlock)) {

                    String lockedBlock = lock_manger.getLockItem(clickedBlock);

                    if (!lock_manger.isPlayerApartOf(lockedBlock, event.getPlayer().getDisplayName())) {

                        if (!event.getPlayer().isOp()) {

                            plugin.getServer().getConsoleSender().sendMessage("Player" + event.getPlayer().getDisplayName() + " was just blocked from accessing item " + lockedBlock + ".");
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "This Item is locked by another Player or Faction.");
                        }
                        if (event.getPlayer().isOp()) {

                            lock_create_override.createOveride(event.getPlayer(), "Administrator " + event.getPlayer().getDisplayName() + " clicked locked item " + lock_manger.getLockItem(clickedBlock) + ".");
                        }
                    }
                }
            }
        }
    }
}
