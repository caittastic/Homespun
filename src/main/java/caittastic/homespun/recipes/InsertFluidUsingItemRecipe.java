package caittastic.homespun.recipes;

import caittastic.homespun.Homespun;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class InsertFluidUsingItemRecipe implements Recipe<SimpleContainerWithTank>{
  private final ResourceLocation id;
  private final ItemStack emptyItem;
  private final FluidStack fluidStack;
  private final ItemStack filledItem;

  public InsertFluidUsingItemRecipe(ResourceLocation id, ItemStack inputItem, FluidStack inputFluid, ItemStack outputItem){
    this.id = id;
    this.emptyItem = inputItem;
    this.fluidStack = inputFluid;
    this.filledItem = outputItem;
  }

  @Override
  public boolean matches(SimpleContainerWithTank container, Level level){
    FluidTank tank = container.getTank();
    ItemStack stackInHand = container.getStack();

    if(level.isClientSide || stackInHand.isEmpty())
      return false;

    return stackInHand.getItem() == this.filledItem.getItem() &&
            (tank.getFluid().isEmpty() || tank.getFluid().getFluid() == this.fluidStack.getFluid()) &&
            (tank.getCapacity() - tank.getFluidAmount()) >= this.fluidStack.getAmount();
  }

  @Override
  public ItemStack assemble(SimpleContainerWithTank pContainer){
    return filledItem;
  }

  @Override
  public boolean canCraftInDimensions(int pWidth, int pHeight){
    return true;
  }

  @Override
  public ItemStack getResultItem(){
    return filledItem.copy();
  }

  @Override
  public ResourceLocation getId(){
    return id;
  }

  @Override
  public RecipeSerializer<?> getSerializer(){
    return Serializer.INSTANCE;
  }

  @Override
  public RecipeType<?> getType(){
    return Type.INSTANCE;
  }

  public ItemStack emptyItem(){
    return this.emptyItem;
  }

  public FluidStack fluid(){
    return fluidStack;
  }

  public ItemStack filledItem(){
    return filledItem;
  }


  public static class Type implements RecipeType<InsertFluidUsingItemRecipe>{
    public static final Type INSTANCE = new Type();
    public static final String ID = "fill_using_item";

    private Type(){}
  }

  public static class Serializer implements RecipeSerializer<InsertFluidUsingItemRecipe>{
    public static final Serializer INSTANCE = new Serializer();
    public static final ResourceLocation ID = new ResourceLocation(Homespun.MOD_ID, "fill_using_item");

    @Override
    public InsertFluidUsingItemRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe){
      ItemStack emptyItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "empty_item"));
      ItemStack filledItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "filled_item"));
      FluidStack fluidStack = fluidStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "fluid_stack"));

      return new InsertFluidUsingItemRecipe(pRecipeId, emptyItem, fluidStack, filledItem);
    }

    private FluidStack fluidStackFromJson(JsonObject json){
      String fluidName = GsonHelper.getAsString(json, "fluid");
      ResourceLocation fluidKey = new ResourceLocation(fluidName);

      if(!ForgeRegistries.FLUIDS.containsKey(fluidKey))
        throw new JsonSyntaxException("Uh oh! Unknown fluid '" + fluidName + "'!");
      Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidKey);

      int amount = GsonHelper.getAsInt(json, "amount", 250);

      return new FluidStack(fluid, amount);
    }

    @Override
    public @Nullable InsertFluidUsingItemRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf){
      ItemStack emptyItem = buf.readItem();
      FluidStack fluidStack = buf.readFluidStack();
      ItemStack filledItem = buf.readItem();

      return new InsertFluidUsingItemRecipe(id, emptyItem, fluidStack, filledItem);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, InsertFluidUsingItemRecipe recipe){
      buf.writeItemStack(recipe.emptyItem, false);
      buf.writeFluidStack(recipe.fluidStack);
      buf.writeItemStack(recipe.filledItem, false);
    }
  }
}
