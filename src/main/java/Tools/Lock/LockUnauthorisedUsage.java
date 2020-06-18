package Tools.Lock;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.LinkedHashSet;
import java.util.Set;

public class LockUnauthorisedUsage implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    private static Set<String> passedDoors = new LinkedHashSet<>();


    public LockUnauthorisedUsage(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(BlockPhysicsEvent event) {

        /*

            Lock_Get_Block_Types getlockables = new Lock_Get_Block_Types(plugin);
            Lock_Get_Locked_Items getlockeditems = new Lock_Get_Locked_Items(plugin);
            Lock_Get_Players retplr = new Lock_Get_Players(plugin);
            Lock_Get_Players getLocPlayers = new Lock_Get_Players(plugin);
            Logout log = new Logout(plugin);

            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "Now in lockable change state event");
            String[] lockableBlocks = getlockables.GetLockableBlocks();
            String[] locked = getlockeditems.getLockedItems();

            int distanceFromDoor = 100;
            boolean validPlayer = false;

            //Is a lockable block
            if (Arrays.toString(lockableBlocks).contains(event.getBlock().getType().toString())) {
                String item = "";
                String itemLoc = event.getBlock().getX() + "|" + event.getBlock().getY() + "|" + event.getBlock().getZ() + "|" + event.getBlock().getType().toString() + "|" + event.getBlock().getWorld().getName() + "|";

                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Confirmed lockable block.");

                //Checks if the item has a lock on it.
                for (int i = 0; i < locked.length; i++) {
                    if (locked[i].contains(itemLoc)) {
                        item = locked[i];
                        break;
                    }
                }


                //If there is an active lock then test the players distance from the door.
                if (item.length() > 0) {

                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Block has an existing lock on it.");

                    String[] locPlayers = getLocPlayers.getPlayers(item);

                    for(String testPlayer : locPlayers)
                    {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Checking if valid players are near the door. Player is <" + testPlayer + ">");

                        if(testPlayer.length() != 0) {


                            try {
                                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Getting player <" + testPlayer + "> distance now.");
                                Player plr = Bukkit.getPlayer(UUID.fromString(testPlayer));

                                log.debugOut(this.getClass().getName(), ChatColor.AQUA, " player is " + plr.getName());

                                distanceFromDoor = (int) event.getBlock().getLocation().distance(plr.getLocation());
                                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Player <" + plr + "> is <" + distanceFromDoor + "> blocks from door.");
                                if (distanceFromDoor < 2) {
                                    validPlayer = true;
                                    log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Is a valid player found!");
                                    break;
                                }
                            } catch (NullPointerException e)
                            {
                                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Error getting player <" + testPlayer + "> with error " + e );

                            }
                        }
                    }

                    if(!validPlayer)
                    {
                        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "No valid player. Cancling event." + event.getBlock().getType().toString());

                        if (event.getBlock().getType().toString().contains("_DOOR")) {

                            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Source block is " + event.getSourceBlock());

                            String theDoor = event.getBlock().getX() + "|" + event.getBlock().getY() + "|" + event.getBlock().getZ() + "|" + event.getBlock().getWorld().getName() + "|";
                            Boolean trigger = true;

                            for(String doorType : passedDoors)
                            {
                                if(doorType.contains(theDoor))
                                {
                                    if(doorType.contains(theDoor + "true"))
                                    {

                                    }
                                }
                            }
                            Door door = (Door) event.getBlock().getBlockData();

                            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Door state is " + door.getAsString());
                            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Door before cast is " + door.isOpen());

                                BlockData doorInfo = event.getBlock().getBlockData();
                                ((Openable) doorInfo).setOpen(false);
                                event.getBlock().setBlockData(doorInfo);


                                        passedDoors.add(theDoor);
                            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "The amout of passed doors " + passedDoors);
                        }
                        else
                        {

                        }
                    }

                }
            }

         */
    }
}


