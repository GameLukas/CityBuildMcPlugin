package at.gamelukas.citybuild.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import at.gamelukas.citybuild.commands.SetSpawn;
import at.gamelukas.citybuild.commands.SetWarp;
import at.gamelukas.citybuild.commands.ShowDailyChallenges;
import at.gamelukas.citybuild.commands.Sign;
import at.gamelukas.citybuild.commands.SilentJoin;
import at.gamelukas.citybuild.commands.Spawn;
import at.gamelukas.citybuild.commands.Spy;
import at.gamelukas.citybuild.commands.Warp;
import at.gamelukas.citybuild.commands.Warps;
import at.gamelukas.citybuild.commands.Wartung;
import at.gamelukas.citybuild.commands.addAkte;
import at.gamelukas.citybuild.commands.readAkte;
import at.gamelukas.citybuild.listener.ChatListener;
import at.gamelukas.citybuild.listener.CraftEvent;
import at.gamelukas.citybuild.listener.DeathListener;
import at.gamelukas.citybuild.listener.Inventory;
import at.gamelukas.citybuild.listener.JoinListener;



public class Main extends JavaPlugin {
	
	
	public static Main plugin;
	
	static boolean wartungen = false;
	
	int challenges = 3;
	
	FileConfiguration config = getConfig();
	
	
	int message = 1;
	static int dailyChallenges[] = new int[3];
	int lastDailyChallengeUpdate = config.getInt("dailyChallenge.lastUpdate");
	
	public static int[] getDailyChallenges() {
		return dailyChallenges;
	} 

	public static void setDailyChallenges(int[] dailyChallenges) {
		Main.dailyChallenges = dailyChallenges;
	}

	static Map<String, Long> signCooldown = new HashMap<String, Long>();
	
	static ArrayList<Player> spyPlayers = new ArrayList<Player>(); 
	
	public static ArrayList<Player> getSpyPlayers() {
		return spyPlayers;
	}

	public static void setSpyPlayers(ArrayList<Player> spyPlayers) {
		Main.spyPlayers = spyPlayers;
	}

	@Override
	public void onEnable() {
		
		plugin = this;
		FileConfiguration config = this.getConfig();
		
		if (config.getString("prefix") == null) {
			config.set("prefix", "�aCityBuild | ");
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
		this.getCommand("spy").setExecutor(new Spy());
		this.getCommand("daily").setExecutor(new ShowDailyChallenges());
		
		Bukkit.getConsoleSender().sendMessage("�3GameLukasCB �9| �aEnabled");
		if (config.getDouble("CB.Spawn.X") == 0) {
			Bukkit.getConsoleSender().sendMessage("�3GameLukasCB �9|�c Spawn unset");
		}
		
		
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new Inventory(), this);
		Bukkit.getPluginManager().registerEvents(new CraftEvent(), this);
		

		
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
					//Bukkit.broadcastMessage(config.getString("prefix") + "�7�l�m----------------------------------");
					Bukkit.broadcastMessage(config.getString("prefix") + "�bMit /shop kannst du den Shop �ffnen!");
					
					message++;
				} else if (message == 2) {
					
					
					Bukkit.broadcastMessage(config.getString("prefix") + "�bAchtung! In unserer Farmwelt ist PVP aktiv!");
					
					message++;

				} else if (message == 3) {
					
					Bukkit.broadcastMessage(config.getString("prefix") + "�bWenn du bugs findest melde diese bitte im Teamspeak.");
					
					message++;
				} else if (message == 4) {
					
					Bukkit.broadcastMessage(config.getString("prefix") + "�bMit /warp Farmwelt kommst du in die Farmwelt");
					
					message++;
				} else if (message == 5 ) {
					
					Bukkit.broadcastMessage(config.getString("prefix") + "�cUPDATE: �bDu kannst jetzt Clans erstellen. Gebe einfach mal /clan ein.");
					
					message = 1;
				}
				
				
				
			}
			
		}, 10000, 36000);
		
		//Daily Challenge
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			LocalDate date = LocalDate.now();
			@Override
			public void run() {
				
				if (Main.getPlugin().getConfig().getInt("dailyChallenge.lastUpdate") != date.getDayOfYear()) {
					Main.getPlugin().getConfig().set("dailyChallenge.lastUpdate", date.getDayOfYear());
					Main.getPlugin().saveConfig();
					Random rdm = new Random();
					
					int lastRdmNumber = 111111;
					int lastlastRdmNumber = 111112;
					int[] challengeArray = Main.getDailyChallenges();
					
					for (int i = 0; i < 3; i++) {
						boolean same = true;
						while (same) {
							int number = rdm.nextInt(challenges);
							if (lastRdmNumber != number && lastlastRdmNumber != number) {
								challengeArray[i] = number;
								same = false;
								if (i == 1) {
									lastlastRdmNumber = number;
								} else if (i == 2) {
									lastlastRdmNumber = number;
								}
							}
						}
					}
				}
				
			}
			
		}, 100, 100);
		
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			
			
			@Override
			public void run() {
				
			
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give * 20");
				Bukkit.broadcastMessage(config.getString("prefix") + "�7�l�m-------------------------------------------");
				Bukkit.broadcastMessage(config.getString("prefix") + "�bDu hast deinen st�ndlichen Aktivit�tsbonus erhalten!");
				Bukkit.broadcastMessage(config.getString("prefix") + "�7�l�m-------------------------------------------");
				
				
				
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
	
	public static Map<String, Long> getSignCooldown() {
		return signCooldown;
	}

	public static void setSignCooldown(Map<String, Long> signCooldown) {
		Main.signCooldown = signCooldown;
	}

	

}
