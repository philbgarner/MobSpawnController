package mobspawncontroller;

import dansplugins.factionsystem.externalapi.MedievalFactionsAPI;
import mobspawncontroller.integrators.MedievalFactionsIntegrator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

public class MobSpawnController extends JavaPlugin {

    public static int ChunkEntitySpawnThreshold = 0;
    public static int SpawnLocationAttemptLimit = 5;
    public static float ChunkChanceAttemptSpawn = 0.005f;
    private static MobSpawnController instance = null;

    public static MobSpawnController getInstance() {
        return instance;
    }

    public MedievalFactionsAPI get_MF_API() {
        return MedievalFactionsIntegrator.getInstance().getAPI();
    }

    @Override
    public void onEnable() {
        System.out.println(("--- Enabling CustomMobSpawning ------ "));

        instance = this;



        getServer().getPluginManager().registerEvents(new EventHandlers(), this);

        if (!MedievalFactionsIntegrator.getInstance().isMedievalFactionsPresent()) {
            System.out.println("Medieval Factions is not present. Mob Spawn Controller cannot run.");
        }
    }

    @Override
    public void onDisable() {
        System.out.println(("--- Disabling CustomMobSpawning --------"));
    }

    private boolean rollPassed(float chance) {
        return ThreadLocalRandom.current().nextFloat() <= chance;
    }

    public void SpawnGroup(Location location, Class spawnClass, int min, int max, int cmin, int cmax) {
        for (int i=0; i < min; i++) {
            location.getWorld().spawn(location, spawnClass);
        }
        int amt = ThreadLocalRandom.current().nextInt(0, max - min);
        for (int i=0; i < amt; i++) {
            location.getWorld().spawn(location, spawnClass);
        }

        int camt = ThreadLocalRandom.current().nextInt(0, cmax - cmin);
        for (int i=0; i < camt; i++) {
            Tameable t = (Tameable) location.getWorld().spawn(location, spawnClass);
            t.setBaby();
        }
    }

    public void SpawnMob(ChunkLoadEvent event, Block block) {
        block = block.getRelative(0, 1, 0);
        //Plains
        if (BiomeInfo.isPlains(block.getBiome())) {
            //Horse
            if (rollPassed(SpawnConditions.HorseChance))
            {
                SpawnGroup(block.getLocation(), Horse.class, SpawnConditions.HorseGroupMin, SpawnConditions.HorseGroupMax,
                                                            SpawnConditions.HorseChildMin, SpawnConditions.HorseChildMax);
            }
            //Donkey
            if (rollPassed(SpawnConditions.DonkeyChance))
            {
                SpawnGroup(block.getLocation(), Donkey.class, SpawnConditions.DonkeyGroupMin, SpawnConditions.DonkeyGroupMax,
                        SpawnConditions.DonkeyChildMin, SpawnConditions.DonkeyChildMax);
            }
            //Log, Bee
            if (rollPassed(SpawnConditions.BeeForestChance) && BiomeInfo.isLog(block.getType()))
            {
                SpawnGroup(block.getLocation(), Bee.class, SpawnConditions.BeeForestGroupMin, SpawnConditions.BeeForestGroupMax,
                        SpawnConditions.BeeForestChildMin, SpawnConditions.BeeForestChildMax);
            }
            if (rollPassed(SpawnConditions.BeePlainsChance) && BiomeInfo.isLog(block.getType()))
            {
                SpawnGroup(block.getLocation(), Bee.class, SpawnConditions.BeePlainsGroupMin, SpawnConditions.BeePlainsGroupMax,
                        SpawnConditions.BeePlainsChildMin, SpawnConditions.BeePlainsChildMax);
            }

        }
        //Forest
        if (BiomeInfo.isForest(block.getBiome()) || (BiomeInfo.isTaiga(block.getBiome()) && !BiomeInfo.isSnowy(block.getBiome()))) {
            //Forest	Red Fox
            if (rollPassed(SpawnConditions.RedFoxChance))
            {
                SpawnGroup(block.getLocation(), Fox.class, SpawnConditions.RedFoxGroupMin, SpawnConditions.RedFoxGroupMax,
                        SpawnConditions.RedFoxChildMin, SpawnConditions.RedFoxChildMax);
            }
            //Forest	Black Rabbits
            if (rollPassed(SpawnConditions.BlackRabbitsChance))
            {
                SpawnGroup(block.getLocation(), Rabbit.class, SpawnConditions.BlackRabbitsGroupMin, SpawnConditions.BlackRabbitsGroupMax,
                        SpawnConditions.BlackRabbitsChildMin, SpawnConditions.BlackRabbitsChildMax);
            }
            //Forest	Bat
            if (rollPassed(SpawnConditions.BatChance))
            {
                SpawnGroup(block.getLocation(), Bat.class, SpawnConditions.BatGroupMin, SpawnConditions.BatGroupMax, 0, 0);
            }
            //Forest	Wolf
            if (rollPassed(SpawnConditions.WolfForestChance))
            {
                SpawnGroup(block.getLocation(), Wolf.class, SpawnConditions.WolfForestGroupMin, SpawnConditions.WolfForestGroupMax,
                        SpawnConditions.WolfForestChildMin, SpawnConditions.WolfForestChildMax);
            }
        }

        //Savannah
        if (BiomeInfo.isSavannah(block.getBiome())) {
            //Savannah	Cat
            if (rollPassed(SpawnConditions.CatChance))
            {
                SpawnGroup(block.getLocation(), Cat.class, SpawnConditions.CatGroupMin, SpawnConditions.CatGroupMax,
                        SpawnConditions.CatChildMin, SpawnConditions.CatChildMax);
            }
            
            //Savannah	Llama
            if (rollPassed(SpawnConditions.LlamaChance))
            {
                SpawnGroup(block.getLocation(), Llama.class, SpawnConditions.LlamaGroupMin, SpawnConditions.LlamaGroupMax,
                        SpawnConditions.LlamaChildMin, SpawnConditions.LlamaChildMax);
            }            
            
            //Savannah	Donkey
            if (rollPassed(SpawnConditions.DonkeyChance))
            {
                SpawnGroup(block.getLocation(), Donkey.class, SpawnConditions.DonkeyGroupMin, SpawnConditions.DonkeyGroupMax,
                        SpawnConditions.DonkeyChildMin, SpawnConditions.DonkeyChildMax);
            }
        }

        //Mountains
        if (BiomeInfo.isMountains(block.getBiome())) {
            //Mountains	Llama
            if (rollPassed(SpawnConditions.LlamaChance))
            {
                SpawnGroup(block.getLocation(), Llama.class, SpawnConditions.LlamaGroupMin, SpawnConditions.LlamaGroupMax,
                        SpawnConditions.LlamaChildMin, SpawnConditions.LlamaChildMax);
            }       
            
            //Mountains	Sheep
            if (rollPassed(SpawnConditions.SheepChance))
            {
                SpawnGroup(block.getLocation(), Sheep.class, SpawnConditions.SheepGroupMin, SpawnConditions.SheepGroupMax,
                        SpawnConditions.SheepChildMin, SpawnConditions.SheepChildMax);
            }
        }

        //Beach
        if (BiomeInfo.isBeach(block.getBiome())) {
            //Turtle
            if (rollPassed(SpawnConditions.TurtleChance) && !block.getType().equals(Material.SNOW))
            {
                SpawnGroup(block.getLocation(), Turtle.class, SpawnConditions.TurtleGroupMin, SpawnConditions.TurtleGroupMax,
                        SpawnConditions.TurtleChildMin, SpawnConditions.TurtleChildMax);
            }
        }

        //Jungle
        if (BiomeInfo.isJungle(block.getBiome())) {
            //Jungle	Parrot
            if (rollPassed(SpawnConditions.ParrotChance))
            {
                SpawnGroup(block.getLocation(), Parrot.class, SpawnConditions.ParrotGroupMin, SpawnConditions.ParrotGroupMax,
                        SpawnConditions.ParrotChildMin, SpawnConditions.ParrotChildMax);
            }
            //Jungle	Panda
            if (rollPassed(SpawnConditions.PandaChance))
            {
                SpawnGroup(block.getLocation(), Panda.class, SpawnConditions.PandaGroupMin, SpawnConditions.PandaGroupMax,
                        SpawnConditions.PandaChildMin, SpawnConditions.PandaChildMax);
            }
            //Jungle	Ocelot
            if (rollPassed(SpawnConditions.OcelotChance))
            {
                SpawnGroup(block.getLocation(), Ocelot.class, SpawnConditions.OcelotGroupMin, SpawnConditions.OcelotGroupMax,
                        SpawnConditions.OcelotChildMin, SpawnConditions.OcelotChildMax);
            }
        }

        if (BiomeInfo.isDesert(block.getBiome())) {
            //Desert	Creamy Rabbits
            if (rollPassed(SpawnConditions.CreamyRabbitsChance))
            {
                SpawnGroup(block.getLocation(), Rabbit.class, SpawnConditions.CreamyRabbitsGroupMin, SpawnConditions.CreamyRabbitsGroupMax,
                        SpawnConditions.CreamyRabbitsChildMin, SpawnConditions.CreamyRabbitsChildMax);
            }
        }

        if (BiomeInfo.isSnowy(block.getBiome())) {
            //Taiga	Arctic Rabbit
            if (rollPassed(SpawnConditions.ArcticRabbitChance))
            {
                SpawnGroup(block.getLocation(), Rabbit.class, SpawnConditions.ArcticRabbitGroupMin, SpawnConditions.ArcticRabbitGroupMax,
                        SpawnConditions.ArcticRabbitChildMin, SpawnConditions.ArcticRabbitChildMax);
            }
            //Taiga	Polar Bear
            if (rollPassed(SpawnConditions.PolarBearChance))
            {
                SpawnGroup(block.getLocation(), PolarBear.class, SpawnConditions.PolarBearGroupMin, SpawnConditions.PolarBearGroupMax,
                        SpawnConditions.PolarBearChildMin, SpawnConditions.PolarBearChildMax);
            }
            //Taiga	Wolf
            if (rollPassed(SpawnConditions.WolfTaigaChance))
            {
                SpawnGroup(block.getLocation(), Wolf.class, SpawnConditions.WolfTaigaGroupMin, SpawnConditions.WolfTaigaGroupMax,
                        SpawnConditions.WolfTaigaChildMin, SpawnConditions.WolfTaigaChildMax);
            }
            //Taiga	Arctic Fox
            if (rollPassed(SpawnConditions.ArcticFoxChance))
            {
                SpawnGroup(block.getLocation(), Fox.class, SpawnConditions.ArcticFoxGroupMin, SpawnConditions.ArcticFoxGroupMax,
                        SpawnConditions.ArcticFoxChildMin, SpawnConditions.ArcticFoxChildMax);
            }
        }

        if (BiomeInfo.isOcean(block.getBiome())) {
            //Non Warm Oceans	Cod
            if (rollPassed(SpawnConditions.CodChance) && !BiomeInfo.isWarm(block.getBiome()))
            {
                SpawnGroup(block.getLocation(), Cod.class, SpawnConditions.CodGroupMin, SpawnConditions.CodGroupMax,
                        0, 0);
            }
            //Warm Oceans	Tropical Fish
            if (rollPassed(SpawnConditions.TropicalFishChance) && BiomeInfo.isWarm(block.getBiome()))
            {
                SpawnGroup(block.getLocation(), TropicalFish.class, SpawnConditions.TropicalFishGroupMin, SpawnConditions.TropicalFishGroupMax,
                        0, 0);
            }
            //Non Cold Oceans	Dolphin
            if (rollPassed(SpawnConditions.DolphinChance) && !BiomeInfo.isCold(block.getBiome()))
            {
                SpawnGroup(block.getLocation(), Dolphin.class, SpawnConditions.DolphinGroupMin, SpawnConditions.DolphinGroupMax,
                        SpawnConditions.DolphinChildMin, SpawnConditions.DolphinChildMax);
            }
            //Warm Oceans	Pufferfish
            if (rollPassed(SpawnConditions.PufferfishChance) && BiomeInfo.isWarm(block.getBiome()))
            {
                SpawnGroup(block.getLocation(), PufferFish.class, SpawnConditions.PufferfishGroupMin, SpawnConditions.PufferfishGroupMax,
                        0, 0);
            }
        }

        if (BiomeInfo.isRiver(block.getBiome())) {
            //River	Salmon
            if (rollPassed(SpawnConditions.SalmonChance))
            {
                SpawnGroup(block.getLocation(), Salmon.class, SpawnConditions.SalmonGroupMin, SpawnConditions.SalmonGroupMax,
                        0, 0);
            }
        }
        if (block.getType().equals(Material.GRASS)) {
            //Grass	Chicken
            if (rollPassed(SpawnConditions.ChickenChance))
            {
                SpawnGroup(block.getLocation(), Chicken.class, SpawnConditions.ChickenGroupMin, SpawnConditions.ChickenGroupMax,
                        SpawnConditions.ChickenChildMin, SpawnConditions.ChickenChildMax);
            }
            //Grass	Cow
            if (rollPassed(SpawnConditions.CowChance))
            {
                SpawnGroup(block.getLocation(), Cow.class, SpawnConditions.CowGroupMin, SpawnConditions.CowGroupMax,
                        SpawnConditions.CowChildMin, SpawnConditions.CowChildMax);
            }
            //Pig
            if (rollPassed(SpawnConditions.PigChance))
            {
                SpawnGroup(block.getLocation(), Pig.class, SpawnConditions.PigGroupMin, SpawnConditions.PigGroupMax,
                        SpawnConditions.PigChildMin, SpawnConditions.PigChildMax);
            }
        }

        if (block.getType().equals(Material.WATER) && block.getRelative(0, -1, 0 ).equals(Material.WATER)
            && block.getRelative(0, -2, 0).equals(Material.WATER)) {
            //Water, >= 3 deep	Squid
            if (rollPassed(SpawnConditions.SquidChance))
            {
                SpawnGroup(block.getRelative(0, -1, 0).getLocation(), Squid.class, SpawnConditions.SquidGroupMin, SpawnConditions.SquidGroupMax,
                        0, 0);
            }
        }

    }

    public boolean isDebugEnabled() {
        return true;
    }
}
