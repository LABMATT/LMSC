package Tools.TreeChop;

import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterTreeChop {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterTreeChop(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerTreeChop() {
        Logout log = new Logout(plugin);

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("treeChop")) {
            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Tree chop is online.");
            plugin.getServer().getPluginManager().registerEvents(new treeChop_Event(plugin), plugin);
        }

    }
}
