package com.kmssuicidepls.europicamod.menu;

import com.kmssuicidepls.europicamod.block.ModBlocks;
import com.kmssuicidepls.europicamod.items.ModItems;
import com.kmssuicidepls.europicamod.registry.ModMenuTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AtmMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;

    public AtmMenu(int id, Inventory playerInv, ContainerLevelAccess access) {
        super(ModMenuTypes.ATM_MENU.get(), id);
        this.access = access;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 36 + col * 18, 137 + row * 18));
            }
        }
        // hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 36 + col * 18, 195));
        }
    }

    public void giveCoins(ServerPlayer player, int amount) {
        ItemStack stack = new ItemStack(ModItems.EUROPICADUCAT.get(), amount);
        player.getInventory().add(stack);
        if (!stack.isEmpty()) {
            player.drop(stack, false);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, ModBlocks.ATM_BLOCK.get());
    }
}