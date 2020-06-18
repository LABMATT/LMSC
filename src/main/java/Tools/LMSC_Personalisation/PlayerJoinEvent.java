package Tools.LMSC_Personalisation;

import Mangers.ConfigManger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class PlayerJoinEvent implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public PlayerJoinEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        ConfigManger config = new ConfigManger(plugin);

        setupJoinmessage();

        try {

            String joinMessage = config.getOldConfig("LMSC-personalisation").getString("joinmessage").replace("<player>", event.getPlayer().getDisplayName());
            event.setJoinMessage(ChatColor.AQUA + joinMessage);
        } catch (NullPointerException e) {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to display join message because there was none to display!");
        }


    }

    void setupJoinmessage() {
        ConfigManger config = new ConfigManger(plugin);
        config.getConfig(new File("LMSC-personalisation" + File.separator + "LMSC-personalisation"));

        if (!config.getOldConfig("LMSC-personalisation").isString("joinmessage")) {
            config.getOldConfig("LMSC-personalisation").set("joinmessage", "Change this message in LMSC-personalisation config file then follow these instructions ---> .Enter Message to be displayed to players on join here. Use <player> to reference joined player.");
            config.saveConfig();
        }
    }
}
