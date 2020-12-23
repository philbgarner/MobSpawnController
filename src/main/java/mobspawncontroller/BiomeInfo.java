package mobspawncontroller;

import org.bukkit.Material;
import org.bukkit.block.Biome;

public class BiomeInfo {

    public static boolean isOcean(Biome biome) {
        if (biome.equals(Biome.OCEAN)
                || biome.equals(Biome.OCEAN)
                || biome.equals(Biome.COLD_OCEAN)
                || biome.equals(Biome.DEEP_COLD_OCEAN)
                || biome.equals(Biome.FROZEN_OCEAN)
                || biome.equals(Biome.DEEP_FROZEN_OCEAN)
                || biome.equals(Biome.DEEP_WARM_OCEAN)
                || biome.equals(Biome.DEEP_LUKEWARM_OCEAN)
                || biome.equals(Biome.LUKEWARM_OCEAN)
                || biome.equals(Biome.WARM_OCEAN)
                || biome.equals(Biome.DEEP_OCEAN)
        ) {
            return true;
        }
        return false;
    }

    public static boolean isCold(Biome biome) {
        if (biome.equals(Biome.COLD_OCEAN) || biome.equals(Biome.DEEP_COLD_OCEAN))
        {
            return true;
        }
        return false;
    }

    public static boolean isWarm(Biome biome) {
        if (biome.equals(Biome.WARM_OCEAN) || biome.equals(Biome.DEEP_WARM_OCEAN)) {
            return true;
        }
        return false;
    }

    public static boolean isPlains(Biome biome) {

        if (biome.equals(Biome.PLAINS) || biome.equals(Biome.SUNFLOWER_PLAINS))
        {
            return true;
        }
        return false;
    }

    public static boolean isLukewarm(Biome biome) {
        if (biome.equals(Biome.LUKEWARM_OCEAN) || biome.equals(Biome.DEEP_LUKEWARM_OCEAN)) {
            return true;
        }
        return false;
    }

    public static boolean isFrozen(Biome biome) {
        if (biome.equals(Biome.FROZEN_OCEAN) || biome.equals(Biome.FROZEN_RIVER) || biome.equals(Biome.DEEP_FROZEN_OCEAN)) {
            return true;
        }
        return false;
    }

    public static boolean isTaiga(Biome biome) {
        if (biome.equals(Biome.TAIGA_MOUNTAINS) || biome.equals(Biome.TAIGA) || biome.equals(Biome.TAIGA_HILLS)
                || biome.equals(Biome.SNOWY_TAIGA_MOUNTAINS) || biome.equals(Biome.SNOWY_TAIGA)
                || biome.equals(Biome.GIANT_SPRUCE_TAIGA) || biome.equals(Biome.GIANT_TREE_TAIGA)
                || biome.equals(Biome.GIANT_TREE_TAIGA_HILLS) || biome.equals(Biome.SNOWY_TAIGA_HILLS)
        )
        {
            return true;
        }
        return false;
    }

    public static boolean isSnowy(Biome biome) {
        if (isFrozen(biome)
                || biome.equals(Biome.SNOWY_TAIGA_HILLS) || biome.equals(Biome.SNOWY_TAIGA) || biome.equals(Biome.SNOWY_TAIGA_MOUNTAINS)
            || biome.equals(Biome.SNOWY_MOUNTAINS) || biome.equals(Biome.SNOWY_BEACH) || biome.equals(Biome.SNOWY_TUNDRA))
        {
            return true;
        }
        return false;
    }

    public static boolean isMountains(Biome biome) {
        if (biome.equals(Biome.MOUNTAINS)
                || biome.equals(Biome.GRAVELLY_MOUNTAINS)
                || biome.equals(Biome.SNOWY_MOUNTAINS)
                || biome.equals(Biome.MOUNTAIN_EDGE)
                || biome.equals(Biome.MODIFIED_GRAVELLY_MOUNTAINS)
                || biome.equals(Biome.SNOWY_TAIGA_MOUNTAINS)
                || biome.equals(Biome.TAIGA_MOUNTAINS)
                || biome.equals(Biome.WOODED_MOUNTAINS))
        {
            return true;
        }
        return false;
    }

    public static boolean isRiver(Biome biome) {
        if (biome.equals(Biome.RIVER) || biome.equals(Biome.FROZEN_RIVER))
        {
            return true;
        }
        return false;
    }

    public static boolean isLeaves(Material material) {
        if (material.equals(Material.ACACIA_LEAVES)
                || material.equals(Material.BIRCH_LEAVES)
                || material.equals(Material.DARK_OAK_LEAVES)
                || material.equals(Material.JUNGLE_LEAVES)
                || material.equals(Material.OAK_LEAVES)
                || material.equals(Material.SPRUCE_LEAVES))
        {
            return true;
        }
        return false;
    }

    public static boolean isForest(Biome biome) {
        if (biome.equals(Biome.FOREST) || biome.equals(Biome.FLOWER_FOREST) || biome.equals(Biome.BIRCH_FOREST)
            || biome.equals(Biome.BIRCH_FOREST_HILLS) || biome.equals(Biome.CRIMSON_FOREST) || biome.equals(Biome.DARK_FOREST)
            || biome.equals(Biome.DARK_FOREST_HILLS) || biome.equals(Biome.TALL_BIRCH_FOREST) || biome.equals(Biome.TALL_BIRCH_HILLS)
            || biome.equals(Biome.GIANT_SPRUCE_TAIGA) || biome.equals(Biome.GIANT_SPRUCE_TAIGA_HILLS) || biome.equals(Biome.GIANT_TREE_TAIGA_HILLS)
            || biome.equals(Biome.GIANT_TREE_TAIGA) || isJungle(biome))
        {
            return true;
        }
        return false;
    }

    public static boolean isSavannah(Biome biome) {
        if (biome.equals(Biome.SAVANNA) || biome.equals(Biome.SAVANNA_PLATEAU) || biome.equals(Biome.SHATTERED_SAVANNA) || biome.equals(Biome.SHATTERED_SAVANNA_PLATEAU))
        {
            return true;
        }
        return false;
    }

    public static boolean isJungle(Biome biome) {
        if (biome.equals(Biome.JUNGLE) || biome.equals(Biome.JUNGLE_HILLS) || biome.equals(Biome.BAMBOO_JUNGLE)
                || biome.equals(Biome.BAMBOO_JUNGLE_HILLS))
        {
            return true;
        }
        return false;
    }

    public static boolean isBeach(Biome biome) {
        if (biome.equals(Biome.BEACH) || biome.equals(Biome.SNOWY_BEACH)) {
            return true;
        }
        return false;
    }

    public static boolean isDesert(Biome biome) {
        if (biome.equals(Biome.DESERT) || biome.equals(Biome.DESERT_HILLS) || biome.equals(Biome.DESERT_LAKES)) {
            return true;
        }
        return false;
    }

    public static boolean isLog(Material log) {
        if (log.equals(Material.ACACIA_LOG) || log.equals(Material.BIRCH_LOG) || log.equals(Material.DARK_OAK_LOG)
                || log.equals(Material.JUNGLE_LOG) || log.equals(Material.OAK_LOG) || log.equals(Material.SPRUCE_LOG)) {
            return true;
        }
        return false;
    }

    public static boolean isGround(Material material) {
        if (material.equals(Material.GRASS_BLOCK)
                || material.equals(Material.SAND)
                || material.equals(Material.ANDESITE)
                || material.equals(Material.GRANITE)
                || material.equals(Material.STONE)
                || material.equals(Material.CLAY)
                || material.equals(Material.BASALT)
                || material.equals(Material.OBSIDIAN)
                || material.equals(Material.TERRACOTTA)
                || material.equals(Material.GRAVEL))
        {
            return true;
        }
        return false;
    }

}
