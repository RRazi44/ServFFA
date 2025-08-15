package fr.razi.ffagame.commands.tabs;

import fr.razi.ffagame.commands.enums.Perms;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WorldTabCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if (!Perms.COMMAND_WORLD.has(player)) return Collections.emptyList();

            if (args.length == 1) {
                final String prefix = args[0].toLowerCase(Locale.ROOT);
                return Bukkit.getWorlds().stream()
                        .map(World::getName)
                        .filter(name -> name.toLowerCase(Locale.ROOT).startsWith(prefix))
                        .sorted()
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();
        }

        return Collections.emptyList();
    }
}
