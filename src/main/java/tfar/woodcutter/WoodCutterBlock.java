package tfar.woodcutter;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.server.permission.context.WorldContext;

import javax.annotation.Nullable;

public class WoodCutterBlock extends StonecutterBlock {

	private static final TranslationTextComponent TITLE = new TranslationTextComponent("container.woodcutter");

	public WoodCutterBlock(AbstractBlock.Properties settings) {
		super(settings);
	}

	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			player.openContainer(state.getContainer(world, pos));
			//player.addStat(Stats.INTERACT_WITH_STONECUTTER);
			return ActionResultType.CONSUME;
		}
	}

	@Nullable
	public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedContainerProvider((i, playerInventory, playerEntity) -> new WoodCutterContainer(i, playerInventory, IWorldPosCallable.of(world, pos)), TITLE);
	}
}
