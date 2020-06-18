package Tools.LMSC_Personalisation;

import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Register_LMSC_Rersonalisation {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Register_LMSC_Rersonalisation(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void regPersonalisation() {
        Logout log = new Logout(plugin);

        if (plugin.getConfig().getBoolean("lmscspice")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Rersonalisation by LMSC is active on this server.");
            plugin.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(plugin), plugin);

            PlayerJoinEvent regMessage = new PlayerJoinEvent(plugin);

            regMessage.setupJoinmessage();

        }
    }
}
