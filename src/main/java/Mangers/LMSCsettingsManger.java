package Mangers;

import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class LMSCsettingsManger {

    private LABMATT_SERVER_CONTROLLER plugin;

    public LMSCsettingsManger(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    public boolean getSetting(String settingName) {
        if (!plugin.getConfig().getBoolean(settingName)) {
            setSetting(settingName, false);
        }
        return plugin.getConfig().getBoolean(settingName);
    }

    private void setSetting(String settingName, boolean value) {
        plugin.getConfig().set(settingName, value);
        plugin.saveConfig();
    }
}
