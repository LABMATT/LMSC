package Tools.Cinema;

//allows crafting of spawners and name tags

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterCinema {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterCinema(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerCinemaSystems() {
        Logout log = new Logout(plugin);
        //ConfigManger config = new ConfigManger(plugin);

        plugin.getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "cinama Block Finder fffffffffffffffffffffffffffff.");


        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("cinema")) {

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Cinema by LABMATT_SERVER_CONTROLLER is active on this server.");
            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Cinema by LABMATT_SERVER_CONTROLLER is active on this server.");

            new CinemaCommand(plugin);
            //plugin.getServer().getPluginManager().registerEvents((Listener) new mapIni(plugin), plugin);

            BukkitTask pf = new PushFrame(plugin).runTaskTimer(plugin, 0, 1);
        }

    }

}
