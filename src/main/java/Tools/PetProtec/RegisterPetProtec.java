package Tools.PetProtec;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterPetProtec {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterPetProtec(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerPetProtec() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("petprotec")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "PetProtec by LMSC is active on this server.");
            plugin.getServer().getPluginManager().registerEvents(new PetProtecEvent(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new DamagePrevent(), plugin);
            plugin.getServer().getPluginManager().registerEvents(new UnTagEvent(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new UnTameEvent(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Petprotec_Event_Mount_Horse(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Petprotec_Event_Inventory_Horse(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new PetProtec_Event_Player_Leave(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Petprotec_Event_Leash(plugin), plugin);
        }

    }
}