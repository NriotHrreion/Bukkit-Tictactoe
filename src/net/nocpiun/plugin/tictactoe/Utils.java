package net.nocpiun.plugin.tictactoe;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.Material;
import org.bukkit.enchantments.*;

public class Utils {
	public String ColorParse(String content) {
		return content.replace('&', '§');
	}
	
	public Player getPlayer(CommandSender sender) {
		return sender.getServer().getPlayer(sender.getName());
	}
	
	public Inventory InventorySetItem(Inventory inv, String displayName, List<String> lores, boolean enchant, boolean unbreakable, Material itemName, int index) {
		ItemStack item = new ItemStack(itemName, 1);
		ItemMeta itemMeta = item.getItemMeta();
		
		itemMeta.setDisplayName(ColorParse("&r"+ displayName));
		itemMeta.setLore(lores);
		itemMeta.setUnbreakable(unbreakable);
		if(enchant) {
			itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, false);
		}
		
		item.setItemMeta(itemMeta);
		inv.setItem(index, item);
		
		return inv;
	}
	
	public void InventoryAddItem(Inventory inv, Piece piece, int index) {
		if(piece == Piece.BLACK) {
			InventorySetItem(inv, "X", null, false, false, Material.BLACK_WOOL, index);
		} else if(piece == Piece.WHITE) {
			InventorySetItem(inv, "O", null, false, false, Material.WHITE_WOOL, index);
		}
	}
	
	public boolean isWin(int map[], Inventory inv) {
		int winner = 3;
		
		List<String> lore = new ArrayList<>();
		lore.add("重新打开窗口以重新开始游戏");
		
		if((map[1 - 1] == map[2 - 1]) && (map[1 - 1] == map[3 - 1]) && map[1 - 1] != 0) {
			winner = map[1 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(1));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(2));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(3));
		} else if((map[4 - 1] == map[5 - 1]) && (map[4 - 1] == map[6 - 1]) && map[4 - 1] != 0) {
			winner = map[4 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(4));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(5));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(6));
		} else if((map[7 - 1] == map[8 - 1]) && (map[7 - 1] == map[9 - 1]) && map[7 - 1] != 0) {
			winner = map[7 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(7));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(8));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(9));
		} else if((map[1 - 1] == map[4 - 1]) && (map[1 - 1] == map[7 - 1]) && map[1 - 1] != 0) {
			winner = map[1 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(1));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(4));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(7));
		} else if((map[2 - 1] == map[5 - 1]) && (map[2 - 1] == map[8 - 1]) && map[2 - 1] != 0) {
			winner = map[2 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(2));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(5));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(8));
		} else if((map[3 - 1] == map[6 - 1]) && (map[3 - 1] == map[9 - 1]) && map[3 - 1] != 0) {
			winner = map[3 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(3));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(6));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(9));
		} else if((map[1 - 1] == map[5 - 1]) && (map[1 - 1] == map[9 - 1]) && map[1 - 1] != 0) {
			winner = map[1 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(1));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(5));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(9));
		} else if((map[3 - 1] == map[5 - 1]) && (map[3 - 1] == map[7 - 1]) && map[3 - 1] != 0) {
			winner = map[3 - 1];
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(3));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(5));
			InventorySetItem(inv, "&r&l胜利", lore, true, true, IdToMaterial(winner), NumberToSlot(7));
		}
		
		if(winner == 1 || winner == 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public Material IdToMaterial(int id) {
		Material result = null;
		
		switch(id) {
		case 1:
			result = Material.BLACK_WOOL;
			break;
		case 2:
			result = Material.WHITE_WOOL;
			break;
		}
		
		return result;
	}
	
	public int SlotToNumber(int slot) {
		int result = 0;
		
		switch(slot) {
		case 3:
			result = 1;
			break;
		case 4:
			result = 2;
			break;
		case 5:
			result = 3;
			break;
		case 12:
			result = 4;
			break;
		case 13:
			result = 5;
			break;
		case 14:
			result = 6;
			break;
		case 21:
			result = 7;
			break;
		case 22:
			result = 8;
			break;
		case 23:
			result = 9;
			break;
		}
		
		return result;
	}
	
	public int NumberToSlot(int number) {
		int result = 0;
		
		switch(number) {
		case 1:
			result = 3;
			break;
		case 2:
			result = 4;
			break;
		case 3:
			result = 5;
			break;
		case 4:
			result = 12;
			break;
		case 5:
			result = 13;
			break;
		case 6:
			result = 14;
			break;
		case 7:
			result = 21;
			break;
		case 8:
			result = 22;
			break;
		case 9:
			result = 23;
			break;
		}
		
		return result;
	}
}
