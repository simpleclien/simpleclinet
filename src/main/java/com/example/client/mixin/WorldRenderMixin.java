package com.example.client.mixin;

import com.example.client.SimpleClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRenderMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void renderESP(CallbackInfo ci) {
        if (!SimpleClient.playerEspEnabled && !SimpleClient.storageEspEnabled && !SimpleClient.spawnerFinderEnabled) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        if (SimpleClient.playerEspEnabled) {
            for (Entity entity : client.world.getEntities()) {
                if (entity instanceof PlayerEntity && entity != client.player) {
                    // Entity rendering bounds hook
                }
            }
        }
    }
}
