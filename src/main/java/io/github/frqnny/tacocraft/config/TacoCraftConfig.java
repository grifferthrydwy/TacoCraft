package io.github.frqnny.tacocraft.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Syncing;

@Syncing
public class TacoCraftConfig implements draylar.omegaconfig.api.Config {
    @Comment("Adds Corn Seeds to Grass Loot Table. Turn off if you do not want TacoCraft Seeds to drop its seeds when grass breaks")
    public boolean addCornSeedsToGrassLootTable = true;

    public int penca_cook_time = 6000;

    public int tortilla_cook_time = 300;

    @Override
    public String getName() {
        return "tacocraft";
    }
}
