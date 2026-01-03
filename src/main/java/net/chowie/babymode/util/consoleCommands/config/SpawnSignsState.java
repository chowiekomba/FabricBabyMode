package net.chowie.babymode.util.consoleCommands.config;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;

public class SpawnSignsState extends PersistentState {
    public static boolean spawnSigns = true;

    public static final PersistentState.Type<SpawnSignsState> TYPE = new PersistentState.Type<>(
            SpawnSignsState::new,
            SpawnSignsState::createFromNbt,
            null
    );

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putBoolean("spawnSigns", spawnSigns);
        return nbt;
    }

    public static SpawnSignsState createFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        SpawnSignsState state = new SpawnSignsState();
        state.spawnSigns = nbt.getBoolean("spawnSigns");
        return state;
    }

    public static SpawnSignsState getServerState(MinecraftServer server) {
        return server.getWorld(net.minecraft.world.World.OVERWORLD)
                .getPersistentStateManager()
                .getOrCreate(TYPE, "spawnSigns_state");
    }
}