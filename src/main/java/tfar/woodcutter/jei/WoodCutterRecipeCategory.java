package tfar.woodcutter.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.config.Constants;
import mezz.jei.recipes.ExtendableRecipeCategoryHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import tfar.woodcutter.WoodCutter;
import tfar.woodcutter.WoodcuttingRecipe;

import java.util.function.Function;

public class WoodCutterRecipeCategory implements IExtendableRecipeCategory<WoodcuttingRecipe, ICraftingCategoryExtension> {
	private static final int inputSlot = 0;
	private static final int outputSlot = 1;
	public static final int width = 82;
	public static final int height = 34;
	private final IDrawable background;
	private final IDrawable icon;
	private final String localizedName;
	private final IModIdHelper modIdHelper;
	private final ExtendableRecipeCategoryHelper<IRecipe<?>, ICraftingCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper(WoodcuttingRecipe.class);

	public WoodCutterRecipeCategory(IGuiHelper guiHelper, IModIdHelper modIdHelper) {
		this.modIdHelper = modIdHelper;
		ResourceLocation location = Constants.RECIPE_GUI_VANILLA;
		this.background = guiHelper.createDrawable(location, 0, 220, 82, 34);
		this.icon = guiHelper.createDrawableIngredient(new ItemStack(WoodCutter.woodcutter));
		this.localizedName = I18n.format("gui.jei.category.woodCutter");
	}

	public <R extends WoodcuttingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends ICraftingCategoryExtension> extensionFactory) {
		this.extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFactory);
	}

	public ResourceLocation getUid() {
		return WoodCutter.ID;
	}

	public Class<? extends WoodcuttingRecipe> getRecipeClass() {
		return WoodcuttingRecipe.class;
	}

	public String getTitle() {
		return this.localizedName;
	}

	public IDrawable getBackground() {
		return this.background;
	}

	public IDrawable getIcon() {
		return this.icon;
	}

	public void setIngredients(WoodcuttingRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	public void setRecipe(IRecipeLayout recipeLayout, WoodcuttingRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 0, 8);
		guiItemStacks.init(1, false, 60, 8);
		guiItemStacks.set(ingredients);
	}
}