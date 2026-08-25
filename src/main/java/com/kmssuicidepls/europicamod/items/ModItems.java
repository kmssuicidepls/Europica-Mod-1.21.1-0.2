package com.kmssuicidepls.europicamod.items;

import com.kmssuicidepls.europicamod.EuropicaMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EuropicaMod.MOD_ID);

    public static final DeferredItem<Item> EUROPICADUCAT = ITEMS.register("europicaducat",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
