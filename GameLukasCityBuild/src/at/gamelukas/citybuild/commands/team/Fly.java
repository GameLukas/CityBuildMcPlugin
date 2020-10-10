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
            if (Main.getPlayerFlying().get(p.getName()) != null || Main.getPlayerFlying().get(p.getName()) == true) {
                p.setFlying(false);
                p.sendMessage(config.getString("prefix") + "§aFlugmodus AUS");
            } else {
                p.setFlying(true);
                p.sendMessage(config.getString("prefix") + "§aFlugmodus AN");
            }
        } else {
            p.sendMessage(config.getString("prefix") + "§cDazu hast du keine Rechte!");
        }
        return false;
    }
}
