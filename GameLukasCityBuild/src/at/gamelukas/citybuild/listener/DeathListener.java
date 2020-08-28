package at.gamelukas.citybuild.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import at.gamelukas.citybuild.main.Main;

public class DeathListener implements Listener {
	
	@EventHandler
	public void onDeath(final PlayerDeathEvent e) {
		FileConfiguration config = Main.getPlugin().getConfig();
		
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() != null) {
				e.setDeathMessage(config.getString("prefix") + "§7Der Spieler §a" + e.getEntity().getName() + "§7 wurde von §c" + e.getEntity().getKiller().getName() + " §7getötet.");
			} else {
				e.setDeathMessage(config.getString("prefix") + "§7Der Spieler §a" + e.getEntity().getName() + "§7 ist gestorben");
			}
		}	
	}
}
