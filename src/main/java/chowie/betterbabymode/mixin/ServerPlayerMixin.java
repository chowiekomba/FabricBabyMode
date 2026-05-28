package chowie.betterbabymode.mixin;

import chowie.betterbabymode.util.BlockPosInt;
import chowie.betterbabymode.util.LogTimer;
import com.mojang.authlib.GameProfile;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {

	@Shadow
    private int levitationStartTime;
	@Shadow
    private Vec3 levitationStartPos;

	public ServerPlayerMixin(Level level, GameProfile gameProfile) {
		super(level, gameProfile);
	}

	@Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
	private void hurtServer(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
		if (source.getEntity() instanceof Enemy) {
			source.getEntity().kill(level);
			this.sendSystemMessage(Component.literal("<" + source.getEntity().getPlainTextName() + "> nope"));
			cir.setReturnValue(false);
		} else if (source.is(DamageTypes.LAVA) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE)) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		ServerPlayer player = (ServerPlayer) (Object) this;

		HitResult result = player.pick(4.5, player.level().getServer().getTickCount(), false);
		if (result instanceof BlockHitResult blockResult && level().getBlockState(blockResult.getBlockPos()).is(BlockTags.LOGS)
		&& !LogTimer.INSTANCE.playerMap.containsKey(player)) {
			LogTimer.INSTANCE.setTimer(player, new BlockPosInt(80, blockResult.getBlockPos()));
		}

		if (player.isInWater() && !player.getInventory().contains(ItemTags.BOATS)) {
			player.sendSystemMessage(Component.translatable("god.message.boat"));
			player.getInventory().add(Items.OAK_BOAT.getDefaultInstance());
		}

		if (player.getDeltaMovement().y() < -0.66) {
			player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 2, 1, true, false));
		}

		if (player.isInWall()) {
			BlockPos headPos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
			level().destroyBlock(headPos, true);
		}

		BlockPos playerPos = player.getOnPos();
		for (BlockPos block : BlockPos.betweenClosed(playerPos.offset(-1, -1, -1), playerPos.offset(1, 3, 1))) {
			if (level().getBlockState(block).is(Blocks.LAVA)) {
				level().setBlock(block, Blocks.OBSIDIAN.defaultBlockState(), 3);
			} else if (level().getBlockState(block).is(Blocks.POWDER_SNOW)) {
				level().setBlock(block, Blocks.WATER.defaultBlockState(), 3);
			} else if (level().getBlockState(block).is(Blocks.MAGMA_BLOCK)) {
				level().setBlock(block, Blocks.NETHERRACK.defaultBlockState(), 3);
			}
		}
	}

	@Inject(method = "onEffectAdded", at = @At("HEAD"), cancellable = true)
	void onEffectAdded(MobEffectInstance effect, Entity source, CallbackInfo ci) {
		if (effect.is(MobEffects.SPEED) || effect.is(MobEffects.ABSORPTION) || effect.is(MobEffects.DOLPHINS_GRACE) ||
				effect.is(MobEffects.FIRE_RESISTANCE) || effect.is(MobEffects.HASTE) || effect.is(MobEffects.BREATH_OF_THE_NAUTILUS) ||
				effect.is(MobEffects.CONDUIT_POWER) || effect.is(MobEffects.HEALTH_BOOST) || effect.is(MobEffects.HERO_OF_THE_VILLAGE) ||
				effect.is(MobEffects.INSTANT_HEALTH) || effect.is(MobEffects.INVISIBILITY) || effect.is(MobEffects.JUMP_BOOST) ||
				effect.is(MobEffects.LUCK) || effect.is(MobEffects.NIGHT_VISION) || effect.is(MobEffects.REGENERATION) ||
				effect.is(MobEffects.RESISTANCE) || effect.is(MobEffects.SATURATION) || effect.is(MobEffects.STRENGTH) ||
				effect.is(MobEffects.WATER_BREATHING)) {
			ServerPlayer player = (ServerPlayer) (Object) this;
            effect = new MobEffectInstance(effect.getEffect(), -1, effect.getAmplifier());

			super.onEffectAdded(effect, source);
			player.connection.send(new ClientboundUpdateMobEffectPacket(this.getId(), effect, true));
			if (effect.is(MobEffects.LEVITATION)) {
				levitationStartTime = this.tickCount;
				levitationStartPos = this.position();
			}

			CriteriaTriggers.EFFECTS_CHANGED.trigger(player, source);
			ci.cancel();
		}
	}
}