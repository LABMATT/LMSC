package Action;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;

public class NoteBlock {

    private LABMATT_SERVER_CONTROLLER plugin;

    public NoteBlock(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    //When fed a player and an array of notes to play it sings.
    //Plr is the player, note is the array of notes to be played, loud is if it plays to just the player, all players.
    public boolean PlayNoteBlock(Player plr, int[] noteArray, int speed) {

        //Reports data of the note block.
        System.out.println("NoteBlock: Player" + plr.getName() + " is playing " + Arrays.toString(noteArray) + " at speed " + speed);


        int i = 0;
        while (i != noteArray.length) {
            plr.playNote(plr.getLocation(), Instrument.PIANO, new Note(noteArray[i]));

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            i++;
        }
        return true;
    }
}
