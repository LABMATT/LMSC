package Tools.Signage;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class MapRender extends MapRenderer {
    public void render(MapView view, MapCanvas canvas, Player plr) {

        try {
            canvas.drawImage(25,25, ImageIO.read(new URL("https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
