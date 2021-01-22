package Tools.Cinema;

import Mangers.ConfigManger;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.List;

public class CreateScreen {

    private LABMATT_SERVER_CONTROLLER plugin;

    public CreateScreen(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    void createNewScreen()
    {
        ConfigManger config = new ConfigManger(plugin);

        List<String> scr = config.getConfig(new File("cinema" + File.pathSeparator + "screens")).getStringList("screens");

        for(scr s : String c)
        {

        }

    }
}
