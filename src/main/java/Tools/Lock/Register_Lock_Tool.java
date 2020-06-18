package Tools.Lock;

import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class Register_Lock_Tool {

    private LABMATT_SERVER_CONTROLLER plugin;

    public Register_Lock_Tool(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void registerLock() {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("locktool")) {
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Lock by LMSC is active on this server.");

            new UnLock(plugin);
            new Lock(plugin);

            if (plugin.getConfig().getBoolean("debug")) {
                new Set_Correct_BlockType(plugin);
            }

            plugin.getServer().getPluginManager().registerEvents(new Lock_Events(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Break_Event(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Explosion_Damage(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Block_Broken_Near_Locked_Event(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Place_Near_Locked_Event(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_PistonEvent(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Under_Explode_Event(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Block_Burn_Event(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Event_Villeger(plugin), plugin);
            plugin.getServer().getPluginManager().registerEvents(new Lock_Event_Entity_Brake_Door(plugin), plugin);
            //plugin.getServer().getPluginManager().registerEvents(new Lock_Redstone_event(plugin), plugin);

            //No working
            //plugin.getServer().getPluginManager().registerEvents(new LockUnauthorisedUsage(plugin), plugin);

        }
    }
}
