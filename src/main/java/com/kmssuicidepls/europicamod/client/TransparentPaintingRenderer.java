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

/**
 * Replacement painting renderer that:
 *  - draws ONLY the front artwork face (no back/side "canvas box" geometry)
 *  - uses a translucent, non-culled render type so PNG alpha is honored
 *
 * Requires that your custom painting art textures actually contain an alpha
 * channel (straight, non-premultiplied alpha works fine for Minecraft's
 * texture atlas).
 */
public class TransparentPaintingRenderer extends EntityRenderer<Painting> {

    /**
     * There's no public constant for this (unlike TextureAtlas.LOCATION_BLOCKS),
     * so it's built the same way vanilla builds that one: namespace + path to
     * the generated atlas image.
     */
    private static final ResourceLocation PAINTING_ATLAS =
            ResourceLocation.withDefaultNamespace("textures/atlas/paintings.png");

    /**
     * A translucent render type, based on vanilla's entityTranslucent, but
     * with backface culling disabled. Culling is turned off defensively —
     * without it, if the quad winding ends up facing away from the camera
     * on your setup, the painting would render invisible instead of just
     * looking wrong from behind.
     */
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
                true, // sort on upload - important for correct translucency blending
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

        // Match vanilla's orientation: face outward from the block it's mounted on.
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));

        // Quad coordinates below are built in raw "pixel" units (16 units per
        // block). This scale converts those into actual block-sized world
        // units — without it, everything renders 16x too large.
        poseStack.scale(0.0625F, 0.0625F, 0.0625F);

        PaintingTextureManager textures = Minecraft.getInstance().getPaintingTextures();
        TextureAtlasSprite artSprite = textures.get(variant);

        VertexConsumer consumer = buffer.getBuffer(paintingTranslucentNoCull(PAINTING_ATLAS));

        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix = pose.pose();
        Matrix3f normalMatrix = pose.normal();

        float halfW = (width * 16) / 2.0F;
        float halfH = (height * 16) / 2.0F;
        float z = -0.5F; // same depth vanilla's front face sits at

        float u0 = artSprite.getU0();
        float u1 = artSprite.getU1();
        float v0 = artSprite.getV0();
        float v1 = artSprite.getV1();

        // Single flat quad — no back face, no side faces, no frame geometry.
        // Note: v0 (top of texture) pairs with +halfH (top of quad in world),
        // and v1 (bottom of texture) pairs with -halfH (bottom of quad).
        vertex(consumer, matrix, normalMatrix, halfW, -halfH, z, u0, v1, packedLight);
        vertex(consumer, matrix, normalMatrix, -halfW, -halfH, z, u1, v1, packedLight);
        vertex(consumer, matrix, normalMatrix, -halfW, halfH, z, u1, v0, packedLight);
        vertex(consumer, matrix, normalMatrix, halfW, halfH, z, u0, v0, packedLight);

        poseStack.popPose();

        // Keeps name tag / other base EntityRenderer behavior intact.
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
