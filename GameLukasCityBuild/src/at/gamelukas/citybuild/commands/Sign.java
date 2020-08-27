package at.gamelukas.citybuild.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import at.gamelukas.citybuild.main.Main;


public class Sign implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		// TODO Auto-generated method stub
		
		Player p = (Player) sender;
		FileConfiguration config = Main.getPlugin().getConfig();
		
		
		if (p.hasPermission("CB.sign")) {
			
				
				//check if Player in hashmap
				if (Main.getSignCooldown().containsKey(p.getName())) {
					
					if (Main.getSignCooldown().get(p.getName()) > System.currentTimeMillis() && !p.hasPermission("CB.sign.bypasscooldown")) {
						
						long timeLeft = ((Main.getSignCooldown().get(p.getName()) - System.currentTimeMillis()) / 1000);
						
						
						p.sendMessage(config.getString("prefix") + "§cDu musst noch " + (timeLeft / 3600) + " Stunden und " + (timeLeft / 60 - ((timeLeft / 3600) * 60)) + " Minuten warten.");
						
					
						return false;
					}
					
				}
					
					
					
					LocalDate date = LocalDate.now(); 
					
					ItemStack item = p.getItemInHand();
					
					String loreOwn = "§7";
					
					for (int i = 0; i < args.length; i++) {
						loreOwn += (args[i] + " ");
					}
					
					if (item.getType() != Material.AIR) {
					
						
						ItemMeta meta = item.getItemMeta();
						ArrayList<String> lore = new ArrayList<String>();
						lore.add("§7Signiert von §b" + p.getName());
						lore.add("§7am §b" + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
						lore.add(loreOwn);
						meta.setLore(lore);
						item.setItemMeta(meta);
						//Cooldown
						Main.getSignCooldown().put(p.getName(), System.currentTimeMillis() + (12 * 60 * 60 * 1000));
						p.sendMessage(config.getString("prefix") + "§aDas Item in deiner Hand wurde erfolgreich signiert.");
							
					} else {
						p.sendMessage(config.getString("prefix") + "§cDu darfst das Item in deiner Hand nicht signieren.");
					}


				
				

			
			
			
		} else {
			p.sendMessage(config.getString("prefix") + "§cDazu hast du keine Rechte!");
		}
		
		
		

		
			
		
		
		return false;
	}
	
	

}
