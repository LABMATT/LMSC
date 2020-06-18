package Tools.LMSCWorldManger;

import Mangers.ConfigManger;
import org.bukkit.World;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class InvConfigUpdate {


    public InvConfigUpdate(LABMATT_SERVER_CONTROLLER plugin) {

        ConfigManger config = new ConfigManger(plugin);
        List<World> currentworlds = plugin.getServer().getWorlds();

        for(World world : currentworlds)
        {
            String worldInv = config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).getString(world.getName());

            if(worldInv == null)
            {
                config.getConfig(new File("WorldManger" + File.separator + "Players-Inventory" + File.separator + "Inventory")).set(world.getName(), "default");
                config.saveConfig();
            }
        }
    }
}
