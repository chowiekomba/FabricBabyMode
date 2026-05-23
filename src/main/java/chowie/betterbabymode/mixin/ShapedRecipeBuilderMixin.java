package chowie.betterbabymode.mixin;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ShapedRecipeBuilder.class)
public interface ShapedRecipeBuilderMixin {
    @Invoker("<init>")
    static ShapedRecipeBuilder newShapedRecipeBuilder(final HolderGetter<Item> items, final RecipeCategory category,
                                    final ItemStackTemplate result) {
        throw new AssertionError();
    }
}
