package net.nocpiun.plugin.tictactoe;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

public class TicTacToeMain extends JavaPlugin implements CommandExecutor {
	private Game game = new Game();
	
	@Override
	public void onEnable() {
		this.getLogger().info("TicTacToe initing...");
		
		Bukkit.getPluginCommand("tictactoe").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(game, this);
		
		game.initGame();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		game.launch(sender);
		
		return true;
	}
}
