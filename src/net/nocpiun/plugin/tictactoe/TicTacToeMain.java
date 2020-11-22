package net.nocpiun.plugin.tictactoe;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

public class TicTacToeMain extends JavaPlugin implements CommandExecutor {
	private Game game = new Game();
	private MultiGame multiGame = new MultiGame();
	private Utils util = new Utils();
	
	@Override
	public void onEnable() {
		this.getLogger().info("TicTacToe initing...");
		
		Bukkit.getPluginCommand("tictactoe").setExecutor(this);
		Bukkit.getPluginCommand("multitictactoe").setExecutor(new MultiCommand());
		Bukkit.getPluginManager().registerEvents(game, this);
		Bukkit.getPluginManager().registerEvents(multiGame, this);
		
		game.initGame();
		multiGame.initGame();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		game.launch(sender);
		
		return true;
	}
	
	private class MultiCommand implements CommandExecutor {
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
			multiGame.launch(sender, args[0]);
			
			return true;
		}
	}
}
