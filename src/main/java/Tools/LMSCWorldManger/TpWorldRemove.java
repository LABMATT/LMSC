package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class TpWorldRemove implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public TpWorldRemove(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("rmtpworld");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManger config = new ConfigManger(plugin);


        if (command.getName().equalsIgnoreCase("rmtpworld") && args.length != 0) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }

            if(args[1].length() > 3)
            {
                Player plr = plugin.getServer().getPlayer(args[1]);

                if(plr != null)
                {
                    World world = plugin.getServer().getWorld(args[0]);

                    if(world != null)
                    {
                        config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).set(plr.getUniqueId().toString(), "");
                        config.saveConfig();

                        sender.sendMessage(ChatColor.GREEN + "Players <" + args[1] + "> from world <" + args[0] + ">.");
                        return true;
                    }else
                    {
                        sender.sendMessage(ChatColor.RED + "No world <" + args[0] + "> found.");
                    }
                } else
                {
                    sender.sendMessage(ChatColor.RED + "No player <" + args[1] + "> online.");
                }
            }
        }

        return false;
    }
}
