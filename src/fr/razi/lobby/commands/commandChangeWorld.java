package fr.razi.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.razi.lobby.Main;

public class commandChangeWorld implements CommandExecutor {

	private Main main;
	
	public commandChangeWorld(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("ffarush")) {
					player.sendMessage(player.getLocation().getWorld() + "");
					player.teleport(main.getLobby());
					
					player.sendMessage(player.getLocation().getWorld() + "");
				} 
				else if(args[0].equalsIgnoreCase("lobby")){
					player.sendMessage(player.getLocation().getWorld() + "");
					player.teleport(main.getFFARUSH());
					player.sendMessage(player.getLocation().getWorld() + "");
				}
				else {
					
					player.sendMessage("§cLa commande est /changeworld <World>");
					player.sendMessage("§cPour voir la liste des mondes disponible, voici la commande: /changeworld list");
					
				}
				
			} else {
				
				player.sendMessage("§cLa commande est /changeworld <World>");
				player.sendMessage("§cPour voir la liste des mondes disponible, voici la commande: /changeworld list");
				
			}
			
		}
		
		return false;
	}

}
