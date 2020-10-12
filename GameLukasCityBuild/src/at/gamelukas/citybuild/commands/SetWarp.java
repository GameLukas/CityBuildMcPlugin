package at.gamelukas.citybuild.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class SetWarp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		String warpName = args[0].toLowerCase();
		
		if (p.hasPermission("CB.setwarp")) {
			if (args.length == 1) {
				
				config.set("CB.Warp."+ warpName +".X", p.getLocation().getX());
				config.set("CB.Warp."+ warpName +".Y", p.getLocation().getY());
				config.set("CB.Warp."+ warpName +".Z", p.getLocation().getZ());
				config.set("CB.Warp."+ warpName +".Pitch", p.getLocation().getPitch());
				config.set("CB.Warp." + warpName + ".Yaw", p.getLocation().getYaw());
				config.set("CB.Warp." + warpName + ".World", p.getLocation().getWorld().getName());
				
				
				Main.getPlugin().saveConfig();
				
				p.sendMessage(Main.getPrefix() + "�aWarp gesetzt");
				
				
				
				
			} else {
				p.sendMessage(Main.getPrefix() + "�cBitte verwende /setwarp [warpname]");
			}
		} else {
			p.sendMessage(Main.getPrefix() + "�cDir fehlen die ben�tigten Rechte!");
		}
		
		
		return false;
	}

}
