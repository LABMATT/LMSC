package Tools.LandGuard;

import SubActions.Logout;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterLandGuard {
    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterLandGuard(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void regLandGuard() {
        Logout log = new Logout(plugin);

        if (plugin.getConfig().getBoolean("landguard")) {
            new CreateClaimCommand(plugin);
        }
    }
}
