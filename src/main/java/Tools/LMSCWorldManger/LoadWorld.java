package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

class LoadWorld {

    LoadWorld(LABMATT_SERVER_CONTROLLER plugin, CommandSender sender, String[] args) {
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);


        if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be Op to use this command.");
                    return;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Attempting to load world <" + args[1] + "> to server.");


            //move from non active list to active list
            List<String> nonactiveworldList = config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).getStringList("worlds");

            boolean isactive = false;

            //check non active world list for the world
            for (String worldname : nonactiveworldList) {
                if (worldname.equalsIgnoreCase(args[1])) {
                    isactive = true;
                    break;
                }
            }

            if (isactive)
                {
                    nonactiveworldList.remove(args[1]);
                    config.getConfig(new File("WorldManger" + File.separator + "NonActive-Worlds")).set("worlds", nonactiveworldList);
                    config.saveConfig();

                    List<String> activeworldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                    activeworldList.add(args[1]);
                    config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).set("worlds", activeworldList);
                    config.saveConfig();

                }


                //load the world
                List<String> worldList = config.getConfig(new File("WorldManger" + File.separator + "Active-Worlds")).getStringList("worlds");
                List<World> currentworlds = plugin.getServer().getWorlds();


                for (String world : worldList) {

                    if (world.equalsIgnoreCase(args[1])) {

                        File worldfile = new File(plugin.getServer().getWorldContainer().getPath() + File.separator + world);

                        if (worldfile.exists()) {

                            for (World currentWorld : currentworlds) {
                                if (currentWorld.getName().equalsIgnoreCase(world)) {

                                    sender.sendMessage(ChatColor.DARK_RED + "World <" + world + "> is already loaded.");
                                    return;
                                }
                            }

                            try {

                                WorldCreator creator = new WorldCreator(world);
                                creator.createWorld();

                                sender.sendMessage(ChatColor.GREEN + "World <" + world + "> is loaded.");

                                new InvConfigUpdate(plugin);

                                return;

                            } catch (Exception e) {

                                sender.sendMessage(ChatColor.DARK_RED + "Error loading world <" + world + ">. World was found in active worlds but could not be loaded form file. This may be because you reloaded the server.");
                                log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Error loading world <" + world + ">. World was found in active worlds but could not be loaded form file. ERROR: " + e);

                                return;
                            }


                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Could not find world file <" + world + ">.");
                            return;
                        }
                    }
                }

                sender.sendMessage(ChatColor.DARK_RED + "Could not find world <" + args[1] + ">. To add world /addworld <world name>");

        }
    }
