package at.gamelukas.citybuild.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShowDailyChallenges implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

		Player p = (Player) sender;
		//Noch nicht offen
		if (p.isOp()) {
			Inventory gui = Bukkit.createInventory(null, 9, "Tägliche Aufgaben");
			gui.setItem(4, createItem(Material.BED, "§3Crafte ein Bett"));
			p.openInventory(gui);
		}

		
		ItemStack[] quests = {createItem(Material.BED, "§3Crafte ein Bett")};
		
		return false;
	}
	
	
	
	public static ItemStack createItem(Material itemMaterial, String itemName) {
		ItemStack item = new ItemStack(itemMaterial);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(itemName);
		ArrayList<String> list = new ArrayList<String>();
		list.add("§cNoch nicht abgeschlossen");
		meta.setLore(list);
		item.setItemMeta(meta);
		return item;
	}

}
