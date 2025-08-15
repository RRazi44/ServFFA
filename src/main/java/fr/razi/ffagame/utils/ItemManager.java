package fr.razi.ffagame.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.List;

public class ItemManager {

    private static ItemStack lobbyAxe;
    public static final String DISPLAY_NAME = "§6✦ §lHache du Guerrier Éternel §6✦";
    public static final List<String> LORE = Arrays.asList(
        "§5☠ §oForgée dans les flammes du Néant,",
        "§5§oelle ne reconnaît qu'un seul maître.",
        "",
        "§d► §l§oClique droit pour entrer dans l'Arène ! ",
        "",
        "§5Les légendes murmurent qu'elle",
        "§5ne faiblit jamais."
    );

    public void loadItems(){
        lobbyAxe = createAxe();
    }

    public static ItemStack getLobbyAxe(){
        return lobbyAxe.clone();
    }

    public static boolean isLobbyAxe(ItemStack itemStack){
        return itemStack != null && lobbyAxe != null && itemStack.isSimilar(lobbyAxe);
    }

    public static ItemStack createItem(Material material, List<String> lore, String name) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);


        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createAxe(){
        return createItem(Material.DIAMOND_AXE, LORE, DISPLAY_NAME);
    }

    public static ItemStack[] getStainedLeatherArmor() {
        return getStainedLeatherArmor(Color.RED);
    }

    public static ItemStack[] getStainedLeatherArmor(Color color){
        ItemStack[] armor = new ItemStack[4];
        armor[0] = colorItemStack(setUnbreakable(new ItemStack(Material.LEATHER_BOOTS)), color);
        armor[1] = colorItemStack(setUnbreakable(new ItemStack(Material.LEATHER_LEGGINGS)), color);
        armor[2] = colorItemStack(setUnbreakable(new ItemStack(Material.LEATHER_CHESTPLATE)), color);
        armor[3] = colorItemStack(setUnbreakable(new ItemStack(Material.LEATHER_HELMET)), color);
        return armor;
    }

    public static ItemStack colorItemStack(ItemStack itemStack, Color color){

        ItemMeta meta = itemStack.getItemMeta();
        if(!(meta instanceof LeatherArmorMeta)) return itemStack;
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);

        LeatherArmorMeta armorMeta = (LeatherArmorMeta) meta;
        armorMeta.setColor(color);
        itemStack.setItemMeta(armorMeta);

        return itemStack;
    }

    public static ItemStack setUnbreakable(ItemStack item){
        ItemMeta itm = item.getItemMeta();
        itm.spigot().setUnbreakable(true);
        item.setItemMeta(itm);
        return item;
    }

}
