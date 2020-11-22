package net.nocpiun.plugin.tictactoe;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;

public class Game implements Listener {
	private Utils util = new Utils();
	
	private static final Material black = Material.BLACK_WOOL;
	private static final Material white = Material.WHITE_WOOL;
	private static final Material blank = Material.GRAY_STAINED_GLASS_PANE;
	
	// 0 = Black, 1 = White
	private int turn = 0;
	private int map[] = new int[9];
	private boolean isFinish = false;
	
	private Inventory gameWindow = null;
	
	public void initGame() {
		//
	}
	
	public void launch(CommandSender sender) {
		Player player = util.getPlayer(sender);
		
		map = new int[9];
		turn = 0;
		isFinish = false;
		
		initGameWindow();
		player.openInventory(gameWindow);
	}
	
	private void gameFinish() {
		map = new int[9];
	}
	
	private void initGameWindow() {
		Inventory window = Bukkit.createInventory(null, 27, "TicTacToe 小游戏");
		
		// Close Button & About Card
		List<String> aboutInfo = new ArrayList<>();
		aboutInfo.add("");
		aboutInfo.add("TicTacToe By NriotHrreion");
		aboutInfo.add("感谢游玩!");
		List<String> closeInfo = new ArrayList<>();
		closeInfo.add("");
		closeInfo.add("点击以关闭窗口");
		util.InventorySetItem(window, "&b关于", aboutInfo, false, false, Material.YELLOW_STAINED_GLASS_PANE, 9);
		util.InventorySetItem(window, "&7&l关闭窗口", closeInfo, false, false, Material.RED_STAINED_GLASS_PANE, 18);
		
		// Frame
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 2);
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 11);
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 20);
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 6);
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 15);
		util.InventorySetItem(window, "", null, false, false, Material.WHITE_STAINED_GLASS_PANE, 24);
		
		// Grids
		List<String> lores = new ArrayList<>();
		lores.add("点击以放置棋子");
		
		util.InventorySetItem(window, "空格", lores, false, true, blank, 3);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 4);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 5);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 12);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 13);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 14);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 21);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 22);
		util.InventorySetItem(window, "空格", lores, false, true, blank, 23);
		
		gameWindow = window;
	}
	
	private void blackClick(int slot) {
		int id = util.SlotToNumber(slot);
		map[id - 1] = 1;
		util.InventoryAddItem(gameWindow, Piece.BLACK, slot);
		
		isFinish = util.isWin(map, gameWindow);
		System.out.println(isFinish);
		if(isFinish) {
			gameFinish();
		}
	}
	
	private void whiteClick(int slot) {
		int id = util.SlotToNumber(slot);
		map[id - 1] = 2;
		util.InventoryAddItem(gameWindow, Piece.WHITE, slot);
		
		isFinish = util.isWin(map, gameWindow);
		System.out.println(isFinish);
		if(isFinish) {
			gameFinish();
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(event.getInventory() == gameWindow) {
			event.setCancelled(true);
			
			Material item = event.getCurrentItem().getType();
			if(item == blank && isFinish == false) {
				switch(turn) {
				case 0:
					turn = 1;
					blackClick(event.getSlot());
					break;
				case 1:
					turn = 0;
					whiteClick(event.getSlot());
					break;
				}
			} else if(item == Material.RED_STAINED_GLASS_PANE) {
				event.getWhoClicked().closeInventory();
			}
		}
	}
}
