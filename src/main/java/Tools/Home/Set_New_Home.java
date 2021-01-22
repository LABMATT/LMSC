package Tools.Home;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Objects;

class Set_New_Home {

    private LABMATT_SERVER_CONTROLLER plugin;

    Set_New_Home(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    void setNewHome(Player plr, String[] args) {
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);


        //Configers wich home number you are setting.
        int num = 1;
        int maxhomes = config.getConfig(new File("home" + File.separator + "home-Config")).getInt("Max-Homes");


        if(args.length == 1){

            try
            {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore)
            {
                plr.sendMessage(ChatColor.RED + "Invalid home number <" + args[0] + ">.");
                return;
            }

            if(num > maxhomes | num < 0)
            {
                plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
                return;
            }
        } else if(args.length > 1)
        {
            plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
            return;
        }

        try {
            //Create new Object array to place location values into.
            Object[] plrLocation = new Object[6];


            //Set the location values into the object array.
            plrLocation[0] = Objects.requireNonNull(plr.getLocation().getWorld()).getName();
            plrLocation[1] = plr.getLocation().getX();
            plrLocation[2] = plr.getLocation().getY();
            plrLocation[3] = plr.getLocation().getZ();
            plrLocation[4] = plr.getLocation().getYaw();
            plrLocation[5] = plr.getLocation().getPitch();

            //Gets the config file and sets the players UUID to the new home location.

            config.getConfig(new File("home" + File.separator + "homes" + File.separator + "home-" + num)).set("Home." + plr.getUniqueId().toString(), plrLocation);
            config.saveConfig();

            //Sends player a message when your home is set.
            plr.sendMessage(ChatColor.GREEN + "Your home has been set.");

        } catch (Exception e) {

            //if an excetpion if caught then send the consoul a message and error. then return falso the give the player a message.
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Error in setting Player " + plr.getDisplayName() + "home. Error:  " + e);
            plr.sendMessage(ChatColor.DARK_RED + "Error setting new home.");
        }
    }
}
