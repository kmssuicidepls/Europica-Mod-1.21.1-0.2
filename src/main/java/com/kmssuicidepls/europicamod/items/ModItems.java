package com.kmssuicidepls.europicamod.items;

import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.sound.ModSounds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EuropicaMod.MOD_ID);

    public static final DeferredItem<Item> EUROPICADUCAT = ITEMS.register("europicaducat",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EUROPICAENTRANCETICKET = ITEMS.register("europicaentranceticket",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EUROPICARIDETICKET = ITEMS.register("europicarideticket",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TEXAS_ANTHEM_MUSIC_DISC = ITEMS.register("texas_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.TEXAS_ANTHEM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> AMERICAN_ANTHEM_MUSIC_DISC = ITEMS.register("american_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.AMERICAN_ANTHEM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> DUTCH_ANTHEM_MUSIC_DISC = ITEMS.register("dutch_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DUTCH_ANTHEM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> GERMAN_ANTHEM_MUSIC_DISC = ITEMS.register("german_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.GERMAN_ANTHEM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> FRENCH_ANTHEM_MUSIC_DISC = ITEMS.register("french_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.FRENCH_ANTHEM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> BRITISH_ANTHEM_MUSIC_DISC = ITEMS.register("british_anthem_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BRITISH_ANTHEM_KEY).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
