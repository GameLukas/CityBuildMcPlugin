package at.gamelukas.citybuild.commands.team;


import at.gamelukas.citybuild.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        FileConfiguration config = Main.getPlugin().getConfig();
        if (p.hasPermission("CB.fly")) {
            if (!Main.getPlayerFlying().containsKey(p.getName())) {
                Main.getPlayerFlying().put(p.getName(), false);
            }
            if (Main.getPlayerFlying().get(p.getName()) == false) {
                Main.getPlayerFlying().replace(p.getName(), true);
                p.setAllowFlight(true);
                p.sendMessage(config.getString("prefix") + "§aFlugmodus AN");
            } else {
                Main.getPlayerFlying().replace(p.getName(), false);
                p.setAllowFlight(false);
                p.sendMessage(config.getString("prefix") + "§aFlugmodus AUS");
            }
        } else {
            p.sendMessage(config.getString("prefix") + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
