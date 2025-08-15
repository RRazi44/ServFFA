package fr.razi.ffagame.commands;

import fr.razi.ffagame.FFAGame;
import fr.razi.ffagame.commands.enums.Perms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandLobby implements CommandExecutor {

	private final FFAGame plugin;

	public commandLobby(FFAGame plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(FFAGame.PREFIX + ChatColor.RED + "This command can only be used in-game.");
			return true;
		}

		Player player = (Player) sender;

		if(!Perms.COMMAND_LOBBY.has(player)){
			player.sendMessage(FFAGame.PREFIX + ChatColor.RED + "You don't have permission to use this command.");
			return true;
		}

		if(args.length > 0){
			player.sendMessage(FFAGame.PREFIX + ChatColor.YELLOW + "Usage: /" + label);
			return true;
		}

		plugin.getPlayerManager().sendToLobby(player, plugin);
		player.sendMessage(FFAGame.PREFIX + ChatColor.GREEN + "Teleported to the lobby.");

		return true;
	}
}
