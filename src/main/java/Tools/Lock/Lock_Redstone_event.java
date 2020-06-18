package Tools.Lock;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_Redstone_event implements Listener {

    public LABMATT_SERVER_CONTROLLER plugin;

    Lock_Redstone_event(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void redstoneTrigger(BlockRedstoneEvent event) {

        Block block = event.getBlock();
        Lock_Manger lock_manger = new Lock_Manger(plugin);

        if(lock_manger.isBlockType(block.getType().toString()))
        {
             System.out.println("Is the corrcet block type.");

            if(lock_manger.isLocked(block))
            {
                System.out.println("Is locked");

                if(block.getType() == Material.OAK_DOOR)
                {
                    System.out.println("Redstone block event is <" + event.getBlock() + ">. And <" + event.getEventName() + ">.");
                    Door door = (Door) block.getBlockData();
                    door.setOpen(false);
                    //event.setNewCurrent(0);
                }
            }
        }

    }
}
