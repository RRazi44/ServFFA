package fr.razi.ffagame.commands;

import fr.razi.ffagame.FFAGame;
import fr.razi.ffagame.commands.enums.Perms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class commandWorld implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(FFAGame.PREFIX + ChatColor.RED + "This command can only be used in-game.");
            return true;
        }

        Player player = (Player) sender;

        if(Perms.COMMAND_WORLD.has(player)){
            player.sendMessage(FFAGame.PREFIX + ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        switch(args.length){
            case 0:
                player.sendMessage("World : " + player.getLocation().getWorld().toString());
                break;
            case 1:
                String targetName = args[0];
                World target = Bukkit.getWorld(targetName);

                if (target == null) {
                    String available = Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.joining(", "));
                    player.sendMessage(FFAGame.PREFIX + ChatColor.RED + "World not found: " + targetName);
                    player.sendMessage(ChatColor.GRAY + "Available: " + (available.isEmpty() ? "none" : available));
                    return true;
                }

                // TP au spawn du monde (plus safe qu'une coord fixe)
                Location dest = target.getSpawnLocation();
                player.teleport(dest);
                player.sendMessage(FFAGame.PREFIX + ChatColor.GREEN + "Teleported to world " + ChatColor.YELLOW + target.getName() +
                        ChatColor.GRAY + " (" + dest.getBlockX() + ", " + dest.getBlockY() + ", " + dest.getBlockZ() + ")");
                return true;
            default:
                player.sendMessage(FFAGame.PREFIX + ChatColor.YELLOW + "Usage: /" + label + " <world>");
                return true;
        }

        return false;
    }

}
