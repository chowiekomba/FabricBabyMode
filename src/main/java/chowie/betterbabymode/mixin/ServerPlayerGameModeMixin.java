package chowie.betterbabymode.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {

    @Shadow
    protected ServerLevel level;

    @Inject(method = "destroyBlock", at = @At("HEAD"))
    void destroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.STONE)) {
            int random = (int) (Math.random() * 100);
            if (random < 30) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.COAL.getDefaultInstance()));
            } else if (random < 50) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.RAW_COPPER.getDefaultInstance()));
            } else if (random < 60) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.RAW_IRON.getDefaultInstance()));
            } else if (random < 70) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.RAW_GOLD.getDefaultInstance()));
            } else if (random < 80) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.REDSTONE.getDefaultInstance()));
            } else if (random < 89) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.LAPIS_LAZULI.getDefaultInstance()));
            } else if (random < 96) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.EMERALD.getDefaultInstance()));
            } else {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.DIAMOND.getDefaultInstance()));
            }
        } else if (state.is(Blocks.DEEPSLATE)) {
            int random = (int) (Math.random() * 100);
            if (random < 90) {
                return;
            }
            if (random < 92) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.RAW_IRON.getDefaultInstance()));
            } else if (random < 96) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.EMERALD.getDefaultInstance()));
            } else if (random < 98) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.DIAMOND.getDefaultInstance()));
            } else {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), Items.ANCIENT_DEBRIS.getDefaultInstance()));
            }
        }
    }
}
