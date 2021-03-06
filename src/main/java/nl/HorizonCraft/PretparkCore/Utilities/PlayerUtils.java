/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See the COPYRIGHT.txt for in the root for more information.
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
 */

package nl.HorizonCraft.PretparkCore.Utilities;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Gadgets.GadgetsEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Ranks.RanksEnum;
import nl.HorizonCraft.PretparkCore.Bundles.Wardrobe.PiecesEnum;
import nl.HorizonCraft.PretparkCore.Enums.StatTypes;
import nl.HorizonCraft.PretparkCore.Enums.Stats;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class has been created on 09/9/11/2015/2015 at 9:11 PM by Cooltimmetje.
 */
public class PlayerUtils {


    public static CorePlayer getProfile(Player p){
        return Variables.profile.get(p.getName());
    }

    public static void createProfile(Player p){
        Variables.profile.put(p.getName(), new CorePlayer(p));
    }

    public static void configPlayer(Player p, boolean forceInv) {
        CorePlayer cp = getProfile(p);
        final CorePlayer cpFinal = cp;
        final Player pFinal = p;

        if(RanksEnum.hasPermission(p,RanksEnum.BOUWER)){
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.setGameMode(GameMode.SURVIVAL);
        }

        if(!RanksEnum.hasPermission(p,RanksEnum.BOUWER) || forceInv){
            p.getInventory().clear();

            ItemStack is = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal(), "&e&lMy&3&lHorizon " + Variables.RIGHT_CLICK, "&7Open je profiel hier, hier kun ",
                    "&7je alles vinden over jouw account.");
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            sm.setOwner(p.getName());
            is.setItemMeta(sm);
            ItemUtils.createDisplay(p, is, 1);

            ItemUtils.createDisplay(p, 2, Material.MINECART, 1, 0, "&aWarps " + Variables.RIGHT_CLICK, "&7Bekijk alle warps en hun status.");
            ItemUtils.createDisplay(p, 9, Material.CHEST, 1, 0, "&aSwag Menu " + Variables.RIGHT_CLICK, "&7Wil je wat swag? Kijk hier!");

            if(RanksEnum.hasPermission(p,RanksEnum.MANAGER)) {
                ItemUtils.createDisplay(p, 7, Material.FLINT, 1, 0, "&aAdmin Menu " + Variables.RIGHT_CLICK, "&7Beheer de server, aleen voor OP's!");
            }
            setGadget(cp.getGadget(),p);
        }

        setPiece(cp.getHead(),p);
        setPiece(cp.getLegs(),p);
        setPiece(cp.getBoots(),p);
        setPiece(cp.getChest(),p);

//        if (cp.hasSpeed()) {
//            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0, false, false));
//        } else {
//            if(p.hasPotionEffect(PotionEffectType.SPEED)){
//                p.removePotionEffect(PotionEffectType.SPEED);
//            }
//        }

        cp.calculateExp(p, true);

        switch (p.getName()) {
            case "xBrandy":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.KOALA_SLAP);
                break;
            case "78wesley":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.PEDOBEAR_SLAP);
                break;
            case "BekertjeZuivel":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.MELK_SLAP);
                break;
            case "Cooltimmetje":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.COOL_SLAP);
                break;
            case "SvenTijger":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.SVEN_SLAP);
                break;
            case "Destiny_VG":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.DESTINY_SLAP);
                break;
            case "Jordy010NL":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.JORDY_SLAP);
                break;
            case "jordyvz01":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.JORDY2_SLAP);
                break;
            case "MAETJE":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.MAE_SLAP);
                break;
            case "Toptim24":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.TIM_SLAP);
                break;
            case "nickjedl":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.NICK_SLAP);
                break;
            case "RickBult":
                PlayerUtils.getProfile(p).awardAchievement(p, AchievementsEnum.RICK_SLAP);
                break;
            default:
                break;
        }

        ScheduleUtils.scheduleTask(30, new Runnable() {
            @Override
            public void run() {
                cpFinal.updateRank(pFinal);
            }
        });
    }

    public static void setPiece(PiecesEnum piece, Player p){
        if(piece != null){
            CorePlayer cp = PlayerUtils.getProfile(p);
            String name = MiscUtils.color("&" + piece.getWeight().getColor() + piece.getSuit().getName() + " " + piece.getSuitType().getName());
            ItemStack is = new ItemStack(piece.getMaterial(), 1, (byte)0);


            if(piece.getMaterial() == Material.SKULL_ITEM){
                SkullMeta sm = (SkullMeta) is.getItemMeta();
                sm.setOwner(piece.getSkullUUID());
                String[] loreA = getLore(piece);
                ArrayList<String> lore = new ArrayList<>();
                for(String loreS : loreA){
                    lore.add(MiscUtils.color(loreS));
                }
                sm.setLore(lore);
                sm.setDisplayName(name);
                is.setItemMeta(sm);
                is.setDurability((short) SkullType.PLAYER.ordinal());
            } else {
                LeatherArmorMeta lam = (LeatherArmorMeta) is.getItemMeta();
                lam.setColor(piece.getColor());
                String[] loreA = getLore(piece);
                ArrayList<String> lore = new ArrayList<>();
                for(String loreS : loreA){
                    lore.add(MiscUtils.color(loreS));
                }
                lam.setLore(lore);
                lam.setDisplayName(name);
                is.setItemMeta(lam);
            }

            switch (piece.getSuitType()){
                case HELMET:
                    cp.setHead(piece);
                    p.getInventory().setHelmet(is);
                    break;
                case CHESTPLATE:
                    cp.setChest(piece);
                    p.getInventory().setChestplate(is);
                    break;
                case LEGGINGS:
                    cp.setLegs(piece);
                    p.getInventory().setLeggings(is);
                    break;
                case BOOTS:
                    cp.setBoots(piece);
                    p.getInventory().setBoots(is);
                    break;
            }
        }
    }

    private static String[] getLore(PiecesEnum piece){
        StringBuilder sb = new StringBuilder();

        sb.append("&3").append(piece.getSuit().getLore()).append("\n \n");
        sb.append("&bFull Suit Ability:\n&3").append(piece.getSuit().getAbility());

        return sb.toString().trim().split("\n");
    }

    private static void setGadget(GadgetsEnum gadget, Player p){
        if(gadget != null){
            int id = gadget.getId();
            int cooldown = gadget.getCooldown();
            String name = "&" + gadget.getWeight().getColor() + gadget.getName();
            String[] lore = constuctLore(gadget.getLore(), true, cooldown);
            Material m = gadget.getMaterial();
            int data = gadget.getDmg();

            ItemUtils.createDisplay(p, 8, m, 1, data, name, lore);
        }
    }

    private static String[] constuctLore(String lore, boolean unlocked, int cooldown) {
        StringBuilder sb = new StringBuilder();

        sb.append("&3COOLDOWN: &b").append(MiscUtils.formatTime(cooldown));
        sb.append("\n \n");
        String[] loreArray = lore.split("\n");
        for(String loreS : loreArray) {
            sb.append("&3").append(loreS).append("\n");
        }
        if(!unlocked){
            sb.append(" \n");
            sb.append("&cLOCKED").append("\n");
            sb.append("&aKoop dit item in de shop!");
        }

        return sb.toString().split("\n");
    }

    public static void showStat(Player p){
        Stats stat = Stats.random();
        if(stat.getStatType() == StatTypes.DISTANCE) {
            int distance = 0;
            for (Stats stats : Stats.values()) {
                if (stats.getStatType() == StatTypes.DISTANCE) {
                    distance = distance + p.getStatistic(stats.getStatistic());
                }
            }
            double distanceKm = (double) distance / (double) 100000;
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.HALF_UP);
            double distanceStat = (double) p.getStatistic(stat.getStatistic()) / (double) 100000;

            String title = MiscUtils.color("&e&lJe hebt op deze server &c&l" + df.format(distanceKm) + "km &e&lafgelegd, waarvan " + stat.getLine().replace("%n", "&c&l" + df.format(distanceStat) + "km&e&l"));

            TitleUtils.sendAction(p, MiscUtils.color(title), 5);
        } else if(stat.getStatType() == StatTypes.TIME) {
            int time = p.getStatistic(stat.getStatistic()) / 20;
            TitleUtils.sendAction(p, MiscUtils.color("&e&l" + stat.getLine().replace("%n", "&c&l" + MiscUtils.formatTime(time) + "&e&l")), 5);
        } else {
            TitleUtils.sendAction(p, MiscUtils.color("&e&l" + stat.getLine().replace("%n", "&c&l" + p.getStatistic(stat.getStatistic()) + "&e&l")), 5);
        }
    }

    public static Player getPlayerByCode(String code){
        for(String s : Variables.profile.keySet()){
            CorePlayer cp = getProfile(Bukkit.getPlayer(s));
            if(cp.getDiscordVerifyCode().equalsIgnoreCase(code.toUpperCase())){
                return Bukkit.getPlayer(s);
            }
        }
        return null;
    }

    public static CorePlayer getByDiscordID(String discordID){
        for(String s : Variables.profile.keySet()){
            CorePlayer cp = Variables.profile.get(s);
            if(cp.getDiscordID().equals(discordID)){
                return cp;
            }
        }
        return null;
    }


}
