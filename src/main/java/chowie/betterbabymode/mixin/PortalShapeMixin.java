package chowie.betterbabymode.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PortalShape.class)
public class PortalShapeMixin {
    @Shadow
    @Final
    @Mutable
    private static BlockBehaviour.StatePredicate FRAME;

    @Inject(method = "<init>(Lnet/minecraft/core/Direction$Axis;ILnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;II)V"
            , at = @At(value = "TAIL"))
    private void PortalShape(Direction.Axis axis, int portalBlockCount, Direction rightDir, BlockPos bottomLeft, int width,
                             int height, CallbackInfo ci) {
        FRAME = (BlockState blockState, BlockGetter _, BlockPos _) -> blockState.is(Blocks.OBSIDIAN) ||
                blockState.is(Blocks.DIRT);
    }
}
