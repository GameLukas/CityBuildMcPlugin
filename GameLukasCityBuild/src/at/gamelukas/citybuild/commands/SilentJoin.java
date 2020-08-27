package at.gamelukas.citybuild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import at.gamelukas.citybuild.main.Main;

public class SilentJoin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		FileConfiguration config = Main.getPlugin().getConfig();

		
		Player p = (Player) sender;
		
		if (p.hasPermission("CB.silentjoin")) {
			if (config.getBoolean("Player." + p.getUniqueId() + ".silentjoin")) {
				config.set("Player." + p.getUniqueId() + ".silentjoin", false);
				p.sendMessage(config.getString("prefix") + "silentjoin: §cfalse");
				Main.getPlugin().saveConfig();
			} else {
				config.set("Player." + p.getUniqueId() + ".silentjoin", true);
				p.sendMessage(config.getString("prefix") + "silentjoin: §atrue");
				Main.getPlugin().saveConfig();
			}
		}

		
		return false;
	}

}
