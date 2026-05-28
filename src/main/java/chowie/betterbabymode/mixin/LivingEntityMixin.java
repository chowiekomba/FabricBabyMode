package chowie.betterbabymode.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow
    protected boolean dead;

    @Inject(method = "die", at = @At("TAIL"))
    private void kill(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity instanceof Animal) {
            entity.setHealth(20);
            this.dead = false;
            entity.level().addFreshEntity(entity);
        } else if (entity instanceof Blaze) {
            ItemStack stack = Items.BLAZE_ROD.getDefaultInstance();
            stack.setCount(12);
            entity.level().addFreshEntity(new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack));
        } else if (entity instanceof WitherSkeleton) {
            ItemStack stack = Items.WITHER_SKELETON_SKULL.getDefaultInstance();
            entity.level().addFreshEntity(new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack));
        } else if (entity instanceof EnderMan) {
            ItemStack stack = Items.ENDER_PEARL.getDefaultInstance();
            stack.setCount(2);
            entity.level().addFreshEntity(new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), stack));
        }
    }
}
