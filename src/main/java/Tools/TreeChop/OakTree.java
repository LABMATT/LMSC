package Tools.TreeChop;

import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

class OakTree {

    private LABMATT_SERVER_CONTROLLER plugin;

    OakTree(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    private Block topBlock = null;
    private Block bottomBlock = null;


    void destoryTree(Block orgin) {
        Logout log = new Logout(plugin);

        getTopBottom(orgin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Orgion block was: <" + orgin + ">.");
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Top Part was: <" + topBlock + ">.");
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Bottom Part was: <" + bottomBlock + ">.");


        if (topBlock != null && bottomBlock != null) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Valid tree part found.");

            if (isTree(orgin)) {
                breakLog();
                breakLeaf(orgin);
            }
        } else {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "No tree found. testing for wired tree.");
            wiredTree();
        }

    }

    private void getTopBottom(Block origin) {
        Logout log = new Logout(plugin);

        final int maxTreeHight = 6;

        for (int num = 0; num != maxTreeHight; num++) {

            Location newloc = origin.getLocation().add(0, num, 0);

            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Testing block <" + newloc.getBlock() + ">.");

            if (newloc.getBlock().getType() == Material.OAK_LEAVES) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Leafes found at <" + newloc.getBlock() + ">.");
                topBlock = newloc.getBlock();
                break;
            } else if (newloc.getBlock().getType() != Material.OAK_LOG) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Item other than wood found.<" + newloc.getBlock().getBlockData() + ">.");
                break;
            }
        }
        for (int num = 0; num != maxTreeHight; num++) {
            Location newloc = origin.getLocation().subtract(0, num, 0);
            if (newloc.getBlock().getType() == Material.DIRT || newloc.getBlock().getType() == Material.COARSE_DIRT) {
                bottomBlock = newloc.getBlock();
                break;
            } else if (newloc.getBlock().getType() != Material.OAK_LOG) {
                break;
            }
        }
    }

    private void breakLog() {
        int amout = (int) bottomBlock.getLocation().distance(topBlock.getLocation());

        for (int num = 1; num != amout; num++) {
            bottomBlock.getLocation().add(0, num, 0).getBlock().breakNaturally();
        }
    }

    private boolean isTree(final Block origin) {
        Logout log = new Logout(plugin);

        Location[] leaves = new Location[4];
        leaves[0] = topBlock.getLocation().subtract(0, 1, 0).add(0, 0, -1);
        leaves[1] = topBlock.getLocation().subtract(0, 1, 0).add(0, 0, 1);
        leaves[2] = topBlock.getLocation().subtract(0, 1, 0).add(-1, 0, 0);
        leaves[3] = topBlock.getLocation().subtract(0, 1, 0).add(+1, 0, 0);

        boolean hasLeaf = true;

        for (Location testForLeaf : leaves) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Testing block for leaves <" + testForLeaf.getBlock().getBlockData() + ">. <" + testForLeaf + ">.");

            if (testForLeaf.getBlock().getType() != Material.OAK_LEAVES) {
                hasLeaf = false;
            }
        }

        return hasLeaf;
    }

    private void breakLeaf(Block origin) {
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Now Braking Leafs.");


        for (int whipe = origin.getZ() - 2; whipe < 2; whipe++) {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Whipe is: <" + whipe + ">. ");

            for (int sides = 1; sides < 2; sides++) {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Sides is: <" + sides + ">. ");

                Location leftLoc = origin.getLocation().add(-sides, 0, whipe);
                Block leftBlock = leftLoc.getBlock();

                Location rightLoc = origin.getLocation().add(sides, 0, whipe);
                Block rightBlock = rightLoc.getBlock();

                if (leftBlock.getType() == Material.OAK_LEAVES) {
                    leftLoc.getBlock().breakNaturally();
                }
                if (rightBlock.getType() == Material.OAK_LEAVES) {
                    rightLoc.getBlock().breakNaturally();
                }
            }
        }


    }

    private void wiredTree() {

    }
}
