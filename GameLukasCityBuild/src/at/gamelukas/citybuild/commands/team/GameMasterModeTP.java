package at.gamelukas.citybuild.commands.team;

import at.gamelukas.citybuild.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GameMasterModeTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        FileConfiguration config = Main.getPlugin().getConfig();
        if (Main.getGmmPlayer().containsKey(p.getName()) && Main.getGmmPlayer().get(p.getName())) {
            if (p.hasPermission("CB.gmm")) {
                if (strings.length == 1) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))) {
                        p.teleport(Bukkit.getPlayer(strings[0]).getLocation());
                    } else {
                        p.sendMessage(Main.getPrefix() + "§cDieser Spieler ist nicht online!");
                    }
                } else {
                    p.sendMessage(Main.getPrefix() + "§cBitte verwende /tp [Name]");
                }
            } else {
                p.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!");
            }
        } else {
            p.sendMessage(Main.getPrefix() + "§cDiesen Command kannst du nur im GameMasterMode nutzen!");
        }

        return false;
    }
}
