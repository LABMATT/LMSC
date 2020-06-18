package Mangers;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Set;

public class ColourConvert {

    private static HashMap<String, String> colourMap = new HashMap<>();

    public void setHash() {
        colourMap.put("\\blue ", "&9");
        colourMap.put("\\darkblue ", "&1");

        colourMap.put("\\red ", "&c");
        colourMap.put("\\darkred ", "&4");

        colourMap.put("\\green ", "&a");
        colourMap.put("\\darkgreen ", "&2");

        colourMap.put("\\yellow ", "&e");

        colourMap.put("\\pink ", "&d");
        colourMap.put("\\purple ", "&5");

        colourMap.put("\\black ", "&0");

        colourMap.put("\\white ", "&f");

        colourMap.put("\\aqua ", "&b");
        colourMap.put("\\darkaqua ", "&3");

        colourMap.put("\\gold ", "&6");

        colourMap.put("\\bold ", "&l");

        colourMap.put("\\gray ", "&7");
        colourMap.put("\\darkgray ", "&8");

        colourMap.put("\\italic ", "&o");

        colourMap.put("\\strike ", "&m");

        colourMap.put("\\under ", "&n");

        colourMap.put("\\reset ", "&r");

        colourMap.put("\\crazy ", "&k");
    }


    public String convertString(String message) {

        Set colourNames = colourMap.keySet();

        for(Object currentItem : colourNames)
        {

            String itemString = currentItem.toString();
            message = message.replace(itemString, colourMap.get(itemString));
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
