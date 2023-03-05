package caittastic.homespun.compat;

import caittastic.homespun.Homespun;
import caittastic.homespun.block.ModBlocks;
import caittastic.homespun.blockentity.CrushingTubBE;
import caittastic.homespun.recipes.CrushingTubRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CrushingTubRecipeCategory implements IRecipeCategory<CrushingTubRecipe>{
  public static final ResourceLocation UID = new ResourceLocation(Homespun.MOD_ID, "tub_crushing");
  public static final ResourceLocation TEXTURE = new ResourceLocation(Homespun.MOD_ID, "textures/gui/crushing_tub_jei.png");

  public final IDrawable background;
  public final IDrawable icon;

  public CrushingTubRecipeCategory(IGuiHelper helper){
    this.background = helper.createDrawable(TEXTURE, 0, 0, 108, 68);
    this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHING_TUB.get()));
  }

  @Override
  public RecipeType<CrushingTubRecipe> getRecipeType(){
    return JEIHomespunPlugin.CRUSHING_TUB_TYPE;
  }

  @Override
  public Component getTitle(){
    return Component.translatable("jei.homespun.crushing_tub_recipe");
  }

  @Override
  public IDrawable getBackground(){
    return this.background;
  }

  @Override
  public IDrawable getIcon(){
    return this.icon;
  }

  @Override
  public void setRecipe(IRecipeLayoutBuilder builder, CrushingTubRecipe recipe, IFocusGroup focuses){
    builder.addSlot(RecipeIngredientRole.INPUT, 21, 29).addItemStack(recipe.getInputItemStack());
    builder.addSlot(RecipeIngredientRole.OUTPUT, 71, 47).addItemStack(recipe.getResultItem());
    if(!recipe.getResultFluidStack().isEmpty()){
      float scale = 34f /CrushingTubBE.getCapacity();
      int scaledAmount = (int)(recipe.getResultFluidStack().getAmount() * scale);
      builder.addSlot(RecipeIngredientRole.INPUT, 71, 5+(34-scaledAmount))
              .addFluidStack(recipe.getResultFluidStack().getFluid(), recipe.getResultFluidStack().getAmount())
              .setFluidRenderer(recipe.getResultFluidStack().getAmount(), true, 16, scaledAmount);
    }
    //todo output fluid rendering
  }
}
