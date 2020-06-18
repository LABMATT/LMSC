package space.labmatt.labmatt_server_controller;

class ConfigSetup {

    private LABMATT_SERVER_CONTROLLER plugin;

    ConfigSetup(LABMATT_SERVER_CONTROLLER labmatt_server_controller) {
        this.plugin = labmatt_server_controller;
    }

    void Config() {

        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
}
