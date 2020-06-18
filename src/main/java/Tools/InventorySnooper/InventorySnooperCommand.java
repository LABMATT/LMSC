package Tools.InventorySnooper;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class InventorySnooperCommand implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    InventorySnooperCommand(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("inv")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logout log = new Logout(plugin);


        if (command.getName().equals("inv")) {
            log.debugOut(getClass().getName(), ChatColor.AQUA, "Player <" + sender.getName() + "> Used Inv Command.");

            if (sender instanceof Player) {
                Player plr = (Player) sender;
                log.debugOut(getClass().getName(), ChatColor.AQUA, "Player <" + plr.getName() + "> Used Inv Command.");


                if (plr.isOp()) {
                    log.debugOut(getClass().getName(), ChatColor.AQUA, "Player <" + sender.getName() + "> Is authorised.");

                    if (args.length == 1) {
                        log.debugOut(getClass().getName(), ChatColor.AQUA, "Argument <" + args[0] + "> Found.");

                        if (args[0] != null) {
                            try {
                                Player plr2 = plugin.getServer().getPlayer(args[0]);
                                if (plr2 != null) {
                                    log.debugOut(getClass().getName(), ChatColor.AQUA, "Player <" + plr.getDisplayName() + "> Opened the inventory of <" + plr2.getDisplayName() + ">.");
                                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Player <" + plr.getDisplayName() + "> Opened the inventory of <" + plr2.getDisplayName() + ">.");
                                    plr.openInventory(plr2.getInventory());
                                    return true;
                                } else {
                                    log.debugOut(getClass().getName(), ChatColor.AQUA, "No player found by that name.");
                                    plr.sendMessage(ChatColor.DARK_RED + "No player found by that name.");
                                    return true;
                                }
                            } catch (NullPointerException e) {
                                log.debugOut(getClass().getName(), ChatColor.AQUA, "No player found.");
                                sender.sendMessage(ChatColor.DARK_RED + "No player found by that name.");
                                return true;
                            }
                        }
                    } else {
                        log.debugOut(getClass().getName(), ChatColor.AQUA, "No Arguments found.");
                        return false;
                    }

                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You must be an Administrator to use this command.");
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
                return true;
            }
        } else {
            return false;
        }


        return false;
    }

}
