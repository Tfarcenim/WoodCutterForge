package tfar.woodcutter.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.registry.Registry;
import tfar.woodcutter.WoodCutter;

import java.util.function.Consumer;

public class WoodCutterRecipeProvider extends RecipeProvider {
	public WoodCutterRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		tag_to_multiple_items(ItemTags.OAK_LOGS,Blocks.OAK_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.SPRUCE_LOGS,Blocks.SPRUCE_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.BIRCH_LOGS,Blocks.BIRCH_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.JUNGLE_LOGS,Blocks.JUNGLE_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.ACACIA_LOGS,Blocks.ACACIA_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.DARK_OAK_LOGS,Blocks.DARK_OAK_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.CRIMSON_STEMS,Blocks.CRIMSON_PLANKS,6,consumer);
		tag_to_multiple_items(ItemTags.WARPED_STEMS,Blocks.WARPED_PLANKS,6,consumer);

		item_to_item(Blocks.OAK_LOG,Blocks.STRIPPED_OAK_LOG,consumer);
		item_to_item(Blocks.SPRUCE_LOG,Blocks.STRIPPED_SPRUCE_LOG,consumer);
		item_to_item(Blocks.BIRCH_LOG,Blocks.STRIPPED_BIRCH_LOG,consumer);
		item_to_item(Blocks.JUNGLE_LOG,Blocks.STRIPPED_JUNGLE_LOG,consumer);
		item_to_item(Blocks.ACACIA_LOG,Blocks.STRIPPED_ACACIA_LOG,consumer);
		item_to_item(Blocks.DARK_OAK_LOG,Blocks.STRIPPED_DARK_OAK_LOG,consumer);
		item_to_item(Blocks.CRIMSON_STEM,Blocks.STRIPPED_CRIMSON_STEM,consumer);
		item_to_item(Blocks.WARPED_STEM,Blocks.STRIPPED_WARPED_STEM,consumer);

		item_to_item(Blocks.OAK_LOG,Blocks.OAK_WOOD,consumer);
		item_to_item(Blocks.SPRUCE_LOG,Blocks.SPRUCE_WOOD,consumer);
		item_to_item(Blocks.BIRCH_LOG,Blocks.BIRCH_WOOD,consumer);
		item_to_item(Blocks.JUNGLE_LOG,Blocks.JUNGLE_WOOD,consumer);
		item_to_item(Blocks.ACACIA_LOG,Blocks.ACACIA_WOOD,consumer);
		item_to_item(Blocks.DARK_OAK_LOG,Blocks.DARK_OAK_WOOD,consumer);
		item_to_item(Blocks.CRIMSON_STEM,Blocks.CRIMSON_HYPHAE,consumer);
		item_to_item(Blocks.WARPED_STEM,Blocks.WARPED_HYPHAE,consumer);

		item_to_item(Blocks.STRIPPED_OAK_LOG,Blocks.STRIPPED_OAK_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_SPRUCE_LOG,Blocks.STRIPPED_SPRUCE_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_BIRCH_LOG,Blocks.STRIPPED_BIRCH_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_JUNGLE_LOG,Blocks.STRIPPED_JUNGLE_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_ACACIA_LOG,Blocks.STRIPPED_ACACIA_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_DARK_OAK_LOG,Blocks.STRIPPED_DARK_OAK_WOOD,consumer);
		item_to_item(Blocks.STRIPPED_CRIMSON_STEM,Blocks.STRIPPED_CRIMSON_HYPHAE,consumer);
		item_to_item(Blocks.STRIPPED_WARPED_STEM,Blocks.STRIPPED_WARPED_HYPHAE,consumer);

		tag_to_multiple_items(ItemTags.PLANKS, Items.STICK,4,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_STAIRS,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_STAIRS,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_STAIRS,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_STAIRS,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_STAIRS,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_STAIRS,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_STAIRS,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_STAIRS,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_BUTTON,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_BUTTON,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_BUTTON,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_BUTTON,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_BUTTON,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_BUTTON,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_BUTTON,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_BUTTON,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_FENCE_GATE,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_FENCE_GATE,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_FENCE_GATE,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_FENCE_GATE,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_FENCE_GATE,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_FENCE_GATE,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_FENCE_GATE,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_FENCE_GATE,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_SIGN,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_SIGN,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_SIGN,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_SIGN,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_SIGN,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_SIGN,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_SIGN,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_SIGN,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_DOOR,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_DOOR,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_DOOR,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_DOOR,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_DOOR,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_DOOR,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_DOOR,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_DOOR,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_TRAPDOOR,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_TRAPDOOR,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_TRAPDOOR,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_TRAPDOOR,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_TRAPDOOR,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_TRAPDOOR,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_TRAPDOOR,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_TRAPDOOR,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_PRESSURE_PLATE,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_PRESSURE_PLATE,consumer);

		item_to_item(Blocks.OAK_PLANKS,Blocks.OAK_FENCE,consumer);
		item_to_item(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_FENCE,consumer);
		item_to_item(Blocks.BIRCH_PLANKS,Blocks.BIRCH_FENCE,consumer);
		item_to_item(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_FENCE,consumer);
		item_to_item(Blocks.ACACIA_PLANKS,Blocks.ACACIA_FENCE,consumer);
		item_to_item(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_FENCE,consumer);
		item_to_item(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_FENCE,consumer);
		item_to_item(Blocks.WARPED_PLANKS,Blocks.WARPED_FENCE,consumer);

		item_to_multiple_items(Blocks.OAK_PLANKS,Blocks.OAK_SLAB,2,consumer);
		item_to_multiple_items(Blocks.SPRUCE_PLANKS,Blocks.SPRUCE_SLAB,2,consumer);
		item_to_multiple_items(Blocks.BIRCH_PLANKS,Blocks.BIRCH_SLAB,2,consumer);
		item_to_multiple_items(Blocks.JUNGLE_PLANKS,Blocks.JUNGLE_SLAB,2,consumer);
		item_to_multiple_items(Blocks.ACACIA_PLANKS,Blocks.ACACIA_SLAB,2,consumer);
		item_to_multiple_items(Blocks.DARK_OAK_PLANKS,Blocks.DARK_OAK_SLAB,2,consumer);
		item_to_multiple_items(Blocks.CRIMSON_PLANKS,Blocks.CRIMSON_SLAB,2,consumer);
		item_to_multiple_items(Blocks.WARPED_PLANKS,Blocks.WARPED_SLAB,2,consumer);
	}

	public static void item_to_item(IItemProvider input, IItemProvider output, Consumer<IFinishedRecipe> consumer) {
		String s = Registry.ITEM.getKey(output.asItem()).getPath();
		String s1 = Registry.ITEM.getKey(input.asItem()).getPath();
		WoodcuttingRecipeBuilder.woodcuttingRecipe(Ingredient.fromItems(input), output).
						addCriterion("has_"+s, hasItem(input)).build(consumer,WoodCutter.MODID+":"+s+"_from_"+s1);
	}

	public static void helper1(ITag<Item> input, IItemProvider output, Consumer<IFinishedRecipe> consumer) {
		String s = Registry.ITEM.getKey(output.asItem()).getPath();
		String s1 = Registry.ITEM.getKey(output.asItem()).getPath();
		WoodcuttingRecipeBuilder.woodcuttingRecipe(Ingredient.fromTag(input), output).
						addCriterion("has_"+s, hasItem(input)).build(consumer, s+"_from_"+s1);
	}

	public static void tag_to_multiple_items(ITag.INamedTag<Item> input, IItemProvider output, int count, Consumer<IFinishedRecipe> consumer) {
		String s = Registry.ITEM.getKey(output.asItem()).getPath();
		String s1 = input.getName().getPath();
		WoodcuttingRecipeBuilder.woodcuttingRecipe(Ingredient.fromTag(input), output,count).
						addCriterion("has_"+s, hasItem(input)).build(consumer, WoodCutter.MODID+":"+s+"_from_"+s1+"_tag");
	}

	public static void item_to_multiple_items(IItemProvider input, IItemProvider output, int count, Consumer<IFinishedRecipe> consumer) {
		String s = Registry.ITEM.getKey(output.asItem()).getPath();
		String s1 = Registry.ITEM.getKey(input.asItem()).getPath();
		WoodcuttingRecipeBuilder.woodcuttingRecipe(Ingredient.fromItems(input), output,count).
						addCriterion("has_"+s, hasItem(input)).build(consumer, WoodCutter.MODID+":"+s+"_from_"+s1);
	}
}
