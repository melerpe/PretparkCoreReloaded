/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 HorizonCraft
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

package nl.HorizonCraft.PretparkCore.Enums;

/**
 * This class has been created on 09/18/2015 at 3:53 PM by Cooltimmetje.
 */
public enum AchievementsEnum {

    FIRST_TIME_JOIN(0, "To the Horizon and Beyond!", "Join de server voor de eerste keer!", 50, 1),
    KOALA_SLAP(1, "I don't like koala's!", "Launch xBrandy!", 10, 1),
    CREEPER_SLAP(2, "It exploded into diamonds!", "Launch klapklap980!", 10, 1),
    MAZE_COMPLETE_1(3, "Into the maze!", "Haal het einde van doolhof 1!", 25, 2);

    private int id;
    private String name;
    private String description;
    private int coinReward;
    private int keyReward;

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getCoinReward(){
        return coinReward;
    }

    public int getKeyReward(){
        return keyReward;
    }

    AchievementsEnum(int i, String s, String s1, int i1, int i2) {
        this.id = i;
        this.name = s;
        this.description = s1;
        this.coinReward = i1;
        this.keyReward = i2;
    }
}