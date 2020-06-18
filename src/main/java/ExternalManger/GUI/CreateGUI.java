package ExternalManger.GUI;

import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class CreateGUI {

    private LABMATT_SERVER_CONTROLLER plugin;

    public CreateGUI(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    public void createNewGUI() {
        if (plugin.getConfig().getBoolean("gui")) {
            GUI gui = new GUI();
            gui.crateGUI();
        }
    }
}
