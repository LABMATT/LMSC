/*
If a player brakes a block. log it to the handeler file.
 */

package Tools.Handler;

import SubActions.Logout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Block_Break_Event implements Listener  {

    public LABMATT_SERVER_CONTROLLER plugin;

    Block_Break_Event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockBroken(BlockBreakEvent event) {
        Logout log = new Logout(plugin);

        new Handler_SQlite_Manger(plugin);

    }
}
