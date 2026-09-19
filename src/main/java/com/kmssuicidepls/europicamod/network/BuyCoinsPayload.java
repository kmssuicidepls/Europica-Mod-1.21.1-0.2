package com.kmssuicidepls.europicamod.network;
import com.kmssuicidepls.europicamod.EuropicaMod;
import com.kmssuicidepls.europicamod.menu.AtmMenu;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BuyCoinsPayload(int amount) implements CustomPacketPayload {
    public static final Type<BuyCoinsPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(EuropicaMod.MOD_ID, "buy_coins"));

    public static final StreamCodec<ByteBuf, BuyCoinsPayload> STREAM_CODEC =
            ByteBufCodecs.VAR_INT.map(BuyCoinsPayload::new, BuyCoinsPayload::amount);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(BuyCoinsPayload payload, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) return;

        int amount = payload.amount();
        boolean validAmount = amount == 1 || amount == 8 || amount == 16 || amount == 64;

        if (validAmount && player.containerMenu instanceof AtmMenu menu) {
            menu.giveCoins(player, amount);
        }
    }
}