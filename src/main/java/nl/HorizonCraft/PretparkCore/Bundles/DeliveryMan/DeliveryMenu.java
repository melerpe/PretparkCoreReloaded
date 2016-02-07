/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2016 HorizonCraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * In addition to the above:
 * All content in the repo/plugin is created by and owned by HorizonCraft, unless
 * stated otherwise. All content that is not created by us will be placed in their
 * original package, where they were found or that was set by the owner by default.
 *
 * You are free to use the code anywhere you like, but we will not provide ANY support
 * unless you are on our server using this plugin.
 */

package nl.HorizonCraft.PretparkCore.Bundles.DeliveryMan;

import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementType;
import nl.HorizonCraft.PretparkCore.Bundles.Achievements.AchievementTypes;
import nl.HorizonCraft.PretparkCore.Menus.MyHorizon.MyHorizonMenu;
import nl.HorizonCraft.PretparkCore.Profiles.CorePlayer;
import nl.HorizonCraft.PretparkCore.Utilities.ItemUtils;
import nl.HorizonCraft.PretparkCore.Utilities.PlayerUtils;
import nl.HorizonCraft.PretparkCore.Utilities.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SimpleAttachableMaterialData;

import javax.lang.model.element.VariableElement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cooltimmetje on 2/6/2016 at 8:08 PM.
 */
public class DeliveryMenu implements Listener {

    public static void open(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Pieter Post");
        CorePlayer cp = PlayerUtils.getProfile(p);



//        ItemUtils.createDisplay(inv, 13, Material.MINECART, 1, 0, "&cDagelijkse login bonus.", "Kom elke dag hier terug voor een nieuwe bonus,", "deze bonus word elke dag groter!", " ", "&7Binnenkort");

        ItemUtils.createDisplay(constructDaily(cp), inv, 13);
        ItemUtils.createDisplay(inv, 15, Material.MINECART, 1, 0, "&cVote reward.", "Vote elke dag voor onze server", "en krijg een vote token.", " ", "&7Binnenkort");

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).contains("Pieter Post")){
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            Material m = event.getCurrentItem().getType();
            switch (m){
                case STORAGE_MINECART:
                    claimDaily(p);
                    break;
                default:
                    break;
            }
        }
    }

    private void claimDaily(Player p) {
        CorePlayer cp = PlayerUtils.getProfile(p);

        int currentStreak = cp.getCurrent_daily_streak();
        long lastDailyClaim = cp.getLast_daily_claim();
        long curTime = System.currentTimeMillis();
        long claimBefore = lastDailyClaim + (86400000*2);
        int lostStreak = 0;

        boolean streakLost = claimBefore < curTime && (lastDailyClaim != 0);

        if (streakLost) {
            currentStreak = 0;
        }

        int modifier;
        if(currentStreak > Variables.DAILY_MODIFIER_CAP){
            modifier = Variables.DAILY_MODIFIER_CAP;
        } else {
            modifier = currentStreak;
        }

        if(Variables.DAILY_COINS != 0){
            cp.addCoins(p, (int)(Variables.DAILY_COINS * Math.pow(Variables.DAILY_MODIFIER,modifier)), "Dagelijkse Login Bonus", false, true);
        }
        if(Variables.DAILY_EXP != 0){
            cp.addExp(p, (int)(Variables.DAILY_EXP * Math.pow(Variables.DAILY_MODIFIER,modifier)), "Dagelijkse Login Bonus", false, true);
        }
        if(Variables.DAILY_BOXES != 0){
            cp.addBoxes(p, (int)(Variables.DAILY_BOXES * Math.pow(Variables.DAILY_MODIFIER,modifier)), "Dagelijkse Login Bonus", false, true);
        }
        if(Variables.DAILY_KEYS != 0){
            cp.addKeys(p, (int)(Variables.DAILY_KEYS * Math.pow(Variables.DAILY_MODIFIER,modifier)), "Dagelijkse Login Bonus", false, true);
        }

        cp.setCurrent_daily_streak(currentStreak + 1);
        currentStreak = currentStreak + 1;
        cp.setLast_daily_claim(System.currentTimeMillis());
        if(cp.getDaily_streak_record() < currentStreak){
            cp.setDaily_streak_record(currentStreak);
        }

        open(p);
    }

    private static ItemStack constructDaily(CorePlayer cp){
        Material m;
        String nameNoColor = "Dagelijkse login bonus";
        String name;
        StringBuilder lore = new StringBuilder();

        int currentStreak = cp.getCurrent_daily_streak();
        int longestStreak = cp.getDaily_streak_record();
        long lastDailyClaim = cp.getLast_daily_claim();
        long curTime = System.currentTimeMillis();
        long nextClaim = lastDailyClaim + 86400000;
        long claimBefore = lastDailyClaim + (86400000*2);
        int lostStreak = 0;

        boolean claimable = (nextClaim < curTime) || (lastDailyClaim == 0);
        boolean streakLost = claimBefore < curTime && (lastDailyClaim != 0);

        if(claimable){
            m = Material.STORAGE_MINECART;
            name = "&a" + nameNoColor;
            if (streakLost) {
                lostStreak = currentStreak;
                currentStreak = 0;
            }
        } else {
            m = Material.MINECART;
            name = "&c" + nameNoColor;
        }

        lore.append("Kom elke dag hier terug voor een nieuwe bonus,\ndeze bonus wordt elke dag groter!\n \n");

        if(claimable){
            int modifier;
            if(currentStreak > Variables.DAILY_MODIFIER_CAP){
                modifier = Variables.DAILY_MODIFIER_CAP;
            } else {
                modifier = currentStreak;
            }
            lore.append("Vandaag krijg je:\n");
            if(Variables.DAILY_COINS != 0){
                lore.append("&6").append((int)(Variables.DAILY_COINS * Math.pow(Variables.DAILY_MODIFIER,modifier))).append(" coins").append("\n");
            }
            if(Variables.DAILY_EXP != 0){
                lore.append("&9").append((int)(Variables.DAILY_EXP * Math.pow(Variables.DAILY_MODIFIER,modifier))).append(" EXP").append("\n");
            }
            if(Variables.DAILY_BOXES != 0){
                lore.append("&3").append((int)(Variables.DAILY_BOXES * Math.pow(Variables.DAILY_MODIFIER,modifier))).append(" Mystery Boxes").append("\n");
            }
            if(Variables.DAILY_KEYS != 0){
                lore.append("&d").append((int)(Variables.DAILY_KEYS * Math.pow(Variables.DAILY_MODIFIER,modifier))).append(" Mystery Keys").append("\n");
            }
            lore.append(" \n");
            if(!streakLost) {
                lore.append("&bMomentele claim streak: &a").append(currentStreak).append("\n");
                lore.append("&bLangste claim streak: &a").append(longestStreak).append("\n");
            } else {
                lore.append("&c&lCLAIM STREAK VERLOREN\n");
                lore.append("&bOude streak: &a").append(lostStreak).append("\n");
                lore.append("&bLangste claim streak: &a").append(currentStreak).append("\n");
            }
            lore.append("\n&c> &aKlik om te claimen.");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date(nextClaim);
            String friendlyDate = sdf.format(date);

            lore.append("&bMomentele claim streak: &a").append(currentStreak).append("\n");
            lore.append("&bLangste claim streak: &a").append(longestStreak).append("\n \n");

            lore.append("&cJe kunt dit nog niet claimen!\nVolgende claim:\n&b").append(friendlyDate);
        }
        return ItemUtils.createItemstack(m, 1, 0, name, lore.toString().split("\n"));
    }

}