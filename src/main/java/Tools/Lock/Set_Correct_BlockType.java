/*
This class is to be used by DEVS ONLY! it just allows mass items to be put into an array and added to the lock config. saves hours of time for me :)
 */

package Tools.Lock;

import Mangers.ConfigManger;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Set_Correct_BlockType implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    Set_Correct_BlockType(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;

        PluginCommand command = plugin.getCommand("a");
        if (command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ConfigManger config = new ConfigManger(plugin);

        if (sender instanceof Player) {
            Player plr = (Player) sender;

            Block plrTarget = plr.getTargetBlockExact(10);


            if (plrTarget != null) {
                if (args.length == 0) {
                    String name = plrTarget.getType().toString();

                    plr.sendMessage(ChatColor.YELLOW + "Block is: " + name);

                    List<String> list = config.getConfig(new File("setblocktypesDEV")).getStringList("lock");
                    list.add("\"" + name + "\"");
                    config.getConfig(new File("setblocktypesDEV")).set("lock", list);
                    config.saveConfig();
                } else if (args[0].equalsIgnoreCase("print")) {
                    List<String> list = config.getConfig(new File("setblocktypesDEV")).getStringList("lock");
                    String addedItem = "{" + Arrays.toString(list.toArray(new String[0])) + "}";
                    addedItem = addedItem.replace("[", "");
                    addedItem = addedItem.replace("]", "");

                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Your blocks are: " + addedItem);
                }
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
        }

        return true;
    }
}
