package chowie.betterbabymode.mixin;

import chowie.betterbabymode.util.BlockPosInt;
import chowie.betterbabymode.util.LogTimer;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
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
		if (source.is(DamageTypes.LAVA) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE)) {
			level.setBlock(BlockPos.containing(this.getX(), this.getY(), this.getZ()), Blocks.AIR.defaultBlockState(), 3);
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		ServerPlayer player = (ServerPlayer) (Object) this;
		BlockPos playerPos = player.getOnPos();
		HitResult result = player.pick(4.5, player.level().getServer().getTickCount(), false);

		if (result instanceof BlockHitResult blockResult && level().getBlockState(blockResult.getBlockPos()).is(BlockTags.LOGS)
		&& !LogTimer.INSTANCE.playerMap.containsKey(player)) {
			LogTimer.INSTANCE.setTimer(player, new BlockPosInt(80, blockResult.getBlockPos()));
		}

		if (player.isInWater()) {
			if (!player.getInventory().contains(ItemTags.BOATS)) {
				player.sendSystemMessage(Component.translatable("god.message.boat"));
				player.getInventory().add(Items.OAK_BOAT.getDefaultInstance());
			}
			player.setAirSupply(player.getMaxAirSupply());
		}

		if (player.getDeltaMovement().y() < -0.66) {
			player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 2, 1, true, false));
		}

		if (player.isInWall()) {
			BlockPos headPos = BlockPos.containing(player.getX(), player.getEyeY(), player.getZ());
			level().destroyBlock(headPos, true);
		}

		for (BlockPos block : BlockPos.betweenClosed(playerPos.offset(-1, -1, -1), playerPos.offset(1, 3, 1))) {
			if (level().getBlockState(block).is(Blocks.LAVA)) {
				level().setBlock(block, Blocks.OBSIDIAN.defaultBlockState(), 3);
			} else if (level().getBlockState(block).is(Blocks.POWDER_SNOW)) {
				level().setBlock(block, Blocks.WATER.defaultBlockState(), 3);
			} else if (level().getBlockState(block).is(Blocks.MAGMA_BLOCK)) {
				level().setBlock(block, Blocks.NETHERRACK.defaultBlockState(), 3);
			}
		}

		if (player.level().dimension().equals(Level.END) && player.getY() < -50) {
			player.teleportTo(player.getX(), 100, player.getZ());
			player.sendSystemMessage(Component.translatable("god.message.teleport_up"));
		}

		if (player.getMainHandItem().getItem().equals(Items.ELYTRA) && !player.getMainHandItem().isEnchanted()) {
            Registry<Enchantment> registry = level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

			ItemStack stack = Items.ELYTRA.getDefaultInstance();
			stack.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 10);
			stack.enchant(registry.getOrThrow(Enchantments.MENDING), 1);

			player.setItemInHand(InteractionHand.MAIN_HAND, stack);
		}

		if (player.getMainHandItem().getItem().equals(Items.TRIDENT) && !player.getMainHandItem().isEnchanted()) {
			Registry<Enchantment> registry = level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

			ItemStack stack = Items.TRIDENT.getDefaultInstance();
			stack.enchant(registry.getOrThrow(Enchantments.UNBREAKING), 10);
			stack.enchant(registry.getOrThrow(Enchantments.MENDING), 1);
			stack.enchant(registry.getOrThrow(Enchantments.CHANNELING), 1);
			stack.enchant(registry.getOrThrow(Enchantments.IMPALING), 10);
			stack.enchant(registry.getOrThrow(Enchantments.LOYALTY), 10);
			stack.enchant(registry.getOrThrow(Enchantments.RIPTIDE), 1);
			player.setItemInHand(InteractionHand.MAIN_HAND, stack);
		}

		for (Entity entity : player.level().getEntities(player, new AABB(player.getX() - 4, player.getY() - 2.5,
				player.getZ() - 4, player.getX() + 4, player.getY() + 2.5, player.getZ() + 4))) {
			if (entity instanceof Monster monster && !monster.is(EntityType.ENDER_DRAGON) && !monster.is(EntityType.WITHER)) {
				this.sendSystemMessage(Component.literal("<" + monster.getPlainTextName() + "> nope"));
				monster.hurtServer(player.level(), monster.damageSources().playerAttack(player), monster.getMaxHealth() * 2);
				player.level().sendParticles(
						ParticleTypes.LARGE_SMOKE,
						monster.getX(), monster.getY() + 0.5, monster.getZ(),
						20,
						0.2, 0.2, 0.2,
						0.1);
				monster.remove(RemovalReason.KILLED);
			}
		}
	}

	@Inject(method = "onEffectAdded", at = @At("HEAD"))
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
			player.addEffect(effect);
		}
	}
}