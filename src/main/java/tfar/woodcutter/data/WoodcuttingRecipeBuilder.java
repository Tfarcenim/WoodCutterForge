package tfar.woodcutter.data;

import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import tfar.woodcutter.WoodCutter;

public class WoodcuttingRecipeBuilder extends SingleItemRecipeBuilder {
	public WoodcuttingRecipeBuilder(IRecipeSerializer<?> serializerIn, Ingredient ingredientIn, IItemProvider resultProviderIn, int countIn) {
		super(serializerIn, ingredientIn, resultProviderIn, countIn);
	}

	public static SingleItemRecipeBuilder woodcuttingRecipe(Ingredient ingredientIn, IItemProvider resultIn) {
		return new SingleItemRecipeBuilder(WoodCutter.WOODCUTTING, ingredientIn, resultIn, 1);
	}

	public static SingleItemRecipeBuilder woodcuttingRecipe(Ingredient ingredientIn, IItemProvider resultIn, int countIn) {
		return new SingleItemRecipeBuilder(WoodCutter.WOODCUTTING, ingredientIn, resultIn, countIn);
	}

}
