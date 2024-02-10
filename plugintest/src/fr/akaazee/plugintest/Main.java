package fr.akaazee.plugintest;

import org.bukkit.plugin.java.JavaPlugin;

import fr.akaazee.plugintest.commands.CommandSpawn;
import fr.akaazee.plugintest.commands.CommandTest;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Le plugin viens de s'allumer");
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getServer().getPluginManager().registerEvents(new PluginListerners(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("Le plugin viens de s'éteindre");	
	}
	
}
