package Tools.InvShulker;

import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class OpenShulker implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public OpenShulker(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void logStrip(InventoryClickEvent event) {

        System.out.println("Clicked item");


                ItemStack stack = event.getCursor();


                System.out.println("click type is " + event.getClick());
                System.out.println("action is " + event.getAction());
                System.out.println("cursor is " + event.getCursor());
                System.out.println("clicked is " + stack);
               // System.out.println("clicked is type " + stack.getType());

        //Block block = new Location(plugin.getServer().getWorld("world"), 206, 70, 68).getBlock();
        Block block = (Block) event.getCursor();

        System.out.println("block is " + block);

        ShulkerBox box = (ShulkerBox) block.getState();

                event.getWhoClicked().openInventory(box.getInventory());
               // event.getWhoClicked().openInventory(event.getSlot());

    }
}
