package io.github.franiscoder.tacocraft.init;

import io.github.franiscoder.tacocraft.TacoCraft;
import io.github.franiscoder.tacocraft.block.ComalBlock;
import io.github.franiscoder.tacocraft.block.FurnaceBlock;
import io.github.franiscoder.tacocraft.block.crop.CornBlock;
import io.github.franiscoder.tacocraft.blockentity.ComalBlockEntity;
import io.github.franiscoder.tacocraft.blockentity.FurnaceBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final FurnaceBlock FURNACE_BLOCK = new FurnaceBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.5F).lightLevel(createLightLevelFromBlockState()));
    public static final ComalBlock COMAL = new ComalBlock(FabricBlockSettings.of(Material.SUPPORTED).build());

    public static final CornBlock CORN_BLOCK = new CornBlock(7, FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).build());

    public static BlockEntityType<FurnaceBlockEntity> FURNACE_BLOCK_ENTITY;

    public static BlockEntityType<ComalBlockEntity> COMAL_BLOCK_ENTITY;

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, FurnaceBlock.ID, FURNACE_BLOCK);
        Registry.register(Registry.BLOCK, ComalBlock.ID, COMAL);

        Registry.register(Registry.BLOCK, CornBlock.ID, CORN_BLOCK);
    }

    public static void registerBlockItems() {
        Registry.register(Registry.ITEM, FurnaceBlock.ID, new BlockItem(FURNACE_BLOCK, new Item.Settings().group(TacoCraft.ITEM_GROUP)));
        Registry.register(Registry.ITEM, ComalBlock.ID, new BlockItem(COMAL, new Item.Settings().group(TacoCraft.ITEM_GROUP)));
    }

    public static void registerBlockEntities() {
        FURNACE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, FurnaceBlock.ID, BlockEntityType.Builder.create(FurnaceBlockEntity::new, FURNACE_BLOCK).build(null));
        COMAL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ComalBlock.ID, BlockEntityType.Builder.create(ComalBlockEntity::new, COMAL).build(null));
    }

    private static ToIntFunction<BlockState> createLightLevelFromBlockState() {
        return (blockState) -> (Boolean) blockState.get(Properties.LIT) ? 13 : 0;
    }
}
