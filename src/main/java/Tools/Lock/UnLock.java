package Tools.Lock;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UnLock implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    UnLock(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        PluginCommand command = plugin.getCommand("unlc");

        if (command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Logout log = new Logout(plugin);
        Lock_Manger lockM = new Lock_Manger(plugin);
        ConfigManger config = new ConfigManger(plugin);

        if (sender instanceof Player) {
            Player plr = (Player) sender;
            Block plrTarget = plr.getTargetBlockExact(10);

            if (plrTarget != null) {

                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> is unlocking an item.");

                if (lockM.isLocked(plrTarget)) {
                    String item = lockM.getLockItem(plrTarget);
                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Retrived item <" + item + "> from chunk file.");

                    if (lockM.isPlayerApartOf(item, plr.getDisplayName())) {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> is a valid owner of <" + item + ">.");

                        if (!lockM.editItem(item, false)) {
                            plr.sendMessage(ChatColor.DARK_RED + "Error Unlocking Item.");
                            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "edit locked item retuned falses.");
                        } else {
                            plr.sendMessage(ChatColor.GREEN + "Unlocked.");
                        }

                        Block secondBlock = lockM.getSecondBlock(plrTarget);
                        boolean secondUnlock = true;

                        if (secondBlock != null) {
                            secondUnlock = lockM.editItem(lockM.getLockItem(secondBlock), false);
                        }

                        if (!secondUnlock) {
                            plr.sendMessage(ChatColor.DARK_RED + "Error Unlocking Second Item.");
                        }

                    } else {
                        if (!plr.isOp()) {
                            plr.sendMessage(ChatColor.DARK_RED + "You Must Be An Owner Of This Item To UnLock It.");
                        } else {
                            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> is a valid owner of <" + item + ">.");

                            if (!lockM.editItem(item, false)) {
                                plr.sendMessage(ChatColor.DARK_RED + "Error Unlocking Item.");
                                log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "edit locked item retuned falses.");
                            } else {
                                plr.sendMessage(ChatColor.GREEN + "Unlocked.");
                            }

                            Block secondBlock = lockM.getSecondBlock(plrTarget);
                            boolean secondUnlock = true;

                            if (secondBlock != null) {
                                secondUnlock = lockM.editItem(lockM.getLockItem(secondBlock), false);
                            }

                            if (!secondUnlock) {
                                plr.sendMessage(ChatColor.DARK_RED + "Error Unlocking Second Item.");
                            }

                            plr.sendMessage(ChatColor.AQUA + "Overridden lock with administrator privileges. Your Activity will be logged.");
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDateTime now = LocalDateTime.now();

                            List<String> overrideConfig = config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).getStringList("overrides");
                            overrideConfig.add("Administrator " + plr.getDisplayName() + " unlocked item " + item + ".");
                            config.getConfig(new File("lockdata" + File.separator + "adminOverrides" + File.separator + dtf.format(now))).set("overrides", overrideConfig);
                            config.saveConfig();
                        }
                    }

                } else {
                    plr.sendMessage(ChatColor.YELLOW + "This Block Is Not Locked To Anyone.");
                }
            }
        } else {

            //For conseol input for unlocking chests.
            if (args.length > 0) {
                String stringWorld = args[0];
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                int z = Integer.parseInt(args[3]);

                World world;
                Location plrTargetloc;
                Block plrTarget;

                world = Bukkit.getServer().getWorld(stringWorld);

                if (world != null) {
                    plrTargetloc = new Location(world, x, y, z);
                    plrTarget = plrTargetloc.getBlock();

                    boolean firstUnlock = lockM.editItem(lockM.getLockItem(plrTarget), false);
                    boolean secondUnlock = true;

                    Block secondBlock = lockM.getSecondBlock(plrTarget);

                    if (secondBlock != null) {
                        secondUnlock = lockM.editItem(lockM.getLockItem(secondBlock), false);
                    }


                    if (firstUnlock && secondUnlock) {

                        sender.sendMessage(ChatColor.GREEN + "Item unlocked.");
                    } else {

                        sender.sendMessage(ChatColor.DARK_RED + "Error Unlocking Some Items.");
                    }
                } else {

                    sender.sendMessage(ChatColor.DARK_RED + "ERROR World or location invalid.");
                }


            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "To unlock an item in console use /unlc <World> <x> <y> <z>");
            }
        }

        return true;
    }
}
