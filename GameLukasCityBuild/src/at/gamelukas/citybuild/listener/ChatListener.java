package at.gamelukas.citybuild.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import at.gamelukas.citybuild.main.Main;

public class ChatListener implements Listener {

	@EventHandler
	
	public void PlayerChat(final PlayerCommandPreprocessEvent e) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (Main.getSpyPlayers().contains(all)) {
				
				if (!e.getPlayer().getName().equals(all.getName())) {
					all.sendMessage(Main.getPrefix() + "§7[§eSpy§7] §a" + e.getPlayer().getName() + "§7 | " + e.getMessage());
				}
			}
		}
	}
	
}
