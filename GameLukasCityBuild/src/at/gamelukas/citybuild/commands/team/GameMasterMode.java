package at.gamelukas.citybuild.commands.team;

import at.gamelukas.citybuild.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameMasterMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        FileConfiguration config = Main.getPlugin().getConfig();
        if (p.hasPermission("CB.gmm")) {
            if (!Main.getGmmPlayer().containsKey(p.getName())) {
                Main.getGmmPlayer().put(p.getName(), false);
            }
            if (Main.getGmmPlayer().get(p.getName()) == false) {
                Main.getGmmPlayer().replace(p.getName(), true);
                p.setAllowFlight(true);
                p.sendMessage(Main.getPrefix() + "GameMasterMode AN");
                if (!Main.getGmmLocations().containsKey(p.getName())) {
                    Main.getGmmLocations().put(p.getName(), p.getLocation());
                } else {
                    Main.getGmmLocations().replace(p.getName(), p.getLocation());
                }
                if (!Main.getGmmInvs().containsKey(p.getName())) {
                    Main.getGmmInvs().put(p.getName(), p.getInventory().getContents());
                } else {
                    Main.getGmmInvs().replace(p.getName(),p.getInventory().getContents());
                }
                p.getInventory().clear();
            } else {
                Main.getGmmPlayer().replace(p.getName(), false);
                if (p.getGameMode() != GameMode.CREATIVE) {
                    p.setAllowFlight(false);
                }
                p.sendMessage(Main.getPrefix() + "GameMasterMode AUS");
                if (!Main.getGmmLocations().containsKey(p.getName())) {
                    p.kickPlayer("Etwas ist schiefgelaufen! Location wurde nicht gespeichert!");
                } else {
                    p.teleport(Main.getGmmLocations().get(p.getName()));
                }

                ItemStack[] saveInv = Main.getGmmInvs().get(p.getName());
                p.getInventory().setContents(saveInv);

            }
        } else {
            p.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
