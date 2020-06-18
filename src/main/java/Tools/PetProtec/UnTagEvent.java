package Tools.PetProtec;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import space.labmatt.labmatt_server_controller.LABMATT_SERVER_CONTROLLER;

import java.util.Objects;

public class UnTagEvent implements Listener {
    private LABMATT_SERVER_CONTROLLER plugin;

    UnTagEvent(LABMATT_SERVER_CONTROLLER plugin) {
        this.plugin = plugin;
    }

    //Checks if a player has damged a mob. If they have then see if there holding a stick named "untag". If they are and there op then unname tag the mob.
    @EventHandler
    public void unTagEntityEvent(EntityDamageByEntityEvent event) {
        {
            if (event.getDamager() instanceof Player) {
                Player plr = ((Player) event.getDamager()).getPlayer();
                assert plr != null;
                if (plr.isOp()) {
                    if (plr.getInventory().getItemInMainHand().getType() == Material.STICK) {
                        if (Objects.requireNonNull(plr.getInventory().getItemInMainHand().getItemMeta()).getDisplayName().toLowerCase().contains("untag")) {
                            event.getEntity().setCustomName(null);
                        }
                    }
                }
            }
        }
    }
}
