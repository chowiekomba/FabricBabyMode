package chowie.betterbabymode.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronGolem.class)
public class IronGolemMixin {
    @Inject(method = "canAttack", at = @At("HEAD"), cancellable = true)
    void setPersistentAngerTarget(LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof Player) {
            cir.setReturnValue(false);
        }
    }
}
