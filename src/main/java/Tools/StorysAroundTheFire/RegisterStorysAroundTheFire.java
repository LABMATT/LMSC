package Tools.StorysAroundTheFire;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterStorysAroundTheFire {

    private final LABMATT_SERVER_CONTROLLER plugin;

    public RegisterStorysAroundTheFire(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerLMSCWorldManger() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("StorysAroundTheFire")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Storys Around The Fire by LMSC is active on this server.");



            //plugin.getServer().getPluginManager().registerEvents(new EventPlayerChangeWorlds(plugin), plugin);
        }

    }
}
