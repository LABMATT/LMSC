package Tools.LogStrip;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Block_Prevent_Log_Strip implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Block_Prevent_Log_Strip(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void logStrip(PlayerInteractEvent event) {
        Logout log = new Logout(plugin);

        Player plr = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getMaterial().toString().contains("_AXE")) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player clocked a block with axe.");

            if (event.getClickedBlock() != null) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "The block they clicked was not null.");

                if (event.getClickedBlock().toString().contains("LOG") || event.getClickedBlock().toString().contains("_WOOD")) {
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "The block was log or wood.");

                    event.setCancelled(true);
                    plr.sendMessage(ChatColor.DARK_RED + "Stripping Logs is a sin on this server.");
                   /* if(sendmessage)
                    {
                        plr.sendMessage(ChatColor.DARK_RED + message);
                    }

                    */
                }
            }
        }
    }
}
