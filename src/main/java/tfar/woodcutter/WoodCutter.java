package tfar.woodcutter;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.stats.Stat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.CompositeFilter;
import org.apache.logging.log4j.core.filter.ThresholdFilter;

@Mod(WoodCutter.MODID)
public class WoodCutter {

	public static final String MODID = "woodcutter";

	public static final ResourceLocation ID = new ResourceLocation(MODID,"woodcutting");

	public static Block woodcutter;
	public static ContainerType<WoodCutterContainer> woodCutterContainer;
	public static IRecipeSerializer<WoodcuttingRecipe> WOODCUTTING;
	public static ResourceLocation INTERACTED_WITH_WOODCUTTER;

	public WoodCutter() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addGenericListener(Block.class,this::block);
		bus.addGenericListener(Item.class,this::item);
		bus.addGenericListener(ContainerType.class,this::menu);
		bus.addGenericListener(IRecipeSerializer.class,this::recipe);
		bus.addListener(this::onInitializeClient);
	}

	private void block(RegistryEvent.Register<Block> e) {
		register(woodcutter = new WoodCutterBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)),MODID,e.getRegistry());
	}

	private void item(RegistryEvent.Register<Item> e) {
		register(new BlockItem(woodcutter,new Item.Properties().group(ItemGroup.DECORATIONS)),MODID,e.getRegistry());
	}

	private void menu(RegistryEvent.Register<ContainerType<?>> e) {
		register(woodCutterContainer = new ContainerType<>(WoodCutterContainer::new),MODID,e.getRegistry());
	}

	private void recipe(RegistryEvent.Register<IRecipeSerializer<?>> e) {
		register(WOODCUTTING = new WoodcuttingRecipe.Serializer2<>(WoodcuttingRecipe::new),MODID,e.getRegistry());
	}

		private static <T extends IForgeRegistryEntry<T>> void register(T obj, String name, IForgeRegistry<T> registry) {
		registry.register(obj.setRegistryName(new ResourceLocation(MODID, name)));
	}

	private void onInitializeClient(FMLClientSetupEvent e) {
		ScreenManager.registerFactory(woodCutterContainer,WoodCutterScreen::new);

		RenderTypeLookup.setRenderLayer(woodcutter, RenderType.getCutout());

		Filter logfilter = ((Logger) ClientRecipeBook.field_241555_k_).getContext().getConfiguration().getFilter();
		Filter toRemove = null;
		if (logfilter instanceof CompositeFilter) {
			CompositeFilter compositeFilter = (CompositeFilter)logfilter;
			Filter[] filters  = compositeFilter.getFiltersArray();
			for (Filter filter : filters) {
				if (filter instanceof ThresholdFilter)
					toRemove = filter;
				}
			}
		if (toRemove != null) {
			((Logger) ClientRecipeBook.field_241555_k_).getContext().removeFilter(toRemove);
			System.out.println("Log spam from Recipe Book successfully neutralized");
			((Logger) ClientRecipeBook.field_241555_k_).getContext().addFilter(new ShutUpRecipeBookFilter());
		} else {
			System.out.println("Mission failed we'll get em next time");
		}
	}
}
