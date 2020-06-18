package Tools.CraftExtra;

import Mangers.ConfigManger;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

class CraftSpawnEggs {

    private LABMATT_SERVER_CONTROLLER plugin;

    CraftSpawnEggs(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    void addCraftSpawnEggs() {
        ConfigManger config = new ConfigManger(plugin);
        if (config.getOldConfig("CraftExtraConfig").getBoolean("spawner")) {
            creeper();
            zombie();
            skeleton();
            spider();
            blase();
            slime();
            rabbit();
        }
    }

    private void creeper() {
        ItemStack spawnerStack = new ItemStack(Material.CREEPER_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "CreeperEgg");
        ShapedRecipe creeperEgg = new ShapedRecipe(key, spawnerStack);

        creeperEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        creeperEgg.setIngredient('A', Material.EGG);
        creeperEgg.setIngredient('B', Material.EGG);
        creeperEgg.setIngredient('C', Material.EGG);

        creeperEgg.setIngredient('D', Material.EGG);
        creeperEgg.setIngredient('E', Material.GUNPOWDER);
        creeperEgg.setIngredient('F', Material.EGG);

        creeperEgg.setIngredient('G', Material.EGG);
        creeperEgg.setIngredient('H', Material.EGG);
        creeperEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(creeperEgg);
    }

    private void zombie() {
        ItemStack spawnerStack = new ItemStack(Material.ZOMBIE_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "Zombieegg");
        ShapedRecipe zombieEgg = new ShapedRecipe(key, spawnerStack);

        zombieEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        zombieEgg.setIngredient('A', Material.EGG);
        zombieEgg.setIngredient('B', Material.EGG);
        zombieEgg.setIngredient('C', Material.EGG);

        zombieEgg.setIngredient('D', Material.EGG);
        zombieEgg.setIngredient('E', Material.ROTTEN_FLESH);
        zombieEgg.setIngredient('F', Material.EGG);

        zombieEgg.setIngredient('G', Material.EGG);
        zombieEgg.setIngredient('H', Material.EGG);
        zombieEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(zombieEgg);
    }

    private void spider() {
        ItemStack spawnerStack = new ItemStack(Material.SPIDER_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "spiderEgg");
        ShapedRecipe spiderEgg = new ShapedRecipe(key, spawnerStack);

        spiderEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        spiderEgg.setIngredient('A', Material.EGG);
        spiderEgg.setIngredient('B', Material.EGG);
        spiderEgg.setIngredient('C', Material.EGG);

        spiderEgg.setIngredient('D', Material.EGG);
        spiderEgg.setIngredient('E', Material.SPIDER_EYE);
        spiderEgg.setIngredient('F', Material.EGG);

        spiderEgg.setIngredient('G', Material.EGG);
        spiderEgg.setIngredient('H', Material.EGG);
        spiderEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(spiderEgg);
    }

    private void skeleton() {
        ItemStack spawnerStack = new ItemStack(Material.SKELETON_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "skellyegg");
        ShapedRecipe skeletonEgg = new ShapedRecipe(key, spawnerStack);

        skeletonEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        skeletonEgg.setIngredient('A', Material.EGG);
        skeletonEgg.setIngredient('B', Material.EGG);
        skeletonEgg.setIngredient('C', Material.EGG);

        skeletonEgg.setIngredient('D', Material.EGG);
        skeletonEgg.setIngredient('E', Material.BONE);
        skeletonEgg.setIngredient('F', Material.EGG);

        skeletonEgg.setIngredient('G', Material.EGG);
        skeletonEgg.setIngredient('H', Material.EGG);
        skeletonEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(skeletonEgg);
    }

    private void blase() {
        ItemStack spawnerStack = new ItemStack(Material.BLAZE_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "blazeEgg");
        ShapedRecipe blaseEgg = new ShapedRecipe(key, spawnerStack);

        blaseEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        blaseEgg.setIngredient('A', Material.EGG);
        blaseEgg.setIngredient('B', Material.EGG);
        blaseEgg.setIngredient('C', Material.EGG);

        blaseEgg.setIngredient('D', Material.EGG);
        blaseEgg.setIngredient('E', Material.BLAZE_ROD);
        blaseEgg.setIngredient('F', Material.EGG);

        blaseEgg.setIngredient('G', Material.EGG);
        blaseEgg.setIngredient('H', Material.EGG);
        blaseEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(blaseEgg);
    }

    private void slime() {
        ItemStack spawnerStack = new ItemStack(Material.SLIME_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "SlimeEgg");
        ShapedRecipe slimeEgg = new ShapedRecipe(key, spawnerStack);

        slimeEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        slimeEgg.setIngredient('A', Material.EGG);
        slimeEgg.setIngredient('B', Material.EGG);
        slimeEgg.setIngredient('C', Material.EGG);

        slimeEgg.setIngredient('D', Material.EGG);
        slimeEgg.setIngredient('E', Material.SLIME_BALL);
        slimeEgg.setIngredient('F', Material.EGG);

        slimeEgg.setIngredient('G', Material.EGG);
        slimeEgg.setIngredient('H', Material.EGG);
        slimeEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(slimeEgg);
    }

    private void rabbit() {
        ItemStack spawnerStack = new ItemStack(Material.RABBIT_SPAWN_EGG);

        NamespacedKey key = new NamespacedKey(plugin, "RabbitEgg");
        ShapedRecipe RabbitEgg = new ShapedRecipe(key, spawnerStack);

        RabbitEgg.shape("ABC", "DEF", "GHI");
        //ABC
        //DEF
        //GHI

        RabbitEgg.setIngredient('A', Material.EGG);
        RabbitEgg.setIngredient('B', Material.EGG);
        RabbitEgg.setIngredient('C', Material.EGG);

        RabbitEgg.setIngredient('D', Material.EGG);
        RabbitEgg.setIngredient('E', Material.RABBIT_FOOT);
        RabbitEgg.setIngredient('F', Material.EGG);

        RabbitEgg.setIngredient('G', Material.EGG);
        RabbitEgg.setIngredient('H', Material.EGG);
        RabbitEgg.setIngredient('I', Material.EGG);

        plugin.getServer().addRecipe(RabbitEgg);
    }

}
