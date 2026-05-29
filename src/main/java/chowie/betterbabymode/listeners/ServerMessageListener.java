package chowie.betterbabymode.listeners;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class ServerMessageListener {
    private static EnderDragon dragon;
    private static final List<WitherBoss> withers = new ArrayList<>();

    public static void register() {
        ServerMessageEvents.CHAT_MESSAGE.register((message, sender, _) -> {
            if (message.signedContent().toLowerCase().contains("i'd love to have some blaze rods, please? :)")) {
                ItemStack blazeRods = Items.BLAZE_ROD.getDefaultInstance();
                blazeRods.setCount(4);
                sender.addItem(blazeRods);
            } else if (message.signedContent().toLowerCase().contains("i wish i had ender pearls, too")) {
                ItemStack enderPearl = Items.ENDER_PEARL.getDefaultInstance();
                enderPearl.setCount(4);
                sender.addItem(enderPearl);
            } else if (message.signedContent().toLowerCase().contains("no u")) {
                if (dragon != null) {
                    dragon.hurtServer(sender.level(), dragon.damageSources().playerAttack(sender), dragon.getMaxHealth());
                    dragon.kill(sender.level());
                    dragon = null;
                }
                if (!withers.isEmpty()) {
                    for (Entity entity : withers) {
                        entity.kill(sender.level());
                    }
                    withers.clear();
                }
            }
        });
    }

    public static void setDragon(EnderDragon dragon1) {
        dragon = dragon1;
    }

    public static void addWither(WitherBoss wither) {
        withers.add(wither);
    }
}
