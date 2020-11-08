package at.gamelukas.citybuild.shop;

import at.gamelukas.citybuild.listener.Inventory;
import at.gamelukas.citybuild.main.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryListener implements Listener {
    @EventHandler
    public void onClickInv (final InventoryClickEvent e) {

        if (e.getInventory().getTitle() != null && e.getInventory().getTitle().equals("Einkaufen")) {
            e.setCancelled(true);
            FileConfiguration config = Main.getPlugin().getConfig();
            for (int i = 1; i <=  config.getInt("Shop.Items"); i++) {
                if (e.getCurrentItem().getType().equals(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")))) {
                    Player p = (Player) e.getWhoClicked();
                    if (!Main.getEconomy().hasAccount(p)) {
                        Main.getEconomy().createPlayerAccount(p);
                    }

                    double money = Main.getEconomy().getBalance(p);

                    if (e.isShiftClick()) {
                        if (money >= config.getDouble("Shop.Item" + i + ".price") * 64) {
                            Main.getEconomy().withdrawPlayer(p, config.getInt("Shop.Item" + i + ".price") * 64);
                            PlayerInventory inv = p.getInventory();
                            inv.addItem(new ItemStack(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")), 64));
                            p.sendMessage(Main.getPrefix() + "§7neuer Kontostand: §a" + Main.getEconomy().getBalance(p) + "€");
                        } else {
                            p.sendMessage(Main.getPrefix() + "§cDu hast zu wenig Geld!");
                        }
                    } else {
                        if (money >= config.getDouble("Shop.Item" + i + ".price")) {
                            Main.getEconomy().withdrawPlayer(p, config.getInt("Shop.Item" + i + ".price"));
                            PlayerInventory inv = p.getInventory();
                            inv.addItem(new ItemStack(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")), 1));
                            p.sendMessage(Main.getPrefix() + "§7neuer Kontostand: §a" + Main.getEconomy().getBalance(p) + "€");
                        } else {
                            p.sendMessage(Main.getPrefix() + "§cDu hast zu wenig Geld!");
                        }
                    }




                }

            }
            
        } else  if (e.getInventory().getTitle() != null && e.getInventory().getTitle().equals("Verkaufen")) {
            e.setCancelled(true);
            FileConfiguration config = Main.getPlugin().getConfig();
            for (int i = 1; i <=  config.getInt("Shop.Items"); i++) {
                if (e.getCurrentItem().getType().equals(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")))) {
                    Player p = (Player) e.getWhoClicked();
                    if (!Main.getEconomy().hasAccount(p)) {
                        Main.getEconomy().createPlayerAccount(p);
                    }

                    double money = Main.getEconomy().getBalance(p);

                    if (e.isShiftClick()) {
                        if (p.getInventory().contains(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")))) {
                            int counter = 0;
                            for (ItemStack is: p.getInventory().getContents()) {
                                if (is != null && is.getType() == Material.getMaterial(config.getString("Shop.Item" + i + ".Material"))) {
                                    counter += is.getAmount();
                                }
                            }
                            Main.getEconomy().depositPlayer(p, config.getDouble("Shop.Item" + i + ".price") * counter);
                            PlayerInventory inv = p.getInventory();
                            p.getInventory().removeItem(new ItemStack(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")), counter));
                            p.sendMessage(Main.getPrefix() + "§7neuer Kontostand: §a" + Main.getEconomy().getBalance(p) + "€");
                        } else {
                            p.sendMessage(Main.getPrefix() + "§cDu das geforderte Item nicht!");
                        }

                    } else {
                        if (p.getInventory().contains(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")))) {
                            Main.getEconomy().depositPlayer(p, config.getDouble("Shop.Item" + i + ".price"));
                            PlayerInventory inv = p.getInventory();
                            p.getInventory().removeItem(new ItemStack(Material.getMaterial(config.getString("Shop.Item" + i + ".Material")), 1));
                            p.sendMessage(Main.getPrefix() + "§7neuer Kontostand: §a" + Main.getEconomy().getBalance(p) + "€");
                        } else {
                            p.sendMessage(Main.getPrefix() + "§cDu das geforderte Item nicht!");
                        }
                    }




                }

            }
        }
    }
}

