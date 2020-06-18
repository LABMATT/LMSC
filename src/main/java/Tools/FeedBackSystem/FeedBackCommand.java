package Tools.FeedBackSystem;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FeedBackCommand implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public FeedBackCommand(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("feedback").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        plugin.reloadConfig();

        if (!plugin.getConfig().getBoolean("Feedback")) {
            sender.sendMessage(ChatColor.DARK_RED + "Feedback not enabled on this server.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("feedback") && plugin.getConfig().getBoolean("Feedback")) {

            //Checks if we got arguments from players.
            if (args.length > 0) {

                String feedbackName = "Feedback-<" + sender.getName() + ">-" + dtf.format(now).replace(":", "|");

                String feedback = "";
                for (String var : args) {
                    feedback = feedback + " " + var;
                }

                config.getOldConfig("Feedback").set(feedbackName, feedback);
                config.saveConfig();

                sender.sendMessage(ChatColor.GREEN + "Thank you for your feedback.");
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Player " + sender.getName() + " has left feedback. Feedback: " + feedback);
            } else return false;
        } else return false;

        return true;
    }
}
