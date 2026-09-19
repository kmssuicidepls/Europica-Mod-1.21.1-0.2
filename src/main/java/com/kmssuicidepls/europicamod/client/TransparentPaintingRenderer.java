package com.kmssuicidepls.europicamod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TransparentPaintingRenderer extends EntityRenderer<Painting> {

    private static final ResourceLocation PAINTING_ATLAS =
            ResourceLocation.withDefaultNamespace("textures/atlas/paintings.png");

    private static RenderType paintingTranslucentNoCull(ResourceLocation texture) {
        RenderType.CompositeState state = RenderType.CompositeState.builder()
                .setShaderState(RenderStateShard.RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .createCompositeState(true);

        return RenderType.create(
                "europicamod_painting_translucent",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                false,
                true,
                state
        );
    }

    public TransparentPaintingRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Painting entity) {
        return PAINTING_ATLAS;
    }

    @Override
    public void render(Painting painting, float entityYaw, float partialTicks,
                        PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        PaintingVariant variant = painting.getVariant().value();
        int width = variant.width();
        int height = variant.height();

        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));

        poseStack.scale(0.0625F, 0.0625F, 0.0625F);

        PaintingTextureManager textures = Minecraft.getInstance().getPaintingTextures();
        TextureAtlasSprite artSprite = textures.get(variant);

        VertexConsumer consumer = buffer.getBuffer(paintingTranslucentNoCull(PAINTING_ATLAS));

        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        float halfW = (width * 16) / 2.0F;
        float halfH = (height * 16) / 2.0F;
        float z = -0.5F;

        float u0 = artSprite.getU0();
        float u1 = artSprite.getU1();
        float v0 = artSprite.getV0();
        float v1 = artSprite.getV1();

        vertex(consumer, matrix, normalMatrix, halfW, -halfH, z, u0, v1, packedLight);
        vertex(consumer, matrix, normalMatrix, -halfW, -halfH, z, u1, v1, packedLight);
        vertex(consumer, matrix, normalMatrix, -halfW, halfH, z, u1, v0, packedLight);
        vertex(consumer, matrix, normalMatrix, halfW, halfH, z, u0, v0, packedLight);

        poseStack.popPose();

        super.render(painting, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix, Matrix3f normalMatrix,
                                float x, float y, float z, float u, float v, int packedLight) {
        consumer.addVertex(matrix, x, y, z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(0.0F, 0.0F, 1.0F);
    }
}
