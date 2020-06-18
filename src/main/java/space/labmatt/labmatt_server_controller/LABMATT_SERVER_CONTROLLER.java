package space.labmatt.labmatt_server_controller;

import Action.LMSCcleanup;
import ExternalManger.GUI.CreateGUI;
import SubActions.Logout;
import Tools.Hammer.RegisterHammer;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LABMATT_SERVER_CONTROLLER extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "LMSC is attempting to start.");

        Logout log = new Logout(this);
        LMSCcleanup LMSCclean = new LMSCcleanup(this);

        //Enables debug if set to true.
        reloadConfig();
        log.activateDebug(getConfig().getBoolean("debug"));

        log.debugOut(this.getName(), ChatColor.AQUA, "Debug Online!");


        ConfigSetup configSetup = new ConfigSetup(this);
        configSetup.Config();


        //Resister new commands
        CommandSetup commandSetup = new CommandSetup(this);
        commandSetup.CommandReg();


        //Reg the events on the server
        EventSetup eventSetup = new EventSetup(this);
        eventSetup.eventReg();


        //Regsuters tasks
        TaskSetup taskSetup = new TaskSetup(this);
        taskSetup.TaskReg();


        //Registers all LMSC tools
        ToolSetup toolSetup = new ToolSetup(this);
        toolSetup.toolReg();


        //Start GUI if setting is true
        CreateGUI gui = new CreateGUI(this);
        gui.createNewGUI();

        LMSCclean.preformCleanup();

        // Plugin startup logo.
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "LMSC (LABMATT SERVER CONTROLLER) is online!");
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "LMSC by LABMATT (Matthew Lewington). Thanks for using my plugin my dude! If you find errors, issues or have suggestions please send them to labmattcontact@gmail.com or at @LABMATTs on twitter. (including java error or feedback).");
    }

    @Override
    public void onDisable() {

        RegisterHammer resetbossBar = new RegisterHammer(this);

        if(this.getConfig().getBoolean("hammer"))
        {
            resetbossBar.removeBar();
        }

        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "LABMATT server controller is offline.");
    }


}
