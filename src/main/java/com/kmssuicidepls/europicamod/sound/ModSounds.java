package com.kmssuicidepls.europicamod.sound;

import com.kmssuicidepls.europicamod.EuropicaMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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

    public static final DeferredSoundType GODSCOUNTRY_SOUNDS = new DeferredSoundType(5f, 1f,
            ModSounds.GODSCOUNTRY_BREAK, ModSounds.GODSCOUNTRY_STEP, ModSounds.GODSCOUNTRY_PLACE, ModSounds.GODSCOUNTRY_HIT, ModSounds.GODSCOUNTRY_FALL);

    public static Supplier<SoundEvent> registerSoundEvent (String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}