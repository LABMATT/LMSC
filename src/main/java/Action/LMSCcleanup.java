package Action;

import SubActions.Logout;
import Tools.Hammer.HammerTransport;
import Tools.Lock.Lock_Clean_Config;
import Tools.Lock.Lock_Get_Block_Types;
import Tools.Lock.Lock_Get_Locked_Items;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class LMSCcleanup {

    private LABMATT_SERVER_CONTROLLER plugin;

    public LMSCcleanup(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void preformCleanup() {
        Logout log = new Logout(plugin);
        Lock_Clean_Config lcClean = new Lock_Clean_Config(plugin);
        HammerTransport transport = new HammerTransport(plugin);
        Lock_Get_Locked_Items locked = new Lock_Get_Locked_Items(plugin);
        Lock_Get_Block_Types lockables = new Lock_Get_Block_Types(plugin);
        Lock_Clean_Config lock = new Lock_Clean_Config(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Event has triggered LMSC cleanup. Optimising arrays and plugin data.");

        plugin.reloadConfig();

        //Cleanup transport array.
        transport.cleanuptransport();

        //Load both lock and lockables from config
        lockables.loadLockablesFromConfig();

        //Cleans the lock config.
        lock.preformClean();

    }
}
