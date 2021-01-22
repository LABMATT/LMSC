package Tools.Cinema;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CinemaCommand implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public CinemaCommand(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("cinema").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        plugin.reloadConfig();

        if (!plugin.getConfig().getBoolean("cinema")) {
            sender.sendMessage(ChatColor.DARK_RED + "Cinema is not enabled on this server.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("cinema") && plugin.getConfig().getBoolean("cinema")) {

            //Checks if we got arguments from players.
            if (args.length > 0) {

                if(sender.isOp())
                {

                    // Argument for cinema
                    switch(args[1])
                    {
                        case "create": // Make new cinema
                            break;
                        case "destroy": // Destroy existing cinema
                            break;
                        case "play": // Play video on screen
                            break;
                        case "stop": // Stop playing video on screen
                            break;
                    }

                } else
                {
                    sender.sendMessage(ChatColor.RED + "You Must be OP to use this command.");
                }

            } else return false;
        } else return false;

        return true;
    }
}
