package Commands;

import Action.LMSCcleanup;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class LMSCCleanCommand implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public LMSCCleanCommand(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("lmscClean")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("lmscClean")) {
            LMSCcleanup cleanup = new LMSCcleanup(plugin);

            cleanup.preformCleanup();

            sender.sendMessage(ChatColor.GREEN + "Clean up command complete.");
            return true;
        } else {
            return false;
        }
    }
}