package chowie.betterbabymode.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.trading.MerchantOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class VillagerMixin {
    @Inject(method = "updateTrades", at = @At("TAIL"))
    void updateTrades(ServerLevel level, CallbackInfo ci) {
        Villager villager = (Villager) (Object) this;
        MerchantOffers offers = villager.getOffers();

        offers.forEach(trade -> trade.setSpecialPriceDiff(Integer.MIN_VALUE));
    }
}
