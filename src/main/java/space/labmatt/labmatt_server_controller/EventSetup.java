package space.labmatt.labmatt_server_controller;

import EventPackage.StormBreakerEvent;

class EventSetup {

    private LABMATT_SERVER_CONTROLLER plugin;

    EventSetup(LABMATT_SERVER_CONTROLLER labmatt_server_controller) {
        this.plugin = labmatt_server_controller;
    }

    void eventReg() {
        plugin.getServer().getPluginManager().registerEvents(new StormBreakerEvent(plugin), plugin);
    }
}
