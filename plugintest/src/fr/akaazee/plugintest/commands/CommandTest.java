package fr.akaazee.plugintest.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.akaazee.plugintest.Main;

public class CommandTest implements CommandExecutor {
	
	private Main main;

	public CommandTest(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			if(cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage(main.getConfig().getString("message.test").replace('&', '§'));
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
			if(cmd.getName().equalsIgnoreCase("worldlist")) {
				String list = "List of the worlds : ";
				for (World w : Bukkit.getServer().getWorlds()) {
					list += w.getName() + ", ";
				}
				player.sendMessage(list);
			}
			if(cmd.getName().equalsIgnoreCase("swapworld")) {
				
				if(args.length > 1) return false;
				
				World targetWorld = Bukkit.getWorld(args[0]);
				
				if(targetWorld != null) {
					player.teleport(targetWorld.getSpawnLocation());
					player.sendMessage("Vous avez été téléporté vers " + targetWorld.getName());
				}else {
					player.sendMessage("§4Ce monde n'existe pas");
				}
			}
		}
		
		return false;
	}

}

