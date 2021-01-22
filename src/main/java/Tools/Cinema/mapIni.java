package Tools.Cinema;

import SubActions.Logout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import javax.swing.*;

public class mapIni implements Listener {

    private LABMATT_SERVER_CONTROLLER plugin;

    public mapIni(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void mapinievent(MapInitializeEvent event) {
        Logout log = new Logout(plugin);

        MapView map = event.getMap();

        for(MapRenderer r : map.getRenderers())
        {
            map.removeRenderer(r);
        }

        map.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                mapCanvas.drawText(10,10, MinecraftFont.Font, "Hello Matt");
                //mapCanvas.drawImage(20, 20, new ImageIcon());
            }
        });

    }
}
