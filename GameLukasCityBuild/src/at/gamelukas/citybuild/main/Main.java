package at.gamelukas.citybuild.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import at.gamelukas.citybuild.commands.SetSpawn;
import at.gamelukas.citybuild.commands.SetWarp;
import at.gamelukas.citybuild.commands.Sign;
import at.gamelukas.citybuild.commands.SilentJoin;
import at.gamelukas.citybuild.commands.Spawn;
import at.gamelukas.citybuild.commands.Warp;
import at.gamelukas.citybuild.commands.Warps;
import at.gamelukas.citybuild.commands.Wartung;
import at.gamelukas.citybuild.commands.addAkte;
import at.gamelukas.citybuild.commands.readAkte;
import at.gamelukas.citybuild.listener.DeathListener;
import at.gamelukas.citybuild.listener.JoinListener;





public class Main extends JavaPlugin {
	
	
	public static Main plugin;
	
	static boolean wartungen = false;
	
	int message = 1;
	
	static Map<String, Long> signCooldown = new HashMap<String, Long>();


	public static Map<String, Long> getSignCooldown() {
		return signCooldown;
	}

	public static void setSignCooldown(Map<String, Long> signCooldown) {
		Main.signCooldown = signCooldown;
	}

	@Override
	public void onEnable() {
		
		

		plugin = this;
		FileConfiguration config = this.getConfig();
		
		if (config.getString("prefix") == null) {
			config.set("prefix", "§aCityBuild | ");
		}
		
		//save
		saveConfig();
		

		this.getCommand("spawn").setExecutor(new Spawn());
		this.getCommand("setspawn").setExecutor(new SetSpawn());
		this.getCommand("silentjoin").setExecutor(new SilentJoin());
		this.getCommand("setwarp").setExecutor(new SetWarp());
		this.getCommand("warp").setExecutor(new Warp());
		this.getCommand("addakte").setExecutor(new addAkte());
		this.getCommand("readakte").setExecutor(new readAkte());
		this.getCommand("sign").setExecutor(new Sign());
		this.getCommand("warps").setExecutor(new Warps());
		this.getCommand("wartung").setExecutor(new Wartung());
		
		Bukkit.getConsoleSender().sendMessage("§3GameLukasCB §9| §aEnabled");
		if (config.getDouble("CB.Spawn.X") == 0) {
			Bukkit.getConsoleSender().sendMessage("§3GameLukasCB §9|§c Spawn unset");
		}
		
		
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		

		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab reload");
				System.out.println("Tab Reloaded");
				
				
			}
			
		}, 40, 800);
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			
			
			@Override
			public void run() {
				
				if (message == 1) {
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "§bMit /shop kannst du den Shop öffnen!");
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					message++;
				} else if (message == 2) {
					
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "§bAchtung! In unserer Farmwelt ist PVP aktiv!");
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					message++;

				} else if (message == 3) {
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "§bWenn du bugs findest melde diese bitte im Teamspeak.");
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					message++;
				} else if (message == 4) {
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "§bMit /warp Farmwelt kommst du in die Farmwelt");
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					message++;
				} else if (message == 5 ) {
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "§cUPDATE: §bDu kannst jetzt Clans erstellen. Gebe einfach mal /clan ein.");
					Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m----------------------------------");
					message = 1;
				}
				
				
				
			}
			
		}, 10000, 36000);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			
			
			@Override
			public void run() {
				
			
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give * 20");
				Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m-------------------------------------------");
				Bukkit.broadcastMessage(config.getString("prefix") + "§bDu hast deinen stündlichen Aktivitätsbonus erhalten!");
				Bukkit.broadcastMessage(config.getString("prefix") + "§7§l§m-------------------------------------------");
				
				
				
			}
			
		}, 72000, 72000);
		

	}

	public static Main getPlugin() {
		return plugin;
	}
	
	public static boolean isWartungen() {
		return wartungen;
	}

	public static void setWartungen(boolean wartungen) {
		Main.wartungen = wartungen;
	}

	

}
