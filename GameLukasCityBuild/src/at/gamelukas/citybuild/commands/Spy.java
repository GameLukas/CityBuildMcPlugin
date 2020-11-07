package at.gamelukas.citybuild.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class Spy implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();
		
		Player p = (Player) sender;
		
		if (p.hasPermission("CB.spy")) {
			if (args.length == 0) {
				if (Main.getSpyPlayers().contains(p)) {
					for (int i = 0; i < Main.getSpyPlayers().size(); i++) {
						if (Main.getSpyPlayers().get(i).equals(p)) {
							Main.getSpyPlayers().remove(i);
							p.sendMessage(Main.getPrefix() + "§7Spymodus deaktiviert");
						}
					}
				} else {
					Main.getSpyPlayers().add(p);
					p.sendMessage(Main.getPrefix() + "§7Spymodus aktiviert");
				}
			} else {
				p.sendMessage(Main.getPrefix() + "§c/spy");
			}
		} else {
			p.sendMessage(Main.getPrefix() + "§ckeine Rechte!");
		}
		

		return false;
	}

}
