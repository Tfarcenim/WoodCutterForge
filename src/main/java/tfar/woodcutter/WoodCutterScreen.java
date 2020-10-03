package tfar.woodcutter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class WoodCutterScreen extends ContainerScreen<WoodCutterContainer> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/stonecutter.png");
	private float scrollAmount;
	private boolean mouseClicked;
	private int scrollOffset;
	private boolean canCraft;

	public WoodCutterScreen(WoodCutterContainer handler, PlayerInventory inventory, ITextComponent title) {
		super(handler, inventory, title);
		handler.setContentsChangedListener(this::onInventoryChange);
		--this.titleY;
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		super.render(matrices, mouseX, mouseY, delta);
		this.renderHoveredTooltip(matrices, mouseX, mouseY);
	}

	protected void drawGuiContainerBackgroundLayer(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		this.renderBackground(matrices);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(matrices, i, j, 0, 0, this.xSize, this.ySize);
		int k = (int)(41.0F * this.scrollAmount);
		this.blit(matrices, i + 119, j + 15 + k, 176 + (this.shouldScroll() ? 0 : 12), 0, 12, 15);
		int l = this.guiLeft + 52;
		int m = this.guiTop + 14;
		int n = this.scrollOffset + 12;
		this.renderRecipeBackground(matrices, mouseX, mouseY, l, m, n);
		this.renderRecipeIcons(l, m, n);
	}

	protected void drawGuiContainerForegroundLayer(MatrixStack matrices, int x, int y) {
		super.drawGuiContainerForegroundLayer(matrices, x, y);
		if (this.canCraft) {
			int i = this.guiLeft + 52;
			int j = this.guiTop + 14;
			int k = this.scrollOffset + 12;
			List<WoodcuttingRecipe> list = this.container.getAvailableRecipes();

			for(int l = this.scrollOffset; l < k && l < this.container.getAvailableRecipeCount(); ++l) {
				int m = l - this.scrollOffset;
				int n = i + m % 4 * 16;
				int o = j + m / 4 * 18 + 2;
				if (x >= n && x < n + 16 && y >= o && y < o + 18) {
					this.renderTooltip(matrices, list.get(l).getRecipeOutput(), x, y);
				}
			}
		}

	}

	private void renderRecipeBackground(MatrixStack matrixStack, int i, int j, int k, int l, int m) {
		for(int n = this.scrollOffset; n < m && n < this.container.getAvailableRecipeCount(); ++n) {
			int o = n - this.scrollOffset;
			int p = k + o % 4 * 16;
			int q = o / 4;
			int r = l + q * 18 + 2;
			int s = this.ySize;
			if (n == this.container.getSelectedRecipe()) {
				s += 18;
			} else if (i >= p && j >= r && i < p + 16 && j < r + 18) {
				s += 36;
			}

			this.blit(matrixStack, p, r - 1, 0, s, 16, 18);
		}

	}

	private void renderRecipeIcons(int x, int y, int scrollOffset) {
		List<WoodcuttingRecipe> list = this.container.getAvailableRecipes();

		for(int i = this.scrollOffset; i < scrollOffset && i < this.container.getAvailableRecipeCount(); ++i) {
			int j = i - this.scrollOffset;
			int k = x + j % 4 * 16;
			int l = j / 4;
			int m = y + l * 18 + 2;
			this.minecraft.getItemRenderer().renderItemAndEffectIntoGUI(list.get(i).getRecipeOutput(), k, m);
		}

	}

	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		this.mouseClicked = false;
		if (this.canCraft) {
			int i = this.guiLeft + 52;
			int j = this.guiTop + 14;
			int k = this.scrollOffset + 12;

			for(int l = this.scrollOffset; l < k; ++l) {
				int m = l - this.scrollOffset;
				double d = mouseX - (double)(i + m % 4 * 16);
				double e = mouseY - (double)(j + m / 4 * 18);
				if (d >= 0.0D && e >= 0.0D && d < 16.0D && e < 18.0D && this.container.enchantItem(this.minecraft.player, l)) {
					Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
					this.minecraft.playerController.sendEnchantPacket(this.container.windowId, l);
					return true;
				}
			}

			i = this.guiLeft + 119;
			j = this.guiTop + 9;
			if (mouseX >= i && mouseX < i + 12 && mouseY >= j && mouseY < j + 54) {
				this.mouseClicked = true;
			}
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (this.mouseClicked && this.shouldScroll()) {
			int i = this.guiTop + 14;
			int j = i + 54;
			this.scrollAmount = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
			this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
			this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5D) * 4;
			return true;
		} else {
			return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
		}
	}

	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if (this.shouldScroll()) {
			int i = this.getMaxScroll();
			this.scrollAmount = (float)(this.scrollAmount - amount / i);
			this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
			this.scrollOffset = (int)(this.scrollAmount * i + 0.5D) * 4;
		}

		return true;
	}

	private boolean shouldScroll() {
		return this.canCraft && this.container.getAvailableRecipeCount() > 12;
	}

	protected int getMaxScroll() {
		return (this.container.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
	}

	private void onInventoryChange() {
		this.canCraft = this.container.canCraft();
		if (!this.canCraft) {
			this.scrollAmount = 0.0F;
			this.scrollOffset = 0;
		}
	}
}