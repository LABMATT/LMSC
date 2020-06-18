package Tools.Hammer;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class HammerTransport {

    private LABMATT_SERVER_CONTROLLER plugin;

    public HammerTransport(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    static private Set<String> data = new LinkedHashSet<>();

    String[] getTransportData() {
        Logout log = new Logout(plugin);

        String[] returnInfo = new String[data.size()];

        int i = 0;
        for (String var : data) {
            returnInfo[i] = var;
            i++;
        }

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Return info is " + Arrays.toString(returnInfo));
        return returnInfo;
    }

    void removeTransportData(String setData) {

        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Removing " + setData + " from HammerTransport array.");

        String removeItem = "Error finding item";

        for (String var : data) {
            if (var.toLowerCase().contains(setData.toLowerCase())) {
                removeItem = var;
                break;
            }
        }

        data.remove(removeItem);

        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Removed " + removeItem + " from HammerTransport array.");
    }

    public void addTransportData(String setData) {
        Logout log = new Logout(plugin);
        data.add(setData);
        log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Added " + setData + " to HammerTransport array.");
    }

    public void cleanuptransport() {
        //Items short listed for removal if the player is not online.
        Set<String> shortList = new LinkedHashSet<>();

        //Go though each data item
        for (String transportItem : data) {
            for (Player onlineplayer : Bukkit.getServer().getOnlinePlayers()) {
                if (transportItem.toLowerCase().contains(onlineplayer.getDisplayName().toLowerCase())) {
                    shortList.add(transportItem);
                    break;
                }
            }
        }

        data.removeAll(shortList);
    }
}
