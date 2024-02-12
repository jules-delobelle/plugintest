package fr.akaazee.plugintest;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
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
			
			inv.setItem(22, getItem(Material.ANVIL, "§cGive de TNT", 1));
			inv.setItem(13, getItem(Material.APPLE, "§eGamemode 1", 1));			
			
			player.openInventory(inv);
			
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase("§8Menu")) {
			
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			case APPLE:
				player.closeInventory();
				player.setGameMode(GameMode.CREATIVE);
				break;
				
			case ANVIL:
				player.getInventory().addItem(new ItemStack(Material.TNT, 3));
				break;
			
			default:
				break;
			}
		}
	}
	
	public ItemStack getItem(Material material, String customName, int quantity) {
		ItemStack it = new ItemStack(material, quantity);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		it.setItemMeta(itM);
		return it;
	}
}























