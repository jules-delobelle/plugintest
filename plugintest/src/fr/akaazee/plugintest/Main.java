package fr.akaazee.plugintest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.akaazee.plugintest.commands.CommandSpawn;
import fr.akaazee.plugintest.commands.CommandTest;
import fr.akaazee.plugintest.tasks.TimerTask;

public class Main extends JavaPlugin {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		System.out.println("Le plugin viens de s'allumer");
		getCommand("test").setExecutor(new CommandTest(this));
		getCommand("alert").setExecutor(new CommandTest(this));
		getCommand("worldlist").setExecutor(new CommandTest(this));
		getCommand("spawn").setExecutor(new CommandSpawn());
		getServer().getPluginManager().registerEvents(new PluginListerners(this), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		String[] messages = {"Bienvenue sur le serv", "apacoubeh", "crampte"};
		
		for(String string : getConfig().getConfigurationSection("kits").getKeys(false)) {
			System.out.println(getConfig().getConfigurationSection("kits").getInt(string + ".id"));
		}
		
		TimerTask task = new TimerTask();
		task.runTaskTimer(this, 0, 20);
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("Le plugin viens de s'éteindre");	
	}
	
}
