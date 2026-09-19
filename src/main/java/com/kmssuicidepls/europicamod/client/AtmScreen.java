package com.kmssuicidepls.europicamod.client;

import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.menu.AtmMenu;
import com.kmssuicidepls.europicamod.network.BuyCoinsPayload;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

public class AtmScreen extends AbstractContainerScreen<AtmMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, "textures/gui/atmgui.png");

    private static final ResourceLocation BUTTON =
            ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, "atm_button");
    private static final ResourceLocation BUTTON_HOVER =
            ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, "atm_button_hover");

    private static final int BTN_SIZE = 18;

    public AtmScreen(AtmMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 256;
        this.imageHeight = 220;
    }

    @Override
    protected void init() {
        super.init();
        addCoinButton(22, 17, 1);
        addCoinButton(22, 45, 8);
        addCoinButton(127, 17, 16);
        addCoinButton(127, 45, 64);
    }

    private void addCoinButton(int x, int y, int amount) {
        addRenderableWidget(new CoinButton(leftPos + x, topPos + y, amount));
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        g.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mouseX, int mouseY) {
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        renderTooltip(g, mouseX, mouseY);
    }

    private static class CoinButton extends Button {
        CoinButton(int x, int y, int amount) {
            super(x, y, BTN_SIZE, BTN_SIZE, Component.literal(amount + " Coins"),
                    b -> PacketDistributor.sendToServer(new BuyCoinsPayload(amount)),
                    DEFAULT_NARRATION);
        }

        @Override
        protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
            g.blitSprite(isHoveredOrFocused() ? BUTTON_HOVER : BUTTON,
                    getX(), getY(), getWidth(), getHeight());
        }
    }
}