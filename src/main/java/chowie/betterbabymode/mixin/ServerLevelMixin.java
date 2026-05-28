package chowie.betterbabymode.mixin;

import chowie.betterbabymode.listeners.ServerMessageListener;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Shadow
    @Final
    private MinecraftServer server;

    @Inject(method = "addFreshEntity", at = @At("HEAD"))
    private void addFreshEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof WitherBoss wither) {
            ServerMessageListener.addWither(wither);
            server.getPlayerList().broadcastSystemMessage(Component.translatable("entity.bbm.wither"), false);
        } else if (entity instanceof EnderDragon dragon) {
            ServerMessageListener.setDragon(dragon);
            server.getPlayerList().broadcastSystemMessage(Component.translatable("entity.bbm.dragon"), false);
        }
    }
}
