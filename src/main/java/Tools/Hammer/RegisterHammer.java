package Tools.Hammer;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterHammer {

    private LABMATT_SERVER_CONTROLLER plugin;

    static BossBar bar;

    public RegisterHammer(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerHammer() {
        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("hammer")) {
            bar = plugin.getServer().createBossBar(ChatColor.GREEN + "Hammer on", BarColor.GREEN, BarStyle.SOLID);
            bar.setVisible(true);

            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Hammer by LMSC is active on this server.");
            new HammerToggle(plugin);
            plugin.getServer().getPluginManager().registerEvents(new HammerDisconnectEvent(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new EventHammerMiner(plugin), plugin);

        }
    }

    public void removeBar() {
        bar.removeAll();
    }
}
