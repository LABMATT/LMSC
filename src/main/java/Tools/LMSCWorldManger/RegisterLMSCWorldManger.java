package Tools.LMSCWorldManger;

import Tools.LMSCWorldManger.InventoryManger.EventPlayerChangeWorlds;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterLMSCWorldManger {

    private final LABMATT_SERVER_CONTROLLER plugin;

    public RegisterLMSCWorldManger(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerLMSCWorldManger() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("regWorld")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "World Manger by LMSC is active on this server.");
            new WorldCommands(plugin);

            plugin.getServer().getPluginManager().registerEvents(new EventPlayerTeloport(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new EventPlayerChangeWorlds(plugin), plugin);
        }

    }
}
