package at.gamelukas.citybuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class readAkte implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		
		
		if (p.hasPermission("CB.readAkte")) {
			
			if (args.length == 1) {
				boolean isPlayerOnline = false;
				String player = args[0];
				
				try {
					
					isPlayerOnline = Bukkit.getPlayer(player).isOnline();
					
				} catch (Exception e) {
					
					isPlayerOnline = false;
					
				}
				
				
				if (isPlayerOnline) {
					
					//Messages
					p.sendMessage(Main.getPrefix() + "     �8--- �aAkte �8---");
					
					for (int i = 0; i < 10000; i++) {
						
						
						if (config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reason") != null) {
							p.sendMessage("�eID: �7 "+ i + " �8- �eReason: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reason") + "�e �8- �eDate: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".shortDate") + " �8- �eMelder: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reporter"));
						} else {
							break;
						}
						
					}
					
				} else {
					p.sendMessage(Main.getPrefix() + "�cDieser Spieler ist nicht online!");
				}
				
				
				
			} else if (args.length == 2) {
				
				boolean isPlayerOnline = false;
				String player = args[0];
				
				try {
					
					isPlayerOnline = Bukkit.getPlayer(player).isOnline();
					
				} catch (Exception e) {
					
					isPlayerOnline = false;
					
				}
				
				
				if (isPlayerOnline) {
					if (config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".Reason") != null) {
						//ID infos
						p.sendMessage(Main.getPrefix() + "     �8--- �aAkte �8---");
						p.sendMessage("�eSpieler Online: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".OnlineAmount"));
						p.sendMessage("�eSpieler liste: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".OnlineList"));
						p.sendMessage("�eReported-Player Location: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".ReportedLocation.X") + "/" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".ReportedLocation.Y") + "/" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".ReportedLocation.Z") + "/" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".ReportedLocation.World"));
						p.sendMessage("�eTime: �7" + config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + args[1] + ".Date"));
					} else {
						p.sendMessage(Main.getPrefix() + "�cDiese ID existiert nicht!");
					}
				} else {
					p.sendMessage(Main.getPrefix() + "�cDieser Spieler ist nicht online!");
				}
				

				
			} else {
				p.sendMessage(Main.getPrefix() + "�c/readakte [Name]");
			}
			

		} else {
			p.sendMessage(Main.getPrefix() + "�cDir fehlen die ben�tigten Rechte");
		}
		
		return false;
	}

}
