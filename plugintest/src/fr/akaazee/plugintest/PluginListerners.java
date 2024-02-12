package fr.akaazee.plugintest;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PluginListerners implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		player.getInventory().clear();
		player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 3));
		
		ItemStack customsword = new ItemStack(Material.COMPASS, 1);
		ItemMeta customM = customsword.getItemMeta();
		customM.setDisplayName("§cépée test");
		customM.setLore(Arrays.asList("premiere ligne", "deuxième ligne"));
		customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customsword.setItemMeta(customM);
		
		player.getInventory().setItem(4, customsword);
		
		ItemStack customwool = new ItemStack(Material.WOOL, 8, (byte)14);
		player.getInventory().setHelmet(customwool);
		
		player.updateInventory();
		
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(it == null) return;
		
		if(it.getType() == Material.DIAMOND_HOE && action == Action.RIGHT_CLICK_AIR) {
			player.sendMessage("Vous avez fait un click");
		}
		
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cépée test")) {
			
			Inventory inv = Bukkit.createInventory(null, 27, "§8Menu");
			
			ItemStack apple = new ItemStack(Material.APPLE, 1);
			ItemMeta appleM = apple.getItemMeta();
			appleM.setDisplayName("§cGamemode 1");
			apple.setItemMeta(appleM);
			
			ItemStack anvilTnt = new ItemStack(Material.ANVIL, 1);
			ItemMeta anvilM = anvilTnt.getItemMeta();
			anvilM.setDisplayName("§cGive de TNT");
			anvilTnt.setItemMeta(anvilM);
			
			inv.setItem(22, anvilTnt);
			inv.setItem(13, apple);			
			
			player.openInventory(inv);
			
		}
	}
}
