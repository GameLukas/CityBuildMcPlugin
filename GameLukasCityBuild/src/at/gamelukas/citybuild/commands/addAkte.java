package at.gamelukas.citybuild.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class addAkte implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		Player p = (Player) sender;
		
		boolean isPlayerOnline = false;
		
		
		if (p.hasPermission("CB.addAkte" )) {
			
	
		if (args.length == 2) {
			
			String player = args[0];
			String reason = args[1];

			try {
				
				isPlayerOnline = Bukkit.getPlayer(player).isOnline();
				
			} catch (Exception e) {
				
				isPlayerOnline = false;
				
			}
			
		    LocalDate date = LocalDate.now(); // Create a date object
		    LocalTime time = LocalTime.now();
		    String dateTime = Integer.toString(date.getDayOfMonth());
		    dateTime += ("." + date.getMonthValue());
		    dateTime += ("." + date.getYear());
		    dateTime += (" | " + time.getHour());
		    dateTime += (":" + time.getMinute());
		    
		    String shortDate = Integer.toString(date.getDayOfMonth());
		    shortDate += ".";
		    shortDate += date.getMonthValue();
		    shortDate += ".";
		    shortDate += date.getYear();
		    
		    
		    String onlinePlayerList = "";
		    
		    for(Player fPlayer : Bukkit.getOnlinePlayers()){
		    	onlinePlayerList += (fPlayer.getName() + ",");
		    }
		    
		    System.out.println(dateTime);
		    
			if (isPlayerOnline) {
				for (int i = 0; i < 10000; i++) {
					
					if (config.getString("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reason") == null) {
						p.sendMessage("Set " + i);
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reason", reason);
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Date", dateTime);
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".shortDate", shortDate);
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reporter", p.getName());
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".Reported", Bukkit.getPlayer(player).getName());
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".OnlineAmount", Bukkit.getOnlinePlayers().size());
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".OnlineList", onlinePlayerList);
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".ReportedLocation.X", (int)(Bukkit.getPlayer(player).getLocation().getX()));
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".ReportedLocation.Y", (int)(Bukkit.getPlayer(player).getLocation().getY()));
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".ReportedLocation.Z", (int)(Bukkit.getPlayer(player).getLocation().getZ()));
						config.set("CB.Akte." + Bukkit.getPlayer(player).getUniqueId() + "." + i + ".ReportedLocation.World", Bukkit.getPlayer(player).getLocation().getWorld().getName());
						Main.getPlugin().saveConfig();
						p.sendMessage(Main.getPrefix() + "�aEintrag gespeichert! (" + i + ")");
						break;
					} else {
						continue;
					}
					
				}
			} else {
				p.sendMessage(Main.getPrefix() + "�cDieser Spieler ist nicht online!");
			}
			

		} else {
			p.sendMessage(Main.getPrefix() + "�c/addakte [Name] [Grund]");
		}
		
		} else {
			p.sendMessage(Main.getPrefix() + "�cDir fehlen die ben�tigten Rechte");
		}

		
		return false;
	}

	
}
