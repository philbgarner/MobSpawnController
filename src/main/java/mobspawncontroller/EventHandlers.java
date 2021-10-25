package mobspawncontroller;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.concurrent.ThreadLocalRandom;

import static mobspawncontroller.BiomeInfo.isGround;
import static mobspawncontroller.BiomeInfo.isLeaves;

public class EventHandlers implements Listener {

    private Block getHighestGroundBlockAt(World world, int x, int z) {
        Block highest = world.getHighestBlockAt(x, z);
        if (isLeaves(highest.getType()))
        {
            while (!isGround(highest.getType()) && highest.getLocation().getY() > 0) {
                highest = highest.getRelative(0, -1, 0);
            }
        }
        return highest;
    }

    @EventHandler
    public void onChunkLoad(final ChunkLoadEvent event) {

        if (ThreadLocalRandom.current().nextFloat() <= MobSpawnController.ChunkChanceAttemptSpawn) {
            Bukkit.getScheduler().runTaskLater(MobSpawnController.getInstance(),
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (event.getChunk().getEntities().length == MobSpawnController.ChunkEntitySpawnThreshold
                                    // && !MedievalFactions.getInstance().
                                ) {
                                    int maxAttempts = MobSpawnController.SpawnLocationAttemptLimit;
                                    int attempt = 0;
                                    while (attempt < maxAttempts) {
                                        int randX = ThreadLocalRandom.current().nextInt(event.getChunk().getX() * 16, event.getChunk().getX() * 16 + 15);
                                        int randZ = ThreadLocalRandom.current().nextInt(event.getChunk().getZ() * 16, event.getChunk().getZ() * 16 + 15);
                                        Block highest = getHighestGroundBlockAt(event.getWorld(), randX, randZ);
                                        attempt++;
                                        MobSpawnController.getInstance().SpawnMob(event, highest);
                                    }
                                }
                            } catch (Exception e) {

                            }
                        }
                    }, 40);
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {

        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.NATURAL))
                //|| event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.DEFAULT))
        {
            if (event.getEntityType().equals(EntityType.CREEPER) || event.getEntityType().equals(EntityType.BAT)
                    || event.getEntityType().equals(EntityType.BEE) || event.getEntityType().equals(EntityType.CAT)
                    || event.getEntityType().equals(EntityType.CHICKEN) || event.getEntityType().equals(EntityType.COD)
                    || event.getEntityType().equals(EntityType.COW) || event.getEntityType().equals(EntityType.DOLPHIN)
                    || event.getEntityType().equals(EntityType.DONKEY) || event.getEntityType().equals(EntityType.FOX)
                    || event.getEntityType().equals(EntityType.HORSE) || event.getEntityType().equals(EntityType.LLAMA)
                    || event.getEntityType().equals(EntityType.MULE) || event.getEntityType().equals(EntityType.OCELOT)
                    || event.getEntityType().equals(EntityType.PANDA) || event.getEntityType().equals(EntityType.PARROT)
                    || event.getEntityType().equals(EntityType.PIG) || event.getEntityType().equals(EntityType.POLAR_BEAR)
                    || event.getEntityType().equals(EntityType.PUFFERFISH) || event.getEntityType().equals(EntityType.RABBIT)
                    || event.getEntityType().equals(EntityType.SALMON) || event.getEntityType().equals(EntityType.SHEEP)
                    || event.getEntityType().equals(EntityType.SQUID) || event.getEntityType().equals(EntityType.TROPICAL_FISH)
                    || event.getEntityType().equals(EntityType.TURTLE) || event.getEntityType().equals(EntityType.WOLF))
            {
                event.setCancelled(true);
            }
        }

    }

}
