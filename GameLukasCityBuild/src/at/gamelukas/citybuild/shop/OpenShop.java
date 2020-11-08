package at.gamelukas.citybuild.shop;

import at.gamelukas.citybuild.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class OpenShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Inventory inv = Bukkit.createInventory(null, 9*3, "Einkaufen");
        FileConfiguration config = Main.getPlugin().getConfig();
        for (int i = 1; i <= config.getInt("Shop.Items"); i++) {
            Material material = Material.getMaterial(config.getString("Shop.Item" + i + ".Material"));
            inv.addItem(genItem(material, config.getString("Shop.Item" + i + ".Name"), config.getDouble("Shop.Item" + i + ".price")));
        }
        p.openInventory(inv);
        return false;
    }


    public ItemStack genItem(Material material, String name, double price) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§a1x Preis: " + price + "€");
        lore.add("§a64x Preis: " + price * 64 + "€");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
