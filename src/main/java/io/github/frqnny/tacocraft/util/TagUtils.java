package io.github.frqnny.tacocraft.util;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagUtils {
    public static final Tag<Block> FARMLAND = TagRegistry.block(new Identifier("c","farmland"));

    public static void init() {

    }
}
