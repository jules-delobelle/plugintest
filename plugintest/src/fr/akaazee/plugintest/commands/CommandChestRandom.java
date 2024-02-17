package fr.akaazee.plugintest.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.akaazee.plugintest.Main;

public class CommandChestRandom implements CommandExecutor {

	private Main main;
	
	public CommandChestRandom(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("event")) {
			
			Player player = (Player) sender;
			
			Random r = new Random();
			double x = r.nextInt(500);
			double y = r.nextInt(100);
			double z = r.nextInt(500);
			
			Location spawnChest = new Location(player.getWorld(), x, y, z);
			spawnChest.getBlock().setType(Material.CHEST);
			
			Chest chest = (Chest) spawnChest.getBlock().getState();
			Inventory chestMenu = chest.getInventory();
			
			if(Math.random() * 100 < 50) {
				chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.DIAMOND, 1));
			}else {
				chestMenu.setItem(r.nextInt(chestMenu.getSize()), new ItemStack(Material.IRON_SWORD));
			}
			
			
			
			
			Bukkit.broadcastMessage("Un coffre viens d'apparaître en x: " + x + " y: " + y + " z: " + z);
			
			
			return true;
		}
		
		return false;
	}

}
