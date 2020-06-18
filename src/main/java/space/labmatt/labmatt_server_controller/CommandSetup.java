package space.labmatt.labmatt_server_controller;

import Commands.*;
import Tools.FeedBackSystem.FeedBackCommand;

class CommandSetup {

    private LABMATT_SERVER_CONTROLLER plugin;

    CommandSetup(LABMATT_SERVER_CONTROLLER labmatt_server_controller) {
        this.plugin = labmatt_server_controller;
    }

    void CommandReg() {
        //New commands
        new Stat(plugin);
        new LMSCCleanCommand(plugin);
        //new PizzaTime(plugin);
        //new PlayMusic(plugin);
        new See(plugin);
        new GetPlayers(plugin);
        new GetDistanceBetweenPlayers(plugin);
        new FeedBackCommand(plugin);
        new ToggleLMSCSetting(plugin);
        //new Last_Seen(plugin);
    }
}
