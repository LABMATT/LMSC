package Tools.CommandBlockFinder;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.ServerCommandEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class LogCommandBlockEvent implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public LogCommandBlockEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cmbEvent(BlockEvent event) {
        Logout log = new Logout(plugin);

        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "Command Dected: " + event.getBlock().getType().toString());

    }
}
