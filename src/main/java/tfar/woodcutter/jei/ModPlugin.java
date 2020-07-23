package tfar.woodcutter.jei;

import mezz.jei.Internal;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.gui.textures.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import tfar.woodcutter.WoodCutter;
import tfar.woodcutter.WoodcuttingRecipe;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class ModPlugin implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(WoodCutter.MODID,WoodCutter.MODID);
	}

	public void registerCategories(IRecipeCategoryRegistration registration) {
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		IModIdHelper modIdHelper = jeiHelpers.getModIdHelper();

		registration.addRecipeCategories(new WoodCutterRecipeCategory(guiHelper,modIdHelper));
	}

	public void registerRecipes(IRecipeRegistration registration) {

		List<WoodcuttingRecipe> recipes =
						Minecraft.getInstance().world.getRecipeManager().getRecipes().stream()
										.filter(WoodcuttingRecipe.class::isInstance)
										.map(WoodcuttingRecipe.class::cast)
										.collect(Collectors.toList());

		registration.addRecipes(recipes, WoodCutter.ID);
	}
}
