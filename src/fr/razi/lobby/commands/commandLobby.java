package fr.razi.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.razi.lobby.Main;

public class commandLobby implements CommandExecutor {

	private Main main;
	public commandLobby(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
			if(sender instanceof Player) {	
				
				
				Player player = (Player) sender;
				
				
				player.sendMessage("§aVous venez d'etre téléporté au lobby.");
			
				player.teleport(main.getFFARUSH());
				
				
			}
			
			return false;
	}
}
