package Tools.InventorySnooper;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterInventorySnooper {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterInventorySnooper(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerInventorySnooper() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("invsnoop")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Inventory Snooper by LMSC is active on this server.");
            new InventorySnooperCommand(plugin);
        }

    }
}
