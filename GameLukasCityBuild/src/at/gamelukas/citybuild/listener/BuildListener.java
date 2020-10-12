package at.gamelukas.citybuild.listener;

import at.gamelukas.citybuild.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent e) {
        if (Main.getGmmPlayer().containsKey(e.getPlayer().getName())) {
            if (Main.getGmmPlayer().get(e.getPlayer().getName())) {
                e.setCancelled(true);
            }
        }
    }
}
