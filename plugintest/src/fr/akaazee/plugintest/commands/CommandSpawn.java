package fr.akaazee.plugintest.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			Location spawn = new Location(player.getWorld(), 150, 104, 85, -90f, 0f);
			player.teleport(spawn);
		}
		return false;
	}

}
