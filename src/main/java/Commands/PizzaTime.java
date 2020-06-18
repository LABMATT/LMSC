package Commands;

import Action.NoteBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class PizzaTime implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public PizzaTime(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("pizzatime")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //runTaskTimer(this, 0, 1560);

        //Makes sure ony players can use this command.
        if (sender instanceof Player) {
            //Sets the Player instance to plr.
            Player plr = (Player) sender;

            System.out.println("Entering pizza time.");

            //if the player is ready for some pizza time.
            if (command.getName().equalsIgnoreCase("pizzatime")) {

                System.out.println("Pizzatime: player is in pizza time");

                //Creates new lock instance to call apon.
                NoteBlock CallNoteBlock = new NoteBlock(plugin);

                int[] pizzaSong = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};

                sender.sendMessage("Pizza Time.");

                //Calls the new lock event and passes the player and its arguments in.
                CallNoteBlock.PlayNoteBlock(plr, pizzaSong, 200);


            } else {
                //if the command is nother lc or unlc then produce this message.
                sender.sendMessage("Unknown command: " + command);
            }
        } else {
            sender.sendMessage("You must be a player to use this command.");
        }

        return false;
    }
}



