package Tools.CraftExtra;

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

class CraftSpawner {

    private LABMATT_SERVER_CONTROLLER plugin;

    CraftSpawner(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void createSpawner() {
        ConfigManger config = new ConfigManger(plugin);

        if (config.getOldConfig("CraftExtraConfig").getBoolean("spawner")) {
            regSpanwer();
        }
    }

    private void regSpanwer() {
        ShapedRecipe LMSCSpanwer = new ShapedRecipe(new NamespacedKey(plugin, "LMSCspawner"), new ItemStack(Material.SPAWNER)).shape("BBB", "BDB", "BPB");
        //ABC
        //DEF
        //GHI

        LMSCSpanwer.setIngredient('B', Material.IRON_BARS);
        LMSCSpanwer.setIngredient('D', Material.DISPENSER);
        LMSCSpanwer.setIngredient('P', Material.BLAZE_POWDER);

        Logout log = new Logout(plugin);
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "CraftSpawner was: " + LMSCSpanwer.getChoiceMap());

        plugin.getServer().addRecipe(LMSCSpanwer);

    }
}
