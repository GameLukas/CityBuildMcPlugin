package at.gamelukas.citybuild.shop;

import at.gamelukas.citybuild.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AddShopItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p =(Player) commandSender;
        if (p.hasPermission("CB.setupShop")) {
            // Sellpreis = Buyprice - selldiff
            // /addshopitem [Item] [BuyPreis] [MaxBuyPreis] [MinBuyPreis] [SellDifference] [Name]
            if (strings.length == 6) {
                FileConfiguration config = Main.getPlugin().getConfig();
                int items;
                if (config.contains("Shop.Items")) {
                    items = config.getInt("Shop.Items");
                    items++;
                } else {
                    items = 1;
                    config.set("Shop.Items", 1);
                }
                config.set("Shop.Items", items);
                config.set("Shop.Item" + items + ".Material", strings[0]);
                config.set("Shop.Item" + items + ".price", Integer.parseInt(strings[1]));
                config.set("Shop.Item" + items + ".pricemin",  Integer.parseInt(strings[2]));
                config.set("Shop.Item" + items + ".pricemax",  Integer.parseInt(strings[3]));
                config.set("Shop.Item" + items + ".pricediff",  Integer.parseInt(strings[4]));
                config.set("Shop.Item" + items + ".Name", strings[5]);
            } else {
                p.sendMessage(Main.getPrefix() + "/addshopitem [Item] [BuyPreis] [MaxBuyPreis] [MinBuyPreis] [SellDifference] [Name]");
            }

            Main.getPlugin().saveConfig();
        }
        return false;
    }
}
