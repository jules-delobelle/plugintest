package fr.akaazee.plugintest.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage("§eMessage §bTest");
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("alert")) {
				
				//alert --> aucun argument
				if(args.length == 0) {
					player.sendMessage("la commande est : /alerte <message>");
				}
				//alert <texte texte texte>
				else{
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part + " ");
					}
					Bukkit.broadcastMessage("[" + player.getName() + "] " + bc.toString());
				}
				return true;
			}
		}
		
		return false;
	}

}

