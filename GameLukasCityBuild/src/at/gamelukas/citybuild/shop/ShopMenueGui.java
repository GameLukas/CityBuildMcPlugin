package at.gamelukas.citybuild.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopMenueGui implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        Inventory inv = Bukkit.createInventory(null, 9*3, "Shop");
        inv.setItem(2 + 9, genItem(Material.DIAMOND, "§aEinkaufen"));
        inv.setItem(7 + 9, genItem(Material.GOLD_INGOT, "§aVerkaufen"));
        p.openInventory(inv);
        return false;
    }

    public ItemStack genItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
