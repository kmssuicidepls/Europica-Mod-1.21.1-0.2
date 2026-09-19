package com.kmssuicidepls.europicamod.registry;

import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.menu.AtmMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, EuropicaMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<AtmMenu>> ATM_MENU = MENUS.register("atm_menu",
            () -> IMenuTypeExtension.create((id, inv, buf) -> new AtmMenu(id, inv, ContainerLevelAccess.NULL)));
}