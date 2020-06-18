package Tools.ChatColours;

import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

public class RegisterChatColours {

    private LABMATT_SERVER_CONTROLLER plugin;

    public RegisterChatColours(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void regChatcolour()
    {

        plugin.reloadConfig();
        if (plugin.getConfig().getBoolean("chatColours")) {

            plugin.getServer().getPluginManager().registerEvents(new ChatColoursEvent(), plugin);
        }
    }
}
