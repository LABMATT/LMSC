package ExternalManger.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JPanel MainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel stats;
    private JLabel players;
    private JLabel spigot;
    private JLabel mcvers;
    private JLabel port;
    private JLabel ip;
    private JLabel nameMODT;

    Color gray = new Color(55, 55, 55);
    Color light = new Color(69, 69, 69);
    Color superLight = new Color(90, 90, 90);
    Font f = new Font("Times", Font.BOLD, 12);
    JFrame frame;

    public void crateGUI() {
        System.out.println("UI Made!");
        frame = new JFrame("LABMATT BACKUP");
        frame.setSize(1000, 600);
        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setMaximumSize(new Dimension(1000, 600));
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(gray);

        //frame.getContentPane().add(MainPanel);
        frame.setVisible(true);

    }

}
