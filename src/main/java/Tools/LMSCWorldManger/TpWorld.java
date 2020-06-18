package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class TpWorld implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public TpWorld(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("tpworld");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManger config = new ConfigManger(plugin);


        if (command.getName().equalsIgnoreCase("tpworld") && args.length != 0) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }


            //Gets all the players names mentioned.
            String worldname = args[0];

            if (worldname.length() > 0) {

                World world = plugin.getServer().getWorld(worldname);
                if (world != null) {
                    for (int i = 1; i < args.length; i++) {
                        if (args[i].length() > 3) {
                            Player player = plugin.getServer().getPlayer(args[i]);

                            if(player != null) {

                                Location loc;

                                String lastLocation = config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).getString(player.getUniqueId().toString());

                                if (lastLocation != null) {

                                    //Gets a list of data from the players UUID

                                    //Sets up a dobble array for XYZ. A string array for the world. A float for the pitch and yaw.
                                    double[] plrLocation = new double[6];
                                    String[] plrWorld = new String[6];
                                    float[] pitchYaw = new float[6];

                                    List<Double> locl = config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).getDoubleList(player.getUniqueId().toString());
                                    List<String> plrHomeWorld = config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).getStringList(player.getUniqueId().toString());
                                    List<Float> getPitchYaw = config.getConfig(new File("WorldManger" + File.separator + "Player-Positions" + File.separator + world.getName())).getFloatList(player.getUniqueId().toString());


                                    //Maps the XYZ values to the dobble array.
                                    int r = 0;
                                    for (double val : locl) {
                                        plrLocation[r] = val;
                                        r++;
                                    }

                                    //Maps the string to the string array
                                    r = 0;
                                    for (String val : plrHomeWorld) {
                                        plrWorld[r] = val;
                                        r++;
                                    }

                                    //Maps the Pitch yaw to an array.
                                    r = 0;
                                    for (float val : getPitchYaw) {
                                        pitchYaw[r] = val;
                                        r++;
                                    }

                                    try {
                                        //Creates new vector location based on data form config.
                                        loc = new Location(plugin.getServer().getWorld(plrWorld[0]), plrLocation[0], plrLocation[1], plrLocation[2], pitchYaw[3], pitchYaw[4]);
                                    } catch (Exception e)
                                    {
                                        sender.sendMessage(ChatColor.RED + "There was an error sending <" + args[1] + "> to <" + args[0] + ">.");
                                        return true;
                                    }

                                } else {
                                    loc = world.getSpawnLocation();
                                }

                                player.teleport(loc);

                            } else {

                                sender.sendMessage(ChatColor.RED + "Could not find player <" + args[i] + ">.");
                                return true;
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "No world <" + args[0] + "> loaded in server.");
                }
                return true;
            }
        }

        return false;
    }
}
