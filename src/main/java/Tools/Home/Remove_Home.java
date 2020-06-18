package Tools.Home;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

class Remove_Home {

    private LABMATT_SERVER_CONTROLLER plugin;

    Remove_Home(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    void removeNewHome(Player plr, String[] args) {
        ConfigManger config = new ConfigManger(plugin);

        Player rmPlayer = plr;

        //Configers wich home number you are setting.
        int num = 1;
        int maxhomes = config.getConfig(new File("home" + File.separator + "home-Config")).getInt("Max-Homes");

        if (args.length == 1 || args.length == 2) {

            try {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
                plr.sendMessage(ChatColor.RED + "Invalid home number <" + args[0] + ">.");
                return;
            }

            if (plr.isOp()) {

                if (args.length == 2) {

                    if (args[1].length() > 3) {

                        Player getPlayer = plugin.getServer().getPlayer(args[1]);

                        if (getPlayer != null) {

                            rmPlayer = getPlayer;
                            plr.sendMessage(ChatColor.GREEN + "Removing home <" + args[0] + "> from <" + rmPlayer.getDisplayName() + ">.");

                        } else {

                            plr.sendMessage(ChatColor.RED + "Unable to find player <" + args[1] + ">.");
                            return;
                        }
                    } else {

                        plr.sendMessage(ChatColor.RED + "Unable to find player <" + args[1] + ">.");
                        return;
                    }
                }
            }

            if (num > maxhomes) {
                plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
                return;
            }
        } else if (args.length > 2) {
            plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
            return;
        }

        config.getConfig(new File("home" + File.separator + "homes" + File.separator + "home-" + num)).set("Home." + rmPlayer.getUniqueId().toString(), "");
        config.saveConfig();

        //Sends player a message when your home is removed.
        if(rmPlayer != plr)
        {

            plr.sendMessage(ChatColor.GREEN + "Player <" + rmPlayer.getDisplayName() + "> <" + num + "> home has been removed.");
        } else
        {

            plr.sendMessage(ChatColor.GREEN + "Your home has been removed.");
        }
    }
}
