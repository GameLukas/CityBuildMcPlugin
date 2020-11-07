package at.gamelukas.citybuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class Wartung implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		// TODO Auto-generated method stub
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		
		if (p.hasPermission("CB.wartungen.toggle" )) {
			if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("true")) {
					
					Main.setWartungen(true);
					p.sendMessage(Main.getPrefix() + "§aWartung: AN");
					
					for (Player i : Bukkit.getOnlinePlayers()) {
						
						if (!i.hasPermission("CB.wartungen.join")) {
							i.kickPlayer("§cWartungsarbeiten");
						}
						
					}
					
				} else if (args[0].equalsIgnoreCase("false")) {
					
					Main.setWartungen(false);
					
					p.sendMessage(Main.getPrefix() + "§aWartung: AUS");
					
				} else {
					p.sendMessage(Main.getPrefix() + "§c/wartung [true/false]");
				}
				
			} else {
				p.sendMessage(Main.getPrefix() + "§c/wartung [true/false]");
			}
		} else {
			p.sendMessage(Main.getPrefix() + "§cDir fehlen die benötigten Rechte!");
		}
		
		return false;
	}

}
