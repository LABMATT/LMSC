package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class CommandLoadWorld implements CommandExecutor {

    //usage in main plugin
    private final LABMATT_SERVER_CONTROLLER plugin;

    public CommandLoadWorld(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("loadWorld");

        if (command != null) {
            command.setExecutor(this);
        }
    }


    //gets all the command in data
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManger config = new ConfigManger(plugin);

        if (command.getName().equalsIgnoreCase("loadWorld") && args.length != 0) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return true;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to load world <" + args[0] + "> to server.");


            //move from non active list to active list
            List<String> nonactiveworldList = config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).getStringList("worlds");

            boolean isactive = false;

            //check non active world list for the world
            for (String worldname : nonactiveworldList) {
                if (worldname.equalsIgnoreCase(args[0])) {
                    isactive = true;
                    break;
                }
            }

            if (isactive)
                {
                    nonactiveworldList.remove(args[0]);
                    config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).set("worlds", nonactiveworldList);
                    config.saveConfig();

                    List<String> activeworldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                    activeworldList.add(args[0]);
                    config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).set("worlds", activeworldList);
                    config.saveConfig();

                }


                //load the world
                List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                List<World> currentworlds = plugin.getServer().getWorlds();


                for (String world : worldList) {

                    if (world.equalsIgnoreCase(args[0])) {

                        File worldfile = new File(plugin.getServer().getWorldContainer().getPath() + File.separator + world);

                        if (worldfile.exists()) {

                            for (World currentWorld : currentworlds) {
                                if (currentWorld.getName().equalsIgnoreCase(world)) {

                                    sender.sendMessage(ChatColor.DARK_RED + "World <" + world + "> is already loaded.");
                                    return true;
                                }
                            }

                            try {

                                WorldCreator creator = new WorldCreator(world);
                                creator.createWorld();

                                sender.sendMessage(ChatColor.GREEN + "World <" + world + "> is loaded.");

                                new InvConfigUpdate(plugin);

                                return true;

                            } catch (Exception e) {

                                sender.sendMessage(ChatColor.DARK_RED + "Error loading world <" + world + ">. World was found in active worlds but could not be loaded form file.");
                                return true;
                            }


                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Could not find world file <" + world + ">.");
                            return true;
                        }
                    }
                }

                sender.sendMessage(ChatColor.DARK_RED + "Could not find world <" + args[0] + ">. To add world /addworld <world name>");
                return true;

            }

            return false;
        }
    }
