package Tools.Lock;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Door;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

class Lock_Get_Second_Block {

    private LABMATT_SERVER_CONTROLLER plugin;

    Lock_Get_Second_Block(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    Block getSecondBlock(Block block) {
        Lock_Manger lockM = new Lock_Manger(plugin);

        if (lockM.isBlockType(block.getType().toString())) {

            if (block.getType().toString().contains("_DOOR")) {

                Door door = (Door) block.getBlockData();

                switch (door.getHalf()) {
                    case BOTTOM:
                        return block.getLocation().add(0, 1, 0).getBlock();

                    case TOP:
                        return block.getLocation().subtract(0, 1, 0).getBlock();
                }


            } else if (block.getType().toString().contains("CHEST")) {

                String chestData = block.getBlockData().toString();

                if (!chestData.contains("type=single")) {

                    if (chestData.contains("type=left")) {

                        if (chestData.contains("north")) {
                            return block.getLocation().add(1, 0, 0).getBlock();
                        } else if (chestData.contains("east")) {
                            return block.getLocation().add(0, 0, 1).getBlock();
                        } else if (chestData.contains("south")) {
                            return block.getLocation().subtract(1, 0, 0).getBlock();
                        } else if (chestData.contains("west")) {
                            return block.getLocation().subtract(0, 0, 1).getBlock();
                        }

                    } else if (chestData.contains("type=right")) {
                        if (chestData.contains("north")) {
                            return block.getLocation().subtract(1, 0, 0).getBlock();
                        } else if (chestData.contains("east")) {
                            return block.getLocation().subtract(0, 0, 1).getBlock();
                        } else if (chestData.contains("south")) {
                            return block.getLocation().add(1, 0, 0).getBlock();
                        } else if (chestData.contains("west")) {
                            return block.getLocation().add(0, 0, 1).getBlock();
                        }
                    }
                } else {
                    return null;
                }
            } else if (block.getType().toString().contains("BED")) {
                Bed bed = (Bed) block.getBlockData();
                System.out.println("Is a bed!");

                switch (bed.getPart()) {
                    case FOOT:
                        System.out.println("Foot of bed");
                        if(bed.getFacing() == BlockFace.WEST)
                        {
                            System.out.println("West");
                            return block.getLocation().subtract(1, 0, 0).getBlock();
                        } else if(bed.getFacing() == BlockFace.EAST)
                        {
                            System.out.println("east");
                            return block.getLocation().add(1, 0, 0).getBlock();
                        }else if(bed.getFacing() == BlockFace.NORTH)
                        {
                            System.out.println("north");
                            return block.getLocation().subtract(0, 0, 1).getBlock();
                        }else if(bed.getFacing() == BlockFace.SOUTH)
                        {
                            System.out.println("south");
                            return block.getLocation().add(0, 0, 1).getBlock();
                        }

                    case HEAD:
                        System.out.println("Head of bed");
                        if(bed.getFacing() == BlockFace.WEST)
                        {
                            System.out.println("West");
                            return block.getLocation().add(1, 0, 0).getBlock();
                        } else if(bed.getFacing() == BlockFace.EAST)
                        {
                            System.out.println("east");
                            return block.getLocation().subtract(1, 0, 0).getBlock();
                        }else if(bed.getFacing() == BlockFace.NORTH)
                        {
                            System.out.println("north");
                            return block.getLocation().add(0, 0, 1).getBlock();
                        }else if(bed.getFacing() == BlockFace.SOUTH)
                        {
                            System.out.println("south");
                            return block.getLocation().subtract(0, 0, 1).getBlock();
                        }
                }

            }
        }

        return null;
    }
}


