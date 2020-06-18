package Tools.Signage;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterSignage {

    private final LABMATT_SERVER_CONTROLLER plugin;

    public RegisterSignage(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerSignage() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("Signage")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Signage by LMSC is active on this server.");

            RenderSign renderSign = new RenderSign(plugin);
            renderSign.RenderNewSign();



            //plugin.getServer().getPluginManager().registerEvents(new EventPlayerChangeWorlds(plugin), plugin);
        }

    }
}
