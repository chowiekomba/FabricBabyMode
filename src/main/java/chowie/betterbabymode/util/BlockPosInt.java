package chowie.betterbabymode.util;

import net.minecraft.core.BlockPos;

public class BlockPosInt {
    int time;
    BlockPos block;

    public BlockPosInt(int time, BlockPos block) {
        this.time = time;
        this.block = block;
    }

    public int getTime() {
        return time;
    }

    public BlockPos getBlockPos() {
        return block;
    }

    public void decrementTime() {
        time--;
    }
}
