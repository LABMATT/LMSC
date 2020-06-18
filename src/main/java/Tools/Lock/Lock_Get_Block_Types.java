package Tools.Lock;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Lock_Get_Block_Types {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Lock_Get_Block_Types(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    static private String[] blockTypes;

    String[] GetLockableBlocks() {
        Logout log = new Logout(plugin);

        if (blockTypes == null) {

            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Lockable blocks are 0, reloading form config");
            loadLockablesFromConfig();
        }

        return blockTypes;
    }


    public void loadLockablesFromConfig() {
        ConfigManger config = new ConfigManger(plugin);
        Lock_Clean_Config clean = new Lock_Clean_Config(plugin);

        if (config.isFile(new File("lockdata" + File.separator + "BlockTypes"))) {
            List<String> blocktypesList = config.getConfig(new File("lockdata" + File.separator + "BlockTypes")).getStringList("BlockTypes");

            blockTypes = blocktypesList.toArray(new String[0]);
        } else {
            clean.preformClean();
        }
    }

    boolean isBlockType(String Type) {
        return Arrays.toString(GetLockableBlocks()).contains(Type);
    }
}
