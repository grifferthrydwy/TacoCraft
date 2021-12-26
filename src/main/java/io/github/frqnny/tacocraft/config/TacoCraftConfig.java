package io.github.frqnny.tacocraft.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;
import draylar.omegaconfig.api.Syncing;

@Syncing
public class TacoCraftConfig implements Config {
    @Comment("Adds Corn Seeds to Grass Loot Table. Turn off if you do not want TacoCraft Seeds to drop its seeds when grass breaks")
    public boolean addCornSeedsToGrassLootTable = true;

    @Comment("Amount of time pencas take to cook in ticks (20 per second)")
    @Syncing
    public int penca_cook_time = 6000;

    @Comment("Amount of time tortillas take to cook (20 per second)")
    @Syncing
    public int tortilla_cook_time = 300;

    @Comment("Amount of barbacoa you get from one penca")
    @Syncing
    public int barbacoa_per_penca = 7;

    @Comment("Chance of corn spawning. Is in 1 per x chunks")
    @Syncing
    public int corn_gen_chance = 300;

    @Override
    public String getName() {
        return "tacocraft";
    }

    @Override
    public String getExtension() {
        return "json5";
    }
}
