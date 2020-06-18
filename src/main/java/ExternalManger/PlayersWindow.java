package ExternalManger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PlayersWindow extends Thread implements ActionListener {

    public boolean end = false;


    private LABMATT_SERVER_CONTROLLER plugin;

    public PlayersWindow(LABMATT_SERVER_CONTROLLER instance) {
        this.plugin = instance;
    }

    public void run() {
        JFrame frame = new JFrame("LMSC Player Controller");
        JTree playerTree = new JTree();
        JLabel test = new JLabel();
        JMenuBar mb = new JMenuBar();
        JMenu topbar = new JMenu("Server");

        JMenuItem stop = new JMenuItem("Stop Server");
        JMenuItem restart = new JMenuItem("Restart Server");
        JMenuItem reload = new JMenuItem("Reload Server");
        JMenuItem save = new JMenuItem("Save-all Server");
        JMenuItem saveOn = new JMenuItem("Auto-Save-On");
        JMenuItem saveOff = new JMenuItem("Auto-Save-Off");

        topbar.add(stop);
        topbar.add(restart);
        topbar.add(reload);
        topbar.add(save);
        topbar.add(saveOn);
        topbar.add(saveOff);

        mb.add(topbar);

        stop.addActionListener(this);
        restart.addActionListener(this);
        reload.addActionListener(this);
        save.addActionListener(this);
        saveOn.addActionListener(this);
        saveOff.addActionListener(this);


        frame.setPreferredSize(new Dimension(500, 500));

        frame.setJMenuBar(mb);
        frame.add(test);

        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 500);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setVisible(true);

        while (true == true) {
            test.setText(plugin.getServer().getOnlinePlayers().toString());
        }

    }

    public void actionPerformed(ActionEvent action) {
        String choice = action.getActionCommand();
        switch (choice) {
            case "Stop Server":
                plugin.getServer().shutdown();
                break;
            case "Restart Server":
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Server Restarting");
                try {
                    File file = new File("./server-restart");
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "ERROR RESTARTING SERVER! Do you have a server-restart script setup in server root directory?");
                }
                break;
            case "Reload Server":
                plugin.getServer().reload();
                break;
            case "Save-all Server":
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "/save-all");

                break;
            case "Auto-Save-On":
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "/save-on");

                break;
            case "Auto-Save-Off":
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "/save-off");

                break;
            default:

                break;
        }
    }
}
