package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

class TpWorldRemove {

    TpWorldRemove(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender, String[] args) {
        ConfigManger config = new ConfigManger(plugin);

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return;
                }
            }

            if(args[2].length() > 3)
            {
                Player plr = plugin.getServer().getPlayer(args[2]);

                if(plr != null)
                {
                    World world = plugin.getServer().getWorld(args[1]);

                    if(world != null)
                    {
                        config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).set(plr.getUniqueId().toString(), "");
                        config.saveConfig();

                        sender.sendMessage(ChatColor.GREEN + "Players <" + args[2] + "> from world <" + args[1] + ">.");
                    }else
                    {
                        sender.sendMessage(ChatColor.RED + "No world <" + args[1] + "> found.");
                    }
                } else
                {
                    sender.sendMessage(ChatColor.RED + "No player <" + args[2] + "> online.");
                }
            }
    }
}
