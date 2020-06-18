package Commands;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class See implements CommandExecutor {


    private LABMATT_SERVER_CONTROLLER plugin;

    public See(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("see").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Makes sure ony players can use this command.
        if (sender instanceof Player) {
            Player plr = (Player) sender;

            //if the player is locking item.
            if (command.getName().equalsIgnoreCase("see")) {

                //gets the block the player is looking at. Then sends it to them. Use catch null pointer if the player is looking to far.
                try {
                    Block plrTarget = plr.getTargetBlockExact(10);
                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "Block: " + plrTarget.getType().toString() + " Location: X" + plrTarget.getLocation().getX() + " Y" + plrTarget.getLocation().getY() + " Z" + plrTarget.getLocation().getZ() + " Info: " + plrTarget.getBlockData());
                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "Chunk Data: " + plrTarget.getChunk().toString());

                } catch (NullPointerException e) {
                    //Tell them they looked to far
                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "Block to far. Max distance is 10 blocks.");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            }
        }
        return true;

    }
}
