package at.gamelukas.citybuild.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import at.gamelukas.citybuild.main.Main;



public class JoinListener implements Listener {
	
	FileConfiguration config = Main.getPlugin().getConfig();
	
	@EventHandler
	public void onPlayerJoin (final PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
		double x = config.getDouble("CB.Spawn.X");
		double y = config.getDouble("CB.Spawn.Y");
		double z = config.getDouble("CB.Spawn.Z");
		int pitch = config.getInt("CB.Spawn.Pitch");
		int yaw = config.getInt("CB.Spawn.Yaw");
		String worldName = config.getString("CB.Spawn.World");
		
		World world = Bukkit.getWorld(worldName);
		
		Location loc = new Location(world, x, y, z, yaw, pitch);
		
		p.teleport(loc);

		if (Main.getGmmPlayer().containsKey(p.getName())) {
			if (Main.getGmmPlayer().get(p.getName())) {
				p.sendMessage(Main.getPrefix() + "§c!!!!ACHTUNG!!!! Du bist noch im GameMasterMode");
				p.sendMessage(Main.getPrefix() + "§cBitte verlasse bei jedem logout den GameMasterMode, ansonsten kann bei einem Server-Neustart dein Inventar gelöscht werden!");
			}
		}
		
		if (config.getBoolean("Player." + p.getUniqueId() + ".silentjoin")) {
			p.sendMessage(Main.getPrefix() + "§aDeine JoinNachricht wurde nicht gesendet!");
			e.setJoinMessage(null);
		} else {
			e.setJoinMessage(Main.getPrefix() + p.getName() + " §7hat den CB-Server betreten!");
		}
		
		if (Main.isWartungen()) {
			
				if (!p.hasPermission("CB.wartungen.join")) {
					p.kickPlayer("§cWartungsarbeiten");
				} else {
					p.sendMessage("§cWARTUNGS-MODUS ist eingeschaltet.");
				}

			}
		}
	
	@EventHandler
	public void onPlayerLeft (final PlayerQuitEvent e) {
		
		Player p = (Player) e.getPlayer();
		e.setQuitMessage(Main.getPrefix() + p.getName() + " §7hat den CB-Server verlassen!");
		
	}
	
	@EventHandler
	public void onPlayerChatEvent(final PlayerCommandPreprocessEvent e) {
		String[] cmd = e.getMessage().substring(1).split(" ");
		FileConfiguration config = Main.getPlugin().getConfig();
		
		Player p = e.getPlayer();
		
		if (cmd[0].startsWith("bukkit") || cmd[0].startsWith("pl") || cmd[0].startsWith("?") || cmd[0].startsWith("help") || cmd[0].startsWith("spigot") || cmd[0].startsWith("version") || cmd[0].startsWith("pex")  || cmd[0].startsWith("server")) {
			if (!p.isOp() || !p.hasPermission("CB.cmd.use")) {
				p.sendMessage(Main.getPrefix() + "§cDu darfst diesen Command nicht verwenden!");
				e.setCancelled(true);
			}
		}
	}
	
		
		
		
	}
