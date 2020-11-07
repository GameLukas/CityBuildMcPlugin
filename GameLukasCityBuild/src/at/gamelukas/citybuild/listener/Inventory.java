package at.gamelukas.citybuild.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class Inventory implements Listener {
	
	@EventHandler
	public void onClickInv (final InventoryClickEvent e) {
		
		if (e.getInventory().getTitle() != null && e.getInventory().getTitle().equals("Tägliche Aufgaben")) {
			e.setCancelled(true);
		}
	}

}
