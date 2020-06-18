package Tools.CustomGameRule;

import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterCustomGameRules {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterCustomGameRules(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerCustomGameRules() {
        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("custom_game_rules")) {

            plugin.getServer().getPluginManager().registerEvents(new Event_Creeper_Explode(plugin), plugin);


        }
    }

}
