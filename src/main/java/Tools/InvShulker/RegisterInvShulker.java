package Tools.InvShulker;

import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterInvShulker {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterInvShulker(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerShulkerInv() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("ShulkerInv")) {

            plugin.getServer().getPluginManager().registerEvents(new OpenShulker(plugin), plugin);
        }
    }
}
