package Tools.Signage;

import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.UUID;

public class RenderSign {

    private final LABMATT_SERVER_CONTROLLER plugin;

    public RenderSign(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    public void RenderNewSign()
    {
       // plugin.getServer().getMap()
        MapView map = (MapView) plugin.getServer().getEntity(UUID.fromString("72f06619-44f3-4750-8884-b15d996107b0"));


                for(MapRenderer ren : map.getRenderers())
                {
                    map.removeRenderer(ren);
                }

                map.addRenderer(new MapRender());
    }
}
