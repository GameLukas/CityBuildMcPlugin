package at.gamelukas.citybuild.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		
		Player p = (Player) sender;
		
		if (p.hasPermission("CB.setup.setspawn") || p.isOp()) {
			
			config.set("CB.Spawn.X", p.getLocation().getX());
			config.set("CB.Spawn.Y", p.getLocation().getY());
			config.set("CB.Spawn.Z", p.getLocation().getZ());
			config.set("CB.Spawn.Pitch", p.getLocation().getPitch());
			config.set("CB.Spawn.Yaw", p.getLocation().getYaw());
			config.set("CB.Spawn.World", p.getLocation().getWorld().getName());
			
			Main.getPlugin().saveConfig();
			
			p.sendMessage(Main.getPrefix() + "§aSpawn gesetzt");
			
		} else {
			
			p.sendMessage(Main.getPrefix() + "§cDir fehlen die benötigten Rechte!");
		}
		
		return false;
	}
	
	

}
