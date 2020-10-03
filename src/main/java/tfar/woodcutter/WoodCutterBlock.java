package tfar.woodcutter;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
