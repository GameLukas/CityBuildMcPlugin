package at.gamelukas.citybuild.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;


public class Warps implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		
		p.sendMessage(Main.getPrefix() + "§bBenutze /warp Farmwelt um in die Farmwelt zu gelangen!");
		
		
	
		return false;
	}

}
