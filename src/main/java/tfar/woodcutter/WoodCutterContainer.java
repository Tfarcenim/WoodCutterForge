package tfar.woodcutter;
import com.google.common.collect.Lists;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class WoodCutterContainer extends Container {
	private final IWorldPosCallable context;
	private final IntReferenceHolder selectedRecipe;
	private final World world;
	private List<WoodcuttingRecipe> availableRecipes;
	private ItemStack inputStack;
	private long lastTakeTime;
	final Slot inputSlot;
	final Slot outputSlot;
	private Runnable contentsChangedListener;
	public final IInventory input = new Inventory(1) {
		public void markDirty() {
			super.markDirty();
			WoodCutterContainer.this.onCraftMatrixChanged(this);
			WoodCutterContainer.this.contentsChangedListener.run();
		}
	};

	private final CraftResultInventory output;

	public WoodCutterContainer(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, IWorldPosCallable.DUMMY);
	}

	public WoodCutterContainer(int syncId, PlayerInventory playerInventory, final IWorldPosCallable context) {
		super(WoodCutter.woodCutterContainer, syncId);
		this.selectedRecipe = IntReferenceHolder.single();
		this.availableRecipes = Lists.newArrayList();
		this.inputStack = ItemStack.EMPTY;
		this.contentsChangedListener = () -> {
		};
		this.output = new CraftResultInventory();
		this.context = context;
		this.world = playerInventory.player.world;
		this.inputSlot = this.addSlot(new Slot(this.input, 0, 20, 33));
		this.outputSlot = this.addSlot(new Slot(this.output, 1, 143, 33) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack) {
				ItemStack itemStack = WoodCutterContainer.this.inputSlot.decrStackSize(1);
				if (!itemStack.isEmpty()) {
					WoodCutterContainer.this.populateResult();
				}

				stack.getItem().onCreated(stack, player.world, player);
				context.consume((world, blockPos) -> {
					long l = world.getGameTime();
					if (WoodCutterContainer.this.lastTakeTime != l) {
						world.playSound(null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
						WoodCutterContainer.this.lastTakeTime = l;
					}
				});
				return super.onTake(player, stack);
			}
		});

		int k;
		for(k = 0; k < 3; ++k) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
			}
		}

		for(k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
		}

		this.trackInt(this.selectedRecipe);
	}

	public int getSelectedRecipe() {
		return this.selectedRecipe.get();
	}

	public List<WoodcuttingRecipe> getAvailableRecipes() {
		return this.availableRecipes;
	}

	public int getAvailableRecipeCount() {
		return this.availableRecipes.size();
	}

	public boolean canCraft() {
		return this.inputSlot.getHasStack() && !this.availableRecipes.isEmpty();
	}

	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(this.context, player, WoodCutter.woodcutter);
	}

	public boolean enchantItem(PlayerEntity player, int id) {
		if (this.isValidIndex(id)) {
			this.selectedRecipe.set(id);
			this.populateResult();
		}

		return true;
	}

	private boolean isValidIndex(int i) {
		return i >= 0 && i < this.availableRecipes.size();
	}

	public void onCraftMatrixChanged(IInventory inventory) {
		ItemStack itemStack = this.inputSlot.getStack();
		if (itemStack.getItem() != this.inputStack.getItem()) {
			this.inputStack = itemStack.copy();
			this.updateInput(inventory, itemStack);
		}
	}

	private void updateInput(IInventory input, ItemStack stack) {
		this.availableRecipes.clear();
		this.selectedRecipe.set(-1);
		this.outputSlot.putStack(ItemStack.EMPTY);
		if (!stack.isEmpty()) {
			this.availableRecipes = this.world.getRecipeManager().getRecipes(WoodcuttingRecipe.TYPE, input, this.world);
		}

	}

	private void populateResult() {
		if (!this.availableRecipes.isEmpty() && this.isValidIndex(this.selectedRecipe.get())) {
			WoodcuttingRecipe woodcuttingRecipe = this.availableRecipes.get(this.selectedRecipe.get());
			this.outputSlot.putStack(woodcuttingRecipe.getCraftingResult(this.input));
		} else {
			this.outputSlot.putStack(ItemStack.EMPTY);
		}

		this.detectAndSendChanges();
	}

	public ContainerType<?> getType() {
		return WoodCutter.woodCutterContainer;
	}

	public void setContentsChangedListener(Runnable runnable) {
		this.contentsChangedListener = runnable;
	}

	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.output && super.canMergeSlot(stack, slot);
	}

	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemStack2 = slot.getStack();
			Item item = itemStack2.getItem();
			itemStack = itemStack2.copy();
			if (index == 1) {
				item.onCreated(itemStack2, player.world, player);
				if (!this.mergeItemStack(itemStack2, 2, 38, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemStack2, itemStack);
			} else if (index == 0) {
				if (!this.mergeItemStack(itemStack2, 2, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (this.world.getRecipeManager().getRecipe(WoodcuttingRecipe.TYPE, new Inventory(itemStack2), this.world).isPresent()) {
				if (!this.mergeItemStack(itemStack2, 0, 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 2 && index < 29) {
				if (!this.mergeItemStack(itemStack2, 29, 38, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 29 && index < 38 && !this.mergeItemStack(itemStack2, 2, 29, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}

			slot.onSlotChanged();
			if (itemStack2.getCount() == itemStack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemStack2);
			this.detectAndSendChanges();
		}

		return itemStack;
	}

	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);
		this.output.removeStackFromSlot(1);
		this.context.consume((world, blockPos) -> {
			this.clearContainer(player, player.world, this.input);
		});
	}
}
