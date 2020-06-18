package SubActions;

import org.bukkit.scheduler.BukkitRunnable;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Scheduler extends BukkitRunnable {


    private LABMATT_SERVER_CONTROLLER plugin;

    public Scheduler(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    @Override
    public void run() {

    }
}
