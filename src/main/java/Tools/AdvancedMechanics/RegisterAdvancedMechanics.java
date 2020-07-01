package Tools.AdvancedMechanics;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;
public class RegisterAdvancedMechanics {

    public RegisterAdvancedMechanics(LABMATT_SERVER_CONTROLLER plugin) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        /*
        if (!config.isFile(new File("AdvancedMechanics"))) {
            config.getOldConfig("AdvancedMechanics").set("placer", true);
            config.saveConfig();
            config.getOldConfig("AdvancedMechanics").set("braker", true);
            config.saveConfig();
        }
         */

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("AdvancedMechanics")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Advanced Mechanics by LABMATT_SERVER_CONTROLLER is active on this server.");

            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "AdvancedMechanics by LABMATT_SERVER_CONTROLLER is active on this server.");

            new BlockBrakerCraft(plugin);
        }

    }

}
