package Tools.AdvancedMechanics;

import Mangers.ConfigManger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Dispenser;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.ArrayList;

class BlockBrakerCraft {

    BlockBrakerCraft(LABMATT_SERVER_CONTROLLER plugin) {
        ConfigManger config = new ConfigManger(plugin);

      //  if (config.getOldConfig("CraftExtraConfig").getBoolean("nametag")) {
            ItemStack bbdispenser = new ItemStack(Material.DISPENSER);

            ItemMeta bbdispenserMeta = Bukkit.getItemFactory().getItemMeta(bbdispenser.getType());
            

                if(bbdispenserMeta != null)
                {
                    bbdispenserMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Block Braker");
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("Advanced Mechanics block breaker! Power to brake block abutting hole.");

                    bbdispenserMeta.setLore(lore);
                }

                bbdispenser.setItemMeta(bbdispenserMeta);






            NamespacedKey key = new NamespacedKey(plugin, "BlockBraker");
            ShapedRecipe bbrep = new ShapedRecipe(key, bbdispenser);

            bbrep.shape("ABC", "DEF", "GHI");

            bbrep.setIngredient('E', Material.DISPENSER);

            bbrep.setIngredient('H', Material.IRON_PICKAXE);

            plugin.getServer().addRecipe(bbrep);


       // }
    }


}

