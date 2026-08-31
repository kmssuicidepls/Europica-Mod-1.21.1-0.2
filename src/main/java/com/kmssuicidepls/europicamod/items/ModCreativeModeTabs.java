package com.kmssuicidepls.europicamod.items;

import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EuropicaMod.MOD_ID);

    public static final Supplier<CreativeModeTab> EUROPICA_MOD = CREATIVE_MODE_TAB.register("europica_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EUROPICADUCAT.get()))
                    .title(Component.translatable("creativetab.europicamod.europica_mod"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EUROPICADUCAT);
                        output.accept(ModItems.EUROPICAENTRANCETICKET);
                        output.accept(ModItems.EUROPICARIDETICKET);
                        output.accept(ModItems.TEXAS_ANTHEM_MUSIC_DISC);
                        output.accept(ModItems.AMERICAN_ANTHEM_MUSIC_DISC);
                        output.accept(ModItems.DUTCH_ANTHEM_MUSIC_DISC);
                        output.accept(ModItems.GERMAN_ANTHEM_MUSIC_DISC);
                        output.accept(ModItems.FRENCH_ANTHEM_MUSIC_DISC);
                        output.accept(ModItems.BRITISH_ANTHEM_MUSIC_DISC);

                        output.accept(ModBlocks.PLACEHOLDER_BLOCK);
                        output.accept(ModBlocks.INVISIBLE_BLOCK);
                        output.accept(ModBlocks.BIN_BLOCK);
                        output.accept(ModBlocks.GODSCOUNTRY);
                        output.accept(ModBlocks.AMERICA_BLOCK);
                        output.accept(ModBlocks.DUTCH_BLOCK);
                        output.accept(ModBlocks.GERMAN_BLOCK);
                        output.accept(ModBlocks.FRANCE_BLOCK);
                        output.accept(ModBlocks.BRITISH_BLOCK);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}