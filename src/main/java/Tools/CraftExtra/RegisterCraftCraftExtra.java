package Tools.CraftExtra;

//allows crafting of spawners and name tags

import Mangers.ConfigManger;
import SubActions.Logout;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.io.File;

public class RegisterCraftCraftExtra {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterCraftCraftExtra(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerCraftExtra() {
        Logout log = new Logout(plugin);
        ConfigManger config = new ConfigManger(plugin);

        if (!config.isFile(new File("CraftExtraConfig"))) {
            config.getOldConfig("CraftExtraConfig").set("nametag", true);
            config.saveConfig();
            config.getOldConfig("CraftExtraConfig").set("spawner", true);
            config.saveConfig();
        }

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("craftextra")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "CraftExtra by LABMATT_SERVER_CONTROLLER is active on this server.");

            log.debugOut(this.getClass().getName(), ChatColor.GREEN, "CraftExtra by LABMATT_SERVER_CONTROLLER is active on this server.");
            CraftSpawner spawner = new CraftSpawner(plugin);
            CraftNametag nameTag = new CraftNametag(plugin);
            CraftSpawnEggs eggs = new CraftSpawnEggs(plugin);

            try {
                plugin.getServer().resetRecipes();
            } catch (NullPointerException e) {
                log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "No recipes reset.");
            }
            spawner.createSpawner();
            nameTag.addCraftNameTag();
            eggs.addCraftSpawnEggs();
        }

    }

}
