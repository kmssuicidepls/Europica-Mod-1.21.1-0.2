package com.kmssuicidepls.europicamod.sound;

import com.kmssuicidepls.europicamod.EuropicaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EuropicaMod.MOD_ID);

    public static final Supplier<SoundEvent> GODSCOUNTRY_BREAK = registerSoundEvent("godscountry_break");
    public static final Supplier<SoundEvent> GODSCOUNTRY_STEP = registerSoundEvent("godscountry_step");
    public static final Supplier<SoundEvent> GODSCOUNTRY_PLACE = registerSoundEvent("godscountry_place");
    public static final Supplier<SoundEvent> GODSCOUNTRY_HIT = registerSoundEvent("godscountry_hit");
    public static final Supplier<SoundEvent> GODSCOUNTRY_FALL = registerSoundEvent("godscountry_fall");

    public static final Supplier<SoundEvent> DUTCH_BREAK = registerSoundEvent("dutch_break");
    public static final Supplier<SoundEvent> DUTCH_STEP = registerSoundEvent("dutch_step");
    public static final Supplier<SoundEvent> DUTCH_PLACE = registerSoundEvent("dutch_place");
    public static final Supplier<SoundEvent> DUTCH_HIT = registerSoundEvent("dutch_hit");
    public static final Supplier<SoundEvent> DUTCH_FALL = registerSoundEvent("dutch_fall");


    public static final DeferredSoundType GODSCOUNTRY_SOUNDS = new DeferredSoundType(5f, 1f,
            ModSounds.GODSCOUNTRY_BREAK, ModSounds.GODSCOUNTRY_STEP, ModSounds.GODSCOUNTRY_PLACE, ModSounds.GODSCOUNTRY_HIT, ModSounds.GODSCOUNTRY_FALL);

    public static final DeferredSoundType DUTCH_SOUNDS = new DeferredSoundType(5f, 1f,
            ModSounds.DUTCH_BREAK, ModSounds.DUTCH_STEP, ModSounds.DUTCH_PLACE, ModSounds.DUTCH_HIT, ModSounds.DUTCH_FALL);

    public static final Supplier<SoundEvent> TEXAS_ANTHEM = registerSoundEvent("texas_anthem");
    public static final ResourceKey<JukeboxSong> TEXAS_ANTHEM_KEY = createSong("texas_anthem");

    public static final Supplier<SoundEvent> AMERICAN_ANTHEM = registerSoundEvent("american_anthem");
    public static final ResourceKey<JukeboxSong> AMERICAN_ANTHEM_KEY = createSong("american_anthem");

    public static final Supplier<SoundEvent> DUTCH_ANTHEM = registerSoundEvent("dutch_anthem");
    public static final ResourceKey<JukeboxSong> DUTCH_ANTHEM_KEY = createSong("dutch_anthem");

    public static final Supplier<SoundEvent> GERMAN_ANTHEM = registerSoundEvent("german_anthem");
    public static final ResourceKey<JukeboxSong> GERMAN_ANTHEM_KEY = createSong("german_anthem");

    public static final Supplier<SoundEvent> FRENCH_ANTHEM = registerSoundEvent("french_anthem");
    public static final ResourceKey<JukeboxSong> FRENCH_ANTHEM_KEY = createSong("french_anthem");

    public static final Supplier<SoundEvent> BRITISH_ANTHEM = registerSoundEvent("british_anthem");
    public static final ResourceKey<JukeboxSong> BRITISH_ANTHEM_KEY = createSong("british_anthem");

    private static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, name));
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}