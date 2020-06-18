package Tools.CraftExtra;

import Mangers.ConfigManger;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

class CraftNametag {

    private LABMATT_SERVER_CONTROLLER plugin;

    CraftNametag(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    void addCraftNameTag() {
        ConfigManger config = new ConfigManger(plugin);

        if (config.getOldConfig("CraftExtraConfig").getBoolean("nametag")) {
            ItemStack nameTag = new ItemStack(Material.NAME_TAG);

            NamespacedKey key = new NamespacedKey(plugin, "NameTag");
            ShapedRecipe tag = new ShapedRecipe(key, nameTag);

            tag.shape("ABC", "DEF", "GHI");

            tag.setIngredient('A', Material.PAPER);
            tag.setIngredient('B', Material.PAPER);

            tag.setIngredient('D', Material.PAPER);
            tag.setIngredient('E', Material.PAPER);

            tag.setIngredient('I', Material.STRING);


            plugin.getServer().addRecipe(tag);
        }
    }
}
