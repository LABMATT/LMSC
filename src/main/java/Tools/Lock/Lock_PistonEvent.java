package Tools.Lock;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Piston;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Lock_PistonEvent implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_PistonEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void pistonEvent(BlockPistonExtendEvent event) {
        Lock_Get_Locked_Items locked = new Lock_Get_Locked_Items(plugin);
        Lock_Manger lock_manger = new Lock_Manger(plugin);
        Logout log = new Logout(plugin);

        Block block = event.getBlock();
        Block blockAbove;
        Piston piston = (Piston) block.getBlockData();
        BlockFace pistonFace = piston.getFacing();

        for (int i = 0; i < 8; i++) {

            switch (pistonFace) {
                case NORTH:
                    blockAbove = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ() - i).getBlock();

                    break;
                case EAST:
                    blockAbove = new Location(block.getWorld(), block.getX() + i, block.getY() + 1, block.getZ()).getBlock();
                    break;

                case SOUTH:
                    blockAbove = new Location(block.getWorld(), block.getX(), block.getY() + 1, block.getZ() + i).getBlock();
                    break;

                case WEST:
                    blockAbove = new Location(block.getWorld(), block.getX() - i, block.getY() + 1, block.getZ()).getBlock();
                    break;

                case UP:
                    blockAbove = new Location(block.getWorld(), block.getX(), block.getY() + i, block.getZ()).getBlock();
                    break;

                case DOWN:
                    blockAbove = new Location(block.getWorld(), block.getX(), block.getY() - i, block.getZ()).getBlock();
                    break;

                default:
                    blockAbove = new Location(block.getWorld(), block.getX(), block.getY(), block.getZ()).getBlock();
            }
            if(lock_manger.isBlockType(blockAbove.getType().toString()))
            {
                if (locked.isLocked(blockAbove)) {

                    log.debugOut(getClass().getName(), ChatColor.DARK_BLUE, "Illegal block movement resulted in piston been broken.");
                    block.breakNaturally();
                    event.setCancelled(true);
                }
            }

        }
    }
}