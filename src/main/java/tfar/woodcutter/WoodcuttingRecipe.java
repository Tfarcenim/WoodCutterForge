package tfar.woodcutter;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class WoodcuttingRecipe extends SingleItemRecipe {

	public static final IRecipeType<WoodcuttingRecipe> TYPE = IRecipeType.register(WoodCutter.MODID +":"+ WoodCutter.MODID);

	public WoodcuttingRecipe(ResourceLocation id, String group, Ingredient input, ItemStack output) {
		super(TYPE, WoodCutter.WOODCUTTING, id, group, input, output);
	}

	public boolean matches(IInventory inv, World world) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	public ItemStack getIcon() {
		return new ItemStack(WoodCutter.woodcutter);
	}

	public static class Serializer2<T extends SingleItemRecipe> extends Serializer<T> {
		//sidestep an Access Widener here
		public Serializer2(IRecipeFactory<T> recipeFactory) {
			super(recipeFactory);
		}
	}
}
