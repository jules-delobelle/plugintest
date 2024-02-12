package fr.akaazee.plugintest.commands;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Random random = new Random();
			Player player = (Player)sender;
			Location ploc = player.getLocation();
			
			Location spawn = new Location(player.getWorld(), ploc.getX() + random.nextInt(50), ploc.getY() + random.nextInt(50), ploc.getZ() + random.nextInt(50), -90f, 0f);
			player.sendMessage("§aVous êtes au spawn du serveur");
			player.teleport(spawn);
		}
		return false;
	}

}
