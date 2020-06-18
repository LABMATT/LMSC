package Tools.Home;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

class Send_Player_Home {
    private LABMATT_SERVER_CONTROLLER plugin;

    Send_Player_Home(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    void sendPlayerHome(Player plr, String[] args) {
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);


        //Configers wich home number you are setting.
        int num = 1;
        int maxhomes = config.getConfig(new File("home" + File.separator + "home-Config")).getInt("Max-Homes");

        if (args.length == 1) {

            try {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
                plr.sendMessage(ChatColor.RED + "Invalid home number <" + args[0] + ">.");
                return;
            }

            if (num > maxhomes) {
                plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
                return;
            }
        } else if (args.length > 1) {

            plr.sendMessage(ChatColor.RED + "You can have a maximum of <" + maxhomes + "> homes on this server.");
            return;
        }


        //try's sending a player home.
        try {

            //Sets up a dobble array for XYZ. A string array for the world. A float for the pitch and yaw.
            double[] plrLocation = new double[6];
            String[] plrWorld = new String[6];
            float[] pitchYaw = new float[6];


            //Gets a list of data from the players UUID
            List<Double> loc = config.getConfig(new File("home" + File.separator + "homes" + File.separator + "home-" + num)).getDoubleList("Home." + plr.getUniqueId().toString());
            List<String> plrHomeWorld = config.getConfig(new File("home" + File.separator + "homes" + File.separator + "home-" + num)).getStringList("Home." + plr.getUniqueId().toString());
            List<Float> getPitchYaw = config.getConfig(new File("home" + File.separator + "homes" + File.separator + "home-" + num)).getFloatList("Home." + plr.getUniqueId().toString());


            //Maps the XYZ values to the dobble array.
            int i = 0;
            for (double val : loc) {
                plrLocation[i] = val;
                i++;
            }

            //Maps the string to the string array
            i = 0;
            for (String val : plrHomeWorld) {
                plrWorld[i] = val;
                i++;
            }

            //Maps the Pitch yaw to an array.
            i = 0;
            for (float val : getPitchYaw) {
                pitchYaw[i] = val;
                i++;
            }

            //Creates new vector location based on data form config.
            Location tploc = new Location(plugin.getServer().getWorld(plrWorld[0]), plrLocation[0], plrLocation[1], plrLocation[2], pitchYaw[3], pitchYaw[4]);


            //Sends all the new location info to the consal for debug and admins.
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Player " + plr.getDisplayName() + " is returning home to " + tploc);

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "The player " + plr.getDisplayName() + " has is retuning home. Info below: ");
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "World: " + plrWorld[0]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "x: " + plrLocation[0]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "y: " + plrLocation[1]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "z: " + plrLocation[2]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "yaw: " + pitchYaw[3]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "pitch: " + pitchYaw[4]);
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Final location vector:" + tploc);

            //teloprts the player home.
            plr.teleport(tploc);

        } catch (Exception e) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Player " + plr.getDisplayName() + " failed to return home with error: " + e);
            plr.sendMessage(ChatColor.DARK_RED + "There was an error sending you home.");
        }
    }

}
