package chowie.betterbabymode.mixin;

import chowie.betterbabymode.util.BlockPosInt;
import chowie.betterbabymode.util.LogTimer;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {

	public ServerPlayerMixin(Level level, GameProfile gameProfile) {
		super(level, gameProfile);
	}

	@Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
	private void hurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
		if (source.getEntity() instanceof Monster monster) {
			monster.kill(level);
			this.sendSystemMessage(Component.literal("<" + monster.getPlainTextName() + "> nope"));
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		ServerPlayer player = (ServerPlayer) (Object) this;

		HitResult result = player.pick(4.5, player.level().getServer().getTickCount(), false);
		if (result instanceof BlockHitResult blockResult && player.level().getBlockState(blockResult.getBlockPos()).is(BlockTags.LOGS)
		&& !LogTimer.INSTANCE.playerMap.containsKey(player)) {
			LogTimer.INSTANCE.setTimer(player, new BlockPosInt(80, blockResult.getBlockPos()));
		}

		if (player.isInWater() && !player.getInventory().contains(ItemTags.BOATS)) {
			player.sendSystemMessage(Component.translatable("god.message.boat"));
			player.getInventory().add(Items.OAK_BOAT.getDefaultInstance());
		}
	}
}