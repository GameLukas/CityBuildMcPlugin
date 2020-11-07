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

public class Warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		
		
		
		if (args.length == 1) {
			String warpName = args[0].toLowerCase();
			if (p.hasPermission("CB.warp." + warpName)) {
				
			
				
				System.out.println(config.getDouble("CB.Warp." + warpName + ".X"));
				
				if (config.getDouble("CB.Warp." + warpName + ".X") != 0.0) {
					double x = config.getDouble("CB.Warp." + warpName + ".X");
					double y = config.getDouble("CB.Warp."+ warpName +".Y");
					double z = config.getDouble("CB.Warp."+ warpName +".Z");
					int pitch = config.getInt("CB.Warp."+ warpName +".Pitch");
					int yaw = config.getInt("CB.Warp."+ warpName +".Yaw");
					String worldName = config.getString("CB.Warp."+ warpName +".World");
					
					World world = Bukkit.getWorld(worldName);
					
					Location loc = new Location(world, x, y, z, yaw, pitch);
					
					p.teleport(loc);
					
				} else {
					p.sendMessage(Main.getPrefix() + "§cDieser Warppunkt existiert nicht!");
				}
				
			
				
				
			} else {
				p.sendMessage(Main.getPrefix() + "§cDir fehlen die benötigten Rechte für diesen Warppunkt!");
			}
		} else {
			p.sendMessage(Main.getPrefix() + "§cBenutze /warp [Warpname]");
		}
		
		
		return false;
	}

}
