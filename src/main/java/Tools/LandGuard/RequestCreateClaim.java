package Tools.LandGuard;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.World;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class RequestCreateClaim {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RequestCreateClaim(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void createClaim(Integer[] coordinates, String[] association, World world) {
        ConfigManger config = new ConfigManger(plugin);
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Claim permeters are: <" + world.getName() + "> <" + Arrays.toString(coordinates) + "> <" + Arrays.toString(association) + ">.");

        if (association.length == 2) {
            if (association[0].contains(" t ")) {

            }
        }

        List<String> requestClaimList = config.getConfig(new File("LandGuard" + File.separator + "Request")).getStringList("requestClaimList");
        requestClaimList.add("");
        config.getConfig(new File("LandGuard" + File.separator + "Request")).set("requestClaimList", requestClaimList);
    }
}
