package com.kmssuicidepls.europicamod.client;

import com.kmssuicidepls.europicamod.EuropicaMod; // adjust to your mod's actual main class/MODID holder
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Registers TransparentPaintingRenderer in place of vanilla's PaintingRenderer.
 * Runs on the mod event bus, client side only.
 */
@EventBusSubscriber(modid = EuropicaMod.MOD_ID , bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRendererSetup {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityType.PAINTING, TransparentPaintingRenderer::new);
    }
}
