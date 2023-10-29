package caittastic.homespun;

import caittastic.homespun.block.ModBlocks;
import caittastic.homespun.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs{
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Homespun.MOD_ID);
  public static final RegistryObject<CreativeModeTab> AGRICULTURE = registerTab("agriculture_tab", new ItemStack(ModItems.IRONBERRIES.get()), (pParameters, pOutput) -> {
    pOutput.accept(ModBlocks.IRONWOOD_SAPLING.get());
    pOutput.accept(ModBlocks.IRONWOOD_LEAVES.get());
    pOutput.accept(ModBlocks.IRONWOOD_LOG.get());

    pOutput.accept(ModBlocks.OLIVE_SAPLING.get());
    pOutput.accept(ModBlocks.OLIVE_LEAVES.get());
    pOutput.accept(ModBlocks.OLIVE_LOG.get());
  });
  public static final RegistryObject<CreativeModeTab> DECORATION = registerTab("decoration_tab", new ItemStack(ModBlocks.IRONWOOD_PLANKS.get()), (pParameters, pOutput) -> {
    pOutput.accept(ModBlocks.STRIPPED_IRONWOOD_LOG.get());
    pOutput.accept(ModBlocks.IRONWOOD_WOOD.get());
    pOutput.accept(ModBlocks.STRIPPED_IRONWOOD_WOOD.get());
    pOutput.accept(ModBlocks.IRONWOOD_PLANKS.get());
    pOutput.accept(ModBlocks.IRONWOOD_STAIRS.get());
    pOutput.accept(ModBlocks.IRONWOOD_SLAB.get());
    pOutput.accept(ModBlocks.IRONWOOD_FENCE.get());
    pOutput.accept(ModBlocks.IRONWOOD_FENCE_GATE.get());
    pOutput.accept(ModBlocks.IRONWOOD_DOOR.get());
    pOutput.accept(ModBlocks.IRONWOOD_TRAPDOOR.get());
    pOutput.accept(ModBlocks.IRONWOOD_BUTTON.get());
    pOutput.accept(ModBlocks.IRONWOOD_PRESSURE_PLATE.get());
    pOutput.accept(ModBlocks.IRONWOOD_MOSAIC.get());
    pOutput.accept(ModBlocks.IRONWOOD_MOSAIC_STAIRS.get());
    pOutput.accept(ModBlocks.IRONWOOD_MOSAIC_SLAB.get());

    pOutput.accept(ModBlocks.STRIPPED_OLIVE_LOG.get());
    pOutput.accept(ModBlocks.OLIVE_WOOD.get());
    pOutput.accept(ModBlocks.STRIPPED_OLIVE_WOOD.get());
    pOutput.accept(ModBlocks.OLIVE_PLANKS.get());
    pOutput.accept(ModBlocks.OLIVE_STAIRS.get());
    pOutput.accept(ModBlocks.OLIVE_SLAB.get());
    pOutput.accept(ModBlocks.OLIVE_FENCE.get());
    pOutput.accept(ModBlocks.OLIVE_FENCE_GATE.get());
    pOutput.accept(ModBlocks.OLIVE_DOOR.get());
    pOutput.accept(ModBlocks.OLIVE_TRAPDOOR.get());
    pOutput.accept(ModBlocks.OLIVE_BUTTON.get());
    pOutput.accept(ModBlocks.OLIVE_PRESSURE_PLATE.get());
    pOutput.accept(ModBlocks.OLIVE_MOSAIC.get());
    pOutput.accept(ModBlocks.OLIVE_MOSAIC_STAIRS.get());
    pOutput.accept(ModBlocks.OLIVE_MOSAIC_SLAB.get());

    pOutput.accept(ModBlocks.SMOOTH_STONE_PILLAR.get());
    pOutput.accept(ModBlocks.CALCITE_BRICKS.get());
    pOutput.accept(ModBlocks.CALCITE_BRICK_STAIRS.get());
    pOutput.accept(ModBlocks.CALCITE_BRICK_SLAB.get());
    pOutput.accept(ModBlocks.CALCITE_BRICK_WALL.get());
    pOutput.accept(ModBlocks.TUFF_TILES.get());
    pOutput.accept(ModBlocks.TUFF_TILE_STAIRS.get());
    pOutput.accept(ModBlocks.TUFF_TILE_SLAB.get());
    pOutput.accept(ModBlocks.TUFF_TILE_WALL.get());

    pOutput.accept(ModBlocks.GOLD_CHAIN.get());
    pOutput.accept(ModBlocks.COPPER_CHAIN.get());
    pOutput.accept(ModBlocks.IRON_POST.get());
    pOutput.accept(ModBlocks.WOODEN_POST.get());
    pOutput.accept(ModBlocks.CAST_IRON_BLOCK.get());
    pOutput.accept(ModBlocks.CAST_IRON_TILES.get());
    pOutput.accept(ModBlocks.CAST_IRON_TILE_WALL.get());
    pOutput.accept(ModBlocks.CAST_IRON_TILE_STAIRS.get());
    pOutput.accept(ModBlocks.CAST_IRON_TILE_SLAB.get());

    pOutput.accept(ModBlocks.CERAMIC_VESSEL.get());
    for(String[] patternKeyword: ModBlocks.vessel_patterns){
      pOutput.accept(ModBlocks.VESSEL_MAP.get(patternKeyword[0]).get());
    }
  });
  public static final RegistryObject<CreativeModeTab> INDUSTRY = registerTab("production_tab", new ItemStack(ModItems.TINY_IRON_DUST.get()), (pParameters, pOutput) -> {
    pOutput.accept(ModBlocks.CRUSHING_TUB.get());
    pOutput.accept(ModBlocks.EVAPORATING_BASIN.get());
    pOutput.accept(ModBlocks.FLUID_STORAGE.get());
  });

  private static RegistryObject<CreativeModeTab> registerTab(String name, ItemStack icon, CreativeModeTab.DisplayItemsGenerator items){
    return CREATIVE_MODE_TABS.register(name, () -> CreativeModeTab.builder().icon(() -> icon).title(Component.translatable("creativetab" + name)).displayItems(items).build());
  }
}
