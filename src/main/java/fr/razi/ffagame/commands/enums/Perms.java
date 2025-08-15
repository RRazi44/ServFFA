package fr.razi.ffagame.commands.enums;

public enum Perms {
    COMMAND_LOBBY("ffagame.command.lobby"),
    COMMAND_WORLD("ffagame.command.world");

    private final String node;

    Perms(String node){
        this.node = node;
    }

    public boolean has(org.bukkit.command.CommandSender sender){
        return sender.hasPermission(node);
    }

}
