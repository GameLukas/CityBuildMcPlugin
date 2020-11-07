package at.gamelukas.citybuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		
		Player p = (Player) sender;
		
		double x = config.getDouble("CB.Spawn.X");
		double y = config.getDouble("CB.Spawn.Y");
		double z = config.getDouble("CB.Spawn.Z");
		int pitch = config.getInt("CB.Spawn.Pitch");
		int yaw = config.getInt("CB.Spawn.Yaw");
		String worldName = config.getString("CB.Spawn.World");
		
		World world = Bukkit.getWorld(worldName);
		
		Location loc = new Location(world, x, y, z, yaw, pitch);
		
		p.teleport(loc);
		p.sendMessage(Main.getPrefix() + "§aDu wurdest zum Spawn teleportiert!");
		
		return false;
	}

}
