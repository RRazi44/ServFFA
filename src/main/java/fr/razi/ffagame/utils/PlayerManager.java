package fr.razi.ffagame.utils;

import fr.razi.ffagame.FFAGame;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class PlayerManager {

    public static void sendToLobby(Player player){
        resetPlayerState(player);
        player.getInventory().setItem(4, ItemManager.getLobbyAxe());
        player.teleport(WorldManager.locLobby);
        player.updateInventory();
        for(PotionEffect potionEffect : player.getActivePotionEffects()){
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public static void sendToFFA(Player player) {
        resetPlayerState(player);
        stuffToFFARush(player);
        player.teleport(FFAGame.spawnManager.getRandomSpawn());
    }

    private static void resetPlayerState(Player player) {
        clearInventory(player);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setHealthScale(20);
        player.setHealth(20);
        player.setFireTicks(0);
        player.setFallDistance(0f);
        player.setExp(0);
        player.setLevel(0);
        player.setGameMode(GameMode.SURVIVAL);
        player.setFlying(false);
        player.setAllowFlight(false);
    }

    public static void stuffToFFARush(Player player){

        clearInventory(player);
        player.getInventory().setArmorContents(ItemManager.getStainedLeatherArmor());

        ItemStack sandstone = new ItemStack(Material.SANDSTONE);
        sandstone.setAmount(64);

        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        gapple.setAmount(64);

        ItemStack tnt = new ItemStack(Material.TNT);
        tnt.setAmount(16);


        for(int i = 4; i<8; i++) player.getInventory().setItem(i, sandstone);
        player.getInventory().setItem(0, ItemManager.setUnbreakable(new ItemStack(Material.STONE_SWORD)));
        player.getInventory().setItem(1, tnt);
        player.getInventory().setItem(2, ItemManager.setUnbreakable(new ItemStack(Material.FLINT_AND_STEEL)));
        player.getInventory().setItem(3, ItemManager.setUnbreakable(new ItemStack(Material.DIAMOND_PICKAXE)));
        player.getInventory().setItem(8, gapple);
        player.updateInventory();

    }



    public static void clearInventory(Player player){
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
    }


}
