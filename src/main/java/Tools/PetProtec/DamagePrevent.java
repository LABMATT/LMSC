package Tools.PetProtec;

import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamagePrevent implements Listener {

    //Checks if a mob is name tagged. If it is then prevent it from making damage.
    @EventHandler
    public void preventMobDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Monster) {
            if (event.getDamager().getCustomName() != null) {
                event.setCancelled(true);
            }
        }
    }
}
