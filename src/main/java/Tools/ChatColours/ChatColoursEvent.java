package Tools.ChatColours;

import Mangers.ColourConvert;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColoursEvent implements Listener {

    @EventHandler
    public void getChat(AsyncPlayerChatEvent event) {

        ColourConvert colourConvert = new ColourConvert();

        event.setMessage(colourConvert.convertString(event.getMessage()));
    }
}
