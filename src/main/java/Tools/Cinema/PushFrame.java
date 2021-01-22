package Tools.Cinema;

import SubActions.Logout;
import Tools.Hammer.HammerTransport;
import Tools.Lock.Lock_Clean_Config;
import Tools.Lock.Lock_Get_Block_Types;
import Tools.Lock.Lock_Get_Locked_Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;
import org.bukkit.scheduler.BukkitRunnable;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import javax.swing.*;

public class PushFrame extends BukkitRunnable {

    private LABMATT_SERVER_CONTROLLER plugin;

    public PushFrame(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    static int num = 0;

    @Override
    public void run() {
        plugin.getServer().getConsoleSender().sendMessage("Task worked");

        MapView map = plugin.getServer().getMap(174);

        for(MapRenderer r : map.getRenderers())
        {
            map.removeRenderer(r);
        }

        map.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                if(num == 6)
                {
                    num = 1;
                }

                switch (num)
                {
                    case 1:
                        mapCanvas.drawImage(0, 0, new ImageIcon(plugin.getDataFolder().getPath()+"/a.png").getImage());
                        break;
                    case 2:
                        mapCanvas.drawImage(0, 0, new ImageIcon(plugin.getDataFolder().getPath()+"/b.png").getImage());
                        break;
                    case 3:
                        mapCanvas.drawImage(0, 0, new ImageIcon(plugin.getDataFolder().getPath()+"/c.png").getImage());
                        break;
                    case 4:
                        mapCanvas.drawImage(0, 0, new ImageIcon(plugin.getDataFolder().getPath()+"/d.png").getImage());
                        break;
                    case 5:
                        mapCanvas.drawImage(0, 0, new ImageIcon(plugin.getDataFolder().getPath()+"/e.png").getImage());
                        break;
                }


                num++;
            }
        });
    }
}
