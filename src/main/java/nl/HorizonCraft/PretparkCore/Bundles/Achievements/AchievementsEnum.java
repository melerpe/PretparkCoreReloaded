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

package nl.HorizonCraft.PretparkCore.Bundles.Achievements;

/**
 * This class has been created on 09/18/2015 at 3:53 PM by Cooltimmetje.
 */
public enum AchievementsEnum {

    FIRST_TIME_JOIN(0, "To the Horizon and Beyond!", "Join de server voor de eerste keer!", 50, 1, 100, AchievementType.GENERAL, false),
    KOALA_SLAP(1, "I don't like koala's!", "Launch xBrandy!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    PEDOBEAR_SLAP(2, "PEDOBEAR!", "Launch 78wesley!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    MAZE_COMPLETE_1(3, "Into The Maze!", "Haal het einde van Cooltimmetje's Maze of Evil!", 25, 2, 500, AchievementType.MAZES_PARKOUR, false),
    FE_RIDE(4, "Boer Harms op de Trekker!", "Maak een ritje op de Farm Expedition!", 25, 1, 600, AchievementType.RIDES, false),
    MELK_SLAP(5, "Maar ik lus geen melk :(", "Launch BekertjeZuivel!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    MAZE_COMPLETE_2(6, "Haunted Maze!", "Haal het einde van de Haunted Mansion!", 25, 2, 500, AchievementType.MAZES_PARKOUR, false),
    COOL_SLAP(7, "Not so cool anymore, I guess!", "Launch Cooltimmetje!", 25, 2, 500, AchievementType.STAFFPUNCH, false),
    SVEN_SLAP(8, "TIJGER!", "Launch SvenTijger!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    UNLOCK_GADGET(9, "FANCY TECHNOLOGY!", "Unlock je eerste gadget!", 25, 1, 500, AchievementType.UNLOCKABLES, false),
    UNLOCK_CLOTHING(10, "WEARING THE SWAG!", "Unlock je eerste kledingstuk!", 25, 1, 500, AchievementType.UNLOCKABLES, false),
    UNLOCK_PET(11, "EEN VRIENDJE!", "Unlock je eerste pet!", 25, 1, 500, AchievementType.UNLOCKABLES, false),
    DESTINY_SLAP(12, "ITS MY DESTINY!", "Launch Destiny_VG!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    GROT_FIND(13, "Het geheime kamertje...", "Vind het geheime kamertje in de berg.", 50, 1, 250, AchievementType.GENERAL, true),
    JORDY_SLAP(14, "Directeurtje pesten!", "Launch Jordy010NL!", 25, 2, 500, AchievementType.STAFFPUNCH, false),
    JORDY2_SLAP(15, "2 JORDY'S?!", "Launch jordyvz01!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    FIREWORK(16, "YES YES YES!", "Steek een vuurwerkje af!", 10, 0, 100, AchievementType.GENERAL, false),
    DOUBLE(17, "Doubling it up!", "Krijg 2x coins, exp of boxes!", 100, 2, 400, AchievementType.GENERAL, false),
    COMMON(18, "Saaaaaaaiii...", "Vind een COMMON item in de MysteryBox!", 25, 1, 150, AchievementType.UNLOCKABLES, false),
    RARE(19, "RARE?!", "Vind een RARE item in de MysteryBox!", 50, 2, 300, AchievementType.UNLOCKABLES, false),
    EPIC(20, "IT'S SO EPIC!", "Vind een EPIC item in de MysteryBox!", 100, 4, 600, AchievementType.UNLOCKABLES, false),
    LEGENDARY(21, "I'M THE LIVING LEGEND!", "Vind een LEGENDARY item in de MysteryBox!", 200, 8, 1200, AchievementType.UNLOCKABLES, false),
    EXCLUSIVE(22, "THE EXCLUSIVE CLUB!", "Vind een MysteryBox Exclusive item in de MysteryBox!", 200, 2, 300, AchievementType.UNLOCKABLES, false),
    MYSTERYBOX_OPEN(23, "It won't be a Mystery anymore!", "Open een MysteryBox", 100, 1, 200, AchievementType.GENERAL, false),
    DC_RIDE(24, "The dive to the Horizon.", "Maak een ritje op de DiveCoaster", 25, 1, 600, AchievementType.RIDES, false),
    MAE_SLAP(25, "MAWHEEEE!", "Launch MAETJE!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    TIM_SLAP(26, "Tim on top of the world!", "Launch Toptim24!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
    TS_RIDE(27, "Swinging around!", "Maak een ritje op The Swinger", 25, 1, 600, AchievementType.RIDES, false),
    NICK_SLAP(28, "NICKJEEEEDL", "Launch nickjedl!", 10, 1, 200, AchievementType.STAFFPUNCH, false),
//    DISCORD(29, "Kun je me nu horen?", "Link je Minecraft aan je Discord account!", 250, 1000, 3, AchievementType.GENERAL, false),
    CR_RIDE(30, "Crush gevonden?!", "Maak een ritje op The Crush", 25, 1, 600, AchievementType.RIDES, false),
    RICK_SLAP(31, "Bult op je Kop!", "Launch RickBult", 10, 1, 200, AchievementType.STAFFPUNCH, false);


    private int id;
    private String name;
    private String description;
    private int coinReward;
    private int keyReward;
    private int expReward;
    private AchievementType achievementType;
    private boolean mysteryAchievement;

    AchievementsEnum(int i, String s, String s1, int i1, int i2, int i3, AchievementType at, boolean mysteryAchievement) {
        this.id = i;
        this.name = s;
        this.description = s1;
        this.coinReward = i1;
        this.keyReward = i2;
        this.expReward = i3;
        this.achievementType = at;
        this.mysteryAchievement = mysteryAchievement;
    }

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

    public AchievementType getType() {
        return achievementType;
    }

    public int getExpReward() {
        return expReward;
    }

    public static AchievementsEnum getById(int id){
        for(AchievementsEnum achievement : AchievementsEnum.values()){
            if(achievement.getId() == id){
                return achievement;
            }
        }
        return null;
    }

    public boolean isMysteryAchievement() {
        return mysteryAchievement;
    }
}
