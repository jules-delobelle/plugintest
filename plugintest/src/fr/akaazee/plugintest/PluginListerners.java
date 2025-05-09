package fr.akaazee.plugintest;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PluginListerners implements Listener {
	
	private Main main;
	
	public PluginListerners(Main main) {
		this.main = main;
	}
	
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
		
		//put sword in the inventory
		player.getInventory().setItem(4, customsword);
		
		ItemStack customwool = new ItemStack(Material.WOOL, 8, (byte)14);
		player.getInventory().setHelmet(customwool);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner("luck");
		meta.setDisplayName("§eLucky Block");
		skull.setItemMeta(meta);
		
		player.getInventory().addItem(skull);
		
		
		player.updateInventory();
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		Block block = event.getBlock();
		BlockState bs = block.getState();
		
		if(bs instanceof Skull) {
			Skull skull = (Skull) bs;
			if(skull.getOwner().equalsIgnoreCase("Luck")) {
				event.setCancelled(true);
				block.setType(Material.AIR);
				
				Random random = new Random();
				
				switch(random.nextInt(4)) {
					
					case 0:
						player.sendMessage("ratio");
						break;
						
					case 1:
						block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, 3));
						break;
						
					case 2:
						block.getWorld().spawnEntity(block.getLocation(), EntityType.CREEPER);
						break;
						
					default:
						block.getWorld().createExplosion(block.getLocation(), 40);
						break;
				
				}
				
			}
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(event.getClickedBlock() != null && action == Action.RIGHT_CLICK_BLOCK) {
			BlockState bs = event.getClickedBlock().getState();
			if(bs instanceof Sign) {
				
				Sign sign = (Sign) bs;
				
				//clear
				if(sign.getLine(0).equalsIgnoreCase("[Clear]") && sign.getLine(1).equalsIgnoreCase("all")) {
					player.getInventory().clear();
					player.sendMessage("Vous inventaire a été clear");
				}
				
				//teleport Bungee
				if(sign.getLine(0).equalsIgnoreCase("[Teleport]")) {
					
					if(sign.getLine(2) != null) {
						
						World targetWorld = Bukkit.getWorld(sign.getLine(2));
						
						if(targetWorld != null) {
							player.teleport(targetWorld.getSpawnLocation());
							player.sendMessage("Vous avez été téléporté vers " + targetWorld.getName());
						}else{
							WorldCreator creator = new WorldCreator(sign.getLine(2));
							targetWorld = creator.createWorld();
							player.teleport(targetWorld.getSpawnLocation());
	                        player.sendMessage("Un nouveau monde a été créé. Vous avez été téléporté vers " + targetWorld.getName());
						}
					}
					
				}
			}
		}
		
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























