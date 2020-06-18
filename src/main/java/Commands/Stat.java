package Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class Stat implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Stat(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("lmstat")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("lmstat")) {
            sender.sendMessage(ChatColor.BLUE + "--== LABMAT SERVER MONITOR ==--");
            sender.sendMessage(ChatColor.GREEN + "Server Name:  " + ChatColor.LIGHT_PURPLE + Bukkit.getMotd());
            sender.sendMessage(ChatColor.GREEN + "Server Bukkit/Spigot Version:  " + ChatColor.LIGHT_PURPLE + Bukkit.getBukkitVersion());
            sender.sendMessage(ChatColor.GREEN + "Server Minecraft Version:  " + ChatColor.LIGHT_PURPLE + Bukkit.getVersion());
            sender.sendMessage(ChatColor.GREEN + "Server Max players:  " + ChatColor.LIGHT_PURPLE + Bukkit.getMaxPlayers());
            sender.sendMessage(ChatColor.GREEN + "World info: " + ChatColor.LIGHT_PURPLE + Bukkit.getWorlds());
            sender.sendMessage(ChatColor.GREEN + "Max Idle time: " + ChatColor.LIGHT_PURPLE + Bukkit.getIdleTimeout());
            sender.sendMessage(ChatColor.GREEN + "Current LMSC settings are: " + ChatColor.LIGHT_PURPLE + " ");
            sender.sendMessage(ChatColor.GREEN + "Server Port is " + ChatColor.LIGHT_PURPLE + plugin.getServer().getPort());
            sender.sendMessage(ChatColor.GREEN + "Max Idle time is " + ChatColor.LIGHT_PURPLE + plugin.getServer().getIdleTimeout());


            long Xmx = Runtime.getRuntime().maxMemory() / 1048576;
            long free = Runtime.getRuntime().freeMemory() / 1048576;
            float percent = free * 100 / Xmx;

            sender.sendMessage(ChatColor.GREEN + "Server memory: " + ChatColor.LIGHT_PURPLE + free + ChatColor.GREEN + " MB free of " + ChatColor.LIGHT_PURPLE + Xmx + ChatColor.GREEN + "MB " + ChatColor.LIGHT_PURPLE + percent + ChatColor.GREEN + "% Used");
            return true;
        }

        return false;
    }
}
