package io.github.frqnny.tacocraft.init;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.block.*;
import io.github.frqnny.tacocraft.block.crop.CornBlock;
import io.github.frqnny.tacocraft.blockentity.ComalBlockEntity;
import io.github.frqnny.tacocraft.blockentity.FurnaceBlockEntity;
import io.github.frqnny.tacocraft.blockentity.OpenPotBlockEntity;
import io.github.frqnny.tacocraft.blockentity.PencaBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final Block FURNACE_BLOCK = new FurnaceBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.5F).lightLevel(createLightLevelFromBlockState()));
    public static final Block COMAL = new ComalBlock(FabricBlockSettings.of(Material.SUPPORTED));
    public static final Block CORN_BLOCK = new CornBlock(8, FabricBlockSettings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final Block CORN_BRICK = new Block(FabricBlockSettings.copyOf(Blocks.BRICKS));
    public static final Block OPEN_POT = new OpenPotBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON));
    public static final Block PENCA = new PencaBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final Block TACO_BOWL = new TacoBowlBlock(FabricBlockSettings.copyOf(Blocks.CAKE));

    public static BlockEntityType<FurnaceBlockEntity> FURNACE_BLOCK_ENTITY = BlockEntityType.Builder.create(FurnaceBlockEntity::new, FURNACE_BLOCK).build(null);
    public static BlockEntityType<ComalBlockEntity> COMAL_BLOCK_ENTITY = BlockEntityType.Builder.create(ComalBlockEntity::new, COMAL).build(null);
    public static BlockEntityType<OpenPotBlockEntity> OPEN_POT_BLOCK_ENTITY = BlockEntityType.Builder.create(OpenPotBlockEntity::new, OPEN_POT).build(null);
    public static BlockEntityType<PencaBlockEntity> PENCA_BLOCK_ENTITY = BlockEntityType.Builder.create(PencaBlockEntity::new, PENCA).build(null);

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, FurnaceBlock.ID, FURNACE_BLOCK);
        Registry.register(Registry.BLOCK, ComalBlock.ID, COMAL);
        Registry.register(Registry.BLOCK, CornBlock.ID, CORN_BLOCK);
        Registry.register(Registry.BLOCK, OpenPotBlock.ID, OPEN_POT);
        Registry.register(Registry.BLOCK, PencaBlock.ID, PENCA);
        Registry.register(Registry.BLOCK, TacoCraft.id("corn_brick"), CORN_BRICK);
        Registry.register(Registry.BLOCK, TacoBowlBlock.ID, TACO_BOWL);
        registerBlockEntities();
    }

    public static void registerBlockEntities() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, FurnaceBlock.ID, FURNACE_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, ComalBlock.ID, COMAL_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, OpenPotBlock.ID, OPEN_POT_BLOCK_ENTITY);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, PencaBlock.ID, PENCA_BLOCK_ENTITY);
    }

    private static ToIntFunction<BlockState> createLightLevelFromBlockState() {
        return (blockState) -> (Boolean) blockState.get(Properties.LIT) ? 13 : 0;
    }
}
