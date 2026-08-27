package com.kmssuicidepls.europicamod.block;

import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.items.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(EuropicaMod.MOD_ID);

    public static final DeferredBlock<Block> PLACEHOLDER_BLOCK = registerBlock("placeholder_block",
            () -> {
                return new Block(BlockBehaviour.Properties.of()
                        .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANVIL));
            });

    public static final DeferredBlock<Block> FRANCE_BLOCK = registerBlock("france_block",
            () -> new DropExperienceBlock(UniformInt.of(100, 3000),
                    BlockBehaviour.Properties.of().strength(1f).sound(SoundType.BONE_BLOCK)));

    public static final DeferredBlock<Block> INVISIBLE_BLOCK = registerBlock("invisible_block",
            () -> new DropExperienceBlock(UniformInt.of(100, 3000),
                    BlockBehaviour.Properties.of().strength(-2f).noOcclusion().sound(SoundType.ANVIL)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem (String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }


    public static void register (IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
