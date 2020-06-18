package Commands;

import Action.NoteBlock;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;

public class PlayMusic implements CommandExecutor {

    private LABMATT_SERVER_CONTROLLER plugin;

    public PlayMusic(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
        plugin.getCommand("playmusic").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Makes sure ony players can use this command.
        if (sender instanceof Player) {
            //Sets the Player instance to plr.
            Player plr = (Player) sender;

            System.out.println("PlayMusic: Now in music mode");

            //if the player is ready for some pizza time.
            if (command.getName().equalsIgnoreCase("playmusic")) {

                //if we have arguments then start analysising them.
                if (args.length > 1) {

                    //The speed between each note.
                    int Speed;

                    //try convert the string to a number for the speed.
                    try {
                        Speed = Integer.valueOf(args[0]);
                    } catch (NumberFormatException e) {
                        plr.sendMessage(ChatColor.RED + "Speed must be a number between 1ms and 10000ms");
                        return true;
                    }

                    //The incrmentable intager for the new song array.
                    int songCount = 0;

                    //Sets the players note input as the sound source.
                    String argsSongIn = args[1];


                    //knows how many commers ina the argument there are
                    int comRem = 0;
                    boolean preChar = false;
                    int threeNum = 0;


                    //Loops though the argument and counts the amout of commers.
                    for (int i = 0; i < argsSongIn.length(); i++) {
                        if (argsSongIn.charAt(i) == ',') {
                            comRem++;
                            preChar = false;
                            threeNum = 0;
                        }

                        if (Character.isDigit(argsSongIn.charAt(i))) {
                            threeNum++;
                        }
                        if (threeNum == 3) {
                            return false;
                        }

                        if ((Character.isDigit(argsSongIn.charAt(i)) && (preChar))) {
                            comRem++;
                            preChar = false;
                        }
                        preChar = Character.isDigit(argsSongIn.charAt(i));
                    }


                    //This is the array that our song will be stored in. We get the argument lengh the remove the commers.
                    int[] song = new int[argsSongIn.length() - comRem];


                    //CharElement is the number of the char we are looking at.
                    int charElement = 0;

                    //Go though all the chars in the song untill we reach the end.
                    while (charElement < argsSongIn.length()) {

                        //If the char that we are looking at is a "," then just skip it.
                        if (argsSongIn.charAt(charElement) == ',') {
                            //good on you mate. just sit this one out
                        }

                        //If the char we are looking at is the digit of a number then make sure its not to small or big before loading it into the song array.
                        else if (Character.isDigit(argsSongIn.charAt(charElement))) {

                            //CharStatement is the final interger value of players notes between 1 and 25.
                            int charStatement;

                            //Checks to make sure that if we add one number to array then it over floys. if not then checks the next char is a digit as well.
                            if (charElement != argsSongIn.length() - 1 && Character.isDigit(argsSongIn.charAt(charElement + 1))) {
                                //Combine the two chars to a string.
                                String tensNote = "" + argsSongIn.charAt(charElement) + argsSongIn.charAt(charElement + 1);

                                //Convert the string to a int
                                charStatement = Integer.valueOf(tensNote);

                                //And go to next char element because we combined two chars.
                                charElement++;
                            } else {
                                //Converts the char to a number. this is for single intergers
                                charStatement = Character.getNumericValue(argsSongIn.charAt(charElement));
                            }


                            //Make sure the notes are within bounds.
                            if ((charStatement > -1) && (charStatement < 25)) {
                                song[songCount] = charStatement;
                                songCount++;

                            } else {
                                plr.sendMessage(ChatColor.RED + "Note at position " + charElement + " (" + charStatement + ") is out of bounds. min 1 - max 24.");
                                return true;
                            }
                        }

                        //If the players formating of the notes was wrong then produce an error.
                        else {
                            plr.sendMessage(ChatColor.RED + "Notes must be between 1-24 and be set out as: 1,2,3,4,5,6,7,8");
                            return true;
                        }


                        charElement++;
                    }

                    //Creates new lock instance to call apon.
                    NoteBlock CallNoteBlock = new NoteBlock(plugin);

                    System.out.println("PlayMusic: sending data from " + plr.getName() + " is playing " + Arrays.toString(song) + " at speed " + Speed);

                    //Calls the new lock event and passes the player and its arguments in.
                    CallNoteBlock.PlayNoteBlock(plr, song, Speed);


                } else {
                    return false;
                }

            } else {
                //if the command is nother lc or unlc then produce this message.
                sender.sendMessage("Unknown command: " + command);
            }
        } else {
            sender.sendMessage("You must be a player to use this command.");
        }
        return true;
    }
}
