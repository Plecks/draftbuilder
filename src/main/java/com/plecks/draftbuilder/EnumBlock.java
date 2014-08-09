package com.plecks.draftbuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;

import com.plecks.common.BasicBlock;
import com.plecks.common.PlecksCore;
import com.plecks.common.StringUtil;

public enum EnumBlock {
	AIR(0, 0, "Air", "air"),
    STONE(1, 0, "Stone", "stone", "rock"),
    GRASS(2, 0, "Grass", "grass"),
    DIRT(3, 0, "Dirt", "dirt"),
    DIRT_GRASSLESS(3, 1, "Grassless Dirt", "glasslessdirt"),
    DIRT_PODZOL(3, 2, "Podzol (Dirt)", "podzol", "podzoldirt"),
    COBBLESTONE(4, 0, "Cobblestone", "cobblestone", "cobble"),
    WOOD(5, 0, "Oak Planks", "wood","oakwood", "woodplank", "plank", "woodplanks", "planks", "oakplanks"),
    WOOD_SPRUCE(5, 1, "Spruce Planks", "sprucewood", "spruceplank", "spruceplanks", "pinewood", "pineplank", "pineplanks"),
    WOOD_BIRCH(5, 2, "Birch Planks", "birchwood", "birchplank", "birchplanks"),
    WOOD_JUNGLE(5, 3, "Jungle Planks", "junglewood", "jungleplank", "jungleplanks"),
    WOOD_ACACIA(5, 4, "Acacia Planks", "acaciawood", "acaciaplank", "acaciaplanks"),
    WOOD_DARK_OAK(5, 5, "Dark Oak Planks", "darkoakwood", "darkwood", "darkoakplank", "darkplank", "darkplanks", "darkoakplanks"),
    SAPLING(6, 0, "Sapling", "sapling", "seedling"),
    SAPLING_SPRUCE(6, 1, "Spruce Sapling", "sprucesapling", "spruceseedling"),
    SAPLING_BIRCH(6, 2, "Birch Sapling", "birchsapling", "birchseedling"),
    SAPLING_JUNGLE(6, 3, "Jungle Sapling", "junglesapling", "jungleseedling"),
    SAPLING_ACACIA(6, 4, "Acacia Sapling", "acaciasapling", "acaciaseedling"),
    SAPLING_DARK_OAK(6, 5, "Dark Oak Sapling", "darkoaksapling", "darksapling", "darkoakseedling", "darkseedling"),
    BEDROCK(7, 0, "Bedrock", "adminium", "bedrock"),
    WATER(8, 0, "Water", "watermoving", "movingwater", "flowingwater", "waterflowing"),
    STATIONARY_WATER(9, 0, "Water (stationary)", "water", "waterstationary", "stationarywater", "stillwater"),
    LAVA(10, 0, "Lava", "lavamoving", "movinglava", "flowinglava", "lavaflowing"),
    STATIONARY_LAVA(11, 0, "Lava (stationary)", "lava", "lavastationary", "stationarylava", "stilllava"),
    SAND(12, 0, "Sand", "sand"),
    SAND_RED(12, 1, "Red Sand", "redsand"),
    GRAVEL(13, 0, "Gravel", "gravel"),
    GOLD_ORE(14, 0, "Gold ore", "goldore"),
    IRON_ORE(15, 0, "Iron ore", "ironore"),
    COAL_ORE(16, 0, "Coal ore", "coalore"),
    LOG(17, 0, "Log", "log", "tree", "pine", "oak", "birch"),
    LOG_SPRUCE(17, 1, "Spruce Log", "sprucelog", "sprucetree", "spruce", "redwood", "pine"),
    LOG_BIRCH(17, 2, "Birch Log", "birchlog", "birchtree", "birch"),
    LOG_JUNGLE(17, 3, "Jungle Log", "junglelog", "jungletree"),
    LEAVES(18, 0, "Leaves", "leaves", "leaf"),
    LEAVES_SPRUCE(18, 1, "Spruce Leaves", "spruceleaves", "spruceleaf"),
    LEAVES_BIRCH(18, 2, "Birch Leaves", "birchleaves", "birchleaf"),
    LEAVES_JUNGLE(18, 3, "Jungle Leaves", "jungleleaves", "jungleleaf"),
    SPONGE(19, 0, "Sponge", "sponge"),
    GLASS(20, 0, "Glass", "glass"),
    LAPIS_LAZULI_ORE(21, 0, "Lapis Lazuli Ore", "lapislazuliore", "blueore", "lapisore"),
    LAPIS_LAZULI(22, 0, "Lapis Lazuli", "lapislazuli", "lapislazuliblock", "bluerock"),
    DISPENSER(23, 0, "Dispenser", "dispenser"),
    SANDSTONE(24, 0, "Sandstone", "sandstone"),
    SANDSTONE_CHISELED(24, 1, "Chiseled Sandstone", "chiseledsandstone"),
    SANDSTONE_SMOOTH(24, 2, "Smooth Sandstone", "smoothsandstone"),
    NOTE_BLOCK(25, 0, "Note block", "musicblock", "noteblock", "note", "music", "instrument"),
    BED(26, 0, "Bed", "bed"),
    POWERED_RAIL(27, 0, "Powered Rail", "poweredrail", "boosterrail", "poweredtrack", "boostertrack", "booster"),
    DETECTOR_RAIL(28, 0, "Detector Rail", "detectorrail", "detector"),
    PISTON_STICKY_BASE(29, 0, "Sticky Piston", "stickypiston"),
    WEB(30, 0, "Web", "web", "spiderweb"),
    SHRUB(31, 0, "Dead Shrub", "deadshrub", "shrub"),
    TALL_GRASS(31, 1, "Tall Grass", "longgrass", "tallgrass"),
    FERN(31, 2, "Fern", "fern"),
    DEAD_BUSH(32, 0, "Shrub", "deadbush", "shrub", "deadshrub", "tumbleweed"),
    PISTON_BASE(33, 0, "Piston", "piston"),
    PISTON_EXTENSION(34, 0, "Piston Extension", "pistonextendsion", "pistonhead"),
    CLOTH(35, 0, "Wool", "cloth", "wool"),
    CLOTH_ORANGE(35, 1, "Orange Wool", "orangecloth", "orangewool"),
    CLOTH_MAGENTA(35, 2, "Magenta Wool", "magentacloth", "magentawool"),
    CLOTH_LIGHTBLUE(35, 3, "Light Blue Wool", "lightbluecloth", "lightbluewool"),
    CLOTH_YELLOW(35, 4, "Yellow Wool", "yellowcloth", "yellowwool"),
    CLOTH_LIME(35, 5, "Lime Wool", "limecloth", "limewool"),
    CLOTH_PINK(35, 6, "Pink Wool", "pinkcloth", "pinkwool"),
    CLOTH_GRAY(35, 7, "Gray Wool", "graycloth", "graywool"),
    CLOTH_LIGHTGRAY(35, 8, "Light Gray Wool", "lightgraycloth", "lightgraywool"),
    CLOTH_CYAN(35, 9, "Cyan Wool", "cyancloth", "cyanwool"),
    CLOTH_PURPLE(35, 10, "Purple Wool", "purplecloth", "purplewool"),
    CLOTH_BLUE(35, 11, "Blue Wool", "bluecloth", "bluewool"),
    CLOTH_BROWN(35, 12, "Brown Wool", "browncloth", "brownwool"),
    CLOTH_GREEN(35, 13, "Green Wool", "greencloth", "greenwool"),
    CLOTH_RED(35, 14, "Red Wool", "redcloth", "redwool"),
    CLOTH_BLACK(35, 15, "Black Wool", "blackcloth", "blackwool"),
    PISTON_MOVING_PIECE(36, 0, "Piston (moving piece)", "movingpiston"),
    YELLOW_FLOWER(37, 0, "Yellow Flower", "yellowflower", "flower"),
    FLOWER_POPPY(38, 0, "Poppy", "redflower", "poppyflower", "rose"),
    FLOWER_ORCHID(38, 1, "Blue Orchid", "blueflower", "orchidflower", "blueorchid", "orchid"),
    FLOWER_ALLIUM(38, 2, "Allium", "magentaflower", "alliumflower", "allium"),
    FLOWER_AZURE_BLUET(38, 3, "Azure Bluet", "lightgreyflower", "lightgrayflower", "azurebluetflower", "azurebluet", "bluet"),
    FLOWER_TULIP_RED(38, 4, "Red Tulip", "redtulip"),
    FLOWER_TULIP_ORANGE(38, 5, "Orange Tulip", "orangeflower", "orangetulip"),
    FLOWER_TULIP_WHITE(38, 6, "White Tulip", "whiteflower", "whitetulip"),
    FLOWER_TULIP_PINK(38, 7, "Pink Tulip", "pinkflower", "pinktulip"),
    FLOWER_OXEYE_DAISY(38, 8, "Oxeye Daisy", "oxeyedaisy", "daisy"),
    BROWN_MUSHROOM(39, 0, "Brown Mushroom", "brownmushroom", "mushroom"),
    RED_MUSHROOM(40, 0, "Red Mushroom", "redmushroom"),
    GOLD_BLOCK(41, 0, "Gold Block", "gold", "goldblock"),
    IRON_BLOCK(42, 0, "Iron Block", "iron", "ironblock"),
    DOUBLE_STEP(43, 0, "Double Step", "doubleslab", "doublestoneslab", "doublestep"),
    DOUBLE_STEP_SANDSTONE(43, 1, "Sandstone Double Step", "sandstonedoubleslab", "doublesandstoneslab", "doublesandstonestep"),
    DOUBLE_STEP_COBBLESTONE(43, 3, "Cobblestone Double Step", "cobblestonedoubleslab", "doublecobblestoneslab", "doublecobblestonestep"),
    DOUBLE_STEP_BRICK(43, 4, "Brick Double Step", "brickdoubleslab", "doublebricklab", "doublebrickstep"),
    DOUBLE_STEP_STONE_BRICK(43, 5, "Stone Brick Double Step", "stonebrickdoubleslab", "doublestonebrickslab", "doublestonebrickstep"),
    DOUBLE_STEP_NETHER_BRICK(43, 6, "Nether Brick Double Step", "netherbrickdoubleslab", "doublenetherbrickslab", "doublenetherbrickstep"),
    DOUBLE_STEP_QUARTZ(43, 7, "Quartz Double Step", "quartzdoubleslab", "doublequartzslab", "doublequartzstep"),
    DOUBLE_STEP_SMOOTH_STONE(43, 8, "Smoothstone Double Step", "smoothstonedoubleslab", "doublesmoothstoneslab", "doublesmoothstonestep"),
    DOUBLE_STEP_SMOOTH_SANDSTONE(43, 9, "Smooth Sandstone Double Step", "smoothsandstonedoubleslab", "doublesmoothsandstoneslab", "doublesmoothsandstonestep"),
    DOUBLE_STEP_TILE_QUARTZ(43, 15, "Tile Quartz Double Step", "tilequartzdoubleslab", "tilequartzdoublestoneslab", "tilequartzdoublestep"),
    STEP(44, 0, "Step", "slab", "stoneslab", "step", "halfstep"),
    STEP_SANDSTONE(44, 1, "Sandstone Step", "sandslab", "sandstoneslab", "sandstep", "sandstonestep"),
    STEP_COBBLESTONE(44, 3, "Cobblestone Step", "cobbleslab", "cobblestoneslab", "cobblestep", "cobblestonestep"),
    STEP_BRICK(44, 4, "Brick Step", "brickslab", "brickstep"),
    STEP_STONE_BRICK(44, 5, "Stone Brick Step", "stonebrickslab", "stonebrickstep"),
    STEP_NETHER_BRICK(44, 6, "Nether Brick Step", "netherbrickslab", "netherbrickstep"),
    STEP_QUARTZ(44, 7, "Quartz Step", "quartzslab", "quartzstep"),
    BRICK(45, 0, "Brick", "brick", "brickblock"),
    TNT(46, 0, "TNT", "tnt", "c4", "explosive"),
    BOOKCASE(47, 0, "Bookcase", "bookshelf", "bookshelves", "bookcase", "bookcases"),
    MOSSY_COBBLESTONE(48, 0, "Cobblestone (mossy)", "mossycobblestone", "mossstone", "mossystone", "mosscobble", "mossycobble", "moss", "mossy", "sossymobblecone"),
    OBSIDIAN(49, 0, "Obsidian", "obsidian"),
    TORCH(50, 0, "Torch", "torch", "light", "candle"),
    FIRE(51, 0, "Fire", "fire", "flame", "flames"),
    MOB_SPAWNER(52, 0, "Mob Spawner", "mobspawner", "spawner"),
    WOODEN_STAIRS(53, 0, "Wooden Stairs", "woodstair", "woodstairs", "woodenstair", "woodenstairs"),
    CHEST(54, 0, "Chest", "chest", "storage", "storagechest"),
    REDSTONE_WIRE(55, 0, "Redstone Wire", "redstone", "redstoneblock"),
    DIAMOND_ORE(56, 0, "Diamond Ore", "diamondore"),
    DIAMOND_BLOCK(57, 0, "Diamond Block", "diamond", "diamondblock"),
    WORKBENCH(58, 0, "Workbench", "workbench", "table", "craftingtable", "crafting"),
    CROPS(59, 0, "Crops", "crops", "crop", "plant", "plants"),
    SOIL(60, 0, "Soil", "soil", "farmland"),
    FURNACE(61, 0, "Furnace", "furnace"),
    BURNING_FURNACE(62, 0, "Furnace (burning)", "burningfurnace", "litfurnace"),
    SIGN_POST(63, 0, "Sign Post", "sign", "signpost"),
    WOODEN_DOOR(64, 0, "Wooden Door", "wooddoor", "woodendoor", "door"),
    LADDER(65, 0, "Ladder", "ladder"),
    MINECART_TRACKS(66, 0, "Minecart Tracks", "track", "tracks", "minecrattrack", "minecarttracks", "rails", "rail"),
    COBBLESTONE_STAIRS(67, 0, "Cobblestone Stairs", "cobblestonestair", "cobblestonestairs", "cobblestair", "cobblestairs"),
    WALL_SIGN(68, 0, "Wall Sign", "wallsign"),
    LEVER(69, 0, "Lever", "lever", "switch", "stonelever", "stoneswitch"),
    STONE_PRESSURE_PLATE(70, 0, "Stone Pressure Plate", "stonepressureplate", "stoneplate"),
    IRON_DOOR(71, 0, "Iron Door", "irondoor"),
    WOODEN_PRESSURE_PLATE(72, 0, "Wooden Pressure Plate", "woodpressureplate", "woodplate", "woodenpressureplate", "woodenplate", "plate", "pressureplate"),
    REDSTONE_ORE(73, 0, "Redstone Ore", "redstoneore"),
    GLOWING_REDSTONE_ORE(74, 0, "Glowing Redstone ore", "glowingredstoneore"),
    REDSTONE_TORCH_OFF(75, 0, "Redstone Torch (off)", "redstonetorchoff", "rstorchoff"),
    REDSTONE_TORCH_ON(76, 0, "Redstone Torch (on)", "redstonetorch", "redstonetorchon", "rstorchon", "redtorch"),
    STONE_BUTTON(77, 0, "Stone Button", "stonebutton", "button"),
    SNOW(78, 0, "Snow", "snow"),
    ICE(79, 0, "Ice", "ice"),
    SNOW_BLOCK(80, 0, "Snow Block", "snowblock"),
    CACTUS(81, 0, "Cactus", "cactus", "cacti"),
    CLAY(82, 0, "Clay", "clay"),
    SUGAR_CANE(83, 0, "Reed", "reed", "cane", "sugarcane", "sugarcanes", "vine", "vines"),
    JUKEBOX(84, 0, "Jukebox", "jukebox", "stereo", "recordplayer"),
    FENCE(85, 0, "Fence", "fence"),
    PUMPKIN(86, 0, "Pumpkin", "pumpkin"),
    NETHERRACK(87, 0, "Netherrack", "redmossycobblestone", "redcobblestone", "redmosstone", "redcobble", "netherstone", "netherrack", "nether", "hellstone"),
    SOUL_SAND(88, 0, "Soul Sand", "slowmud", "mud", "soulsand", "hellmud"),
    GLOWSTONE(89, 0, "Glowstone", "brittlegold", "glowstone", "lightstone", "brimstone", "australium"),
    PORTAL(90, 0, "Portal", "portal"),
    JACK_O_LANTERN(91, 0, "Pumpkin (on)", "pumpkinlighted", "pumpkinon", "litpumpkin", "jackolantern"),
    CAKE(92, 0, "Cake", "cake", "cakeblock"),
    REDSTONE_REPEATER_OFF(93, 0, "Redstone Repeater (off)", "diodeoff", "redstonerepeater", "repeateroff", "delayeroff"),
    REDSTONE_REPEATER_ON(94, 0, "Redstone Repeater (on)", "diodeon", "redstonerepeateron", "repeateron", "delayeron"),
    STAINED_GLASS_WHITE(95, 0, "White Stained Glass", "whiteglass", "whitestainedglass"),
	STAINED_GLASS_ORANGE(95, 1, "Orange Stained Glass", "orangeglass", "orangestainedglass"),
	STAINED_GLASS_MAGENTA(95, 2, "Magenta Stained Glass", "magentaglass", "magentastainedglass"),
	STAINED_GLASS_LIGHTBLUE(95, 3, "Light Blue Stained Glass", "lightblueglass", "lightbluestainedglass"),
	STAINED_GLASS_YELLOW(95, 4, "Yellow Stained Glass", "yellowglass", "yellowstainedglass"),
	STAINED_GLASS_LIME(95, 5, "Lime Stained Glass", "limeglass", "limestainedglass"),
	STAINED_GLASS_PINK(95, 6, "Pink Stained Glass", "pinkglass", "pinkstainedglass"),
	STAINED_GLASS_GRAY(95, 7, "Gray Stained Glass", "grayglass", "graystainedglass"),
	STAINED_GLASS_LIGHTGRAY(95, 8, "Light Gray Stained Glass", "lightgrayglass", "lightgraystainedglass"),
	STAINED_GLASS_CYAN(95, 9, "Cyan Stained Glass", "cyanglass", "cyanstainedglass"),
	STAINED_GLASS_PURPLE(95, 10, "Purple Stained Glass", "purpleglass", "purplestainedglass"),
	STAINED_GLASS_BLUE(95, 11, "Blue Stained Glass", "blueglass", "bluestainedglass"),
	STAINED_GLASS_BROWN(95, 12, "Brown Stained Glass", "brownglass", "brownstainedglass"),
	STAINED_GLASS_GREEN(95, 13, "Green Stained Glass", "greenglass", "greenstainedglass"),
	STAINED_GLASS_RED(95, 14, "Red Stained Glass", "redglass", "redstainedglass"),
	STAINED_GLASS_BLACK(95, 15, "Black Stained Glass", "blackglass", "blackstainedglass"),
    TRAP_DOOR(96, 0, "Trap Door", "trapdoor", "hatch", "floordoor"),
    SILVERFISH_BLOCK(97, 0, "Silverfish Block", "silverfish", "silver"),
    STONE_BRICK(98, 0, "Stone Brick", "stonebrick", "sbrick", "smoothstonebrick"),
    RED_MUSHROOM_CAP(99, 0, "Red Mushroom Cap", "giantmushroomred", "redgiantmushroom", "redmushroomcap"),
    BROWN_MUSHROOM_CAP(100, 0, "Brown Mushroom Vap", "giantmushroombrown", "browngiantmushoom", "brownmushroomcap"),
    IRON_BARS(101, 0, "Iron Bars", "ironbars", "ironfence"),
    GLASS_PANE(102, 0, "Glass Pane", "window", "glasspane", "glasswindow"),
    MELON_BLOCK(103, 0, "Melon (block)", "melonblock"),
    PUMPKIN_STEM(104, 0, "Pumpkin Stem", "pumpkinstem"),
    MELON_STEM(105, 0, "Melon Stem", "melonstem"),
    VINE(106, 0, "Vine", "vine", "vines", "creepers"),
    FENCE_GATE(107, 0, "Fence Gate", "fencegate", "gate"),
    BRICK_STAIRS(108, 0, "Brick Stairs", "brickstairs", "bricksteps"),
    STONE_BRICK_STAIRS(109, 0, "Stone Brick Stairs", "stonebrickstairs", "smoothstonebrickstairs"),
    MYCELIUM(110, 0, "Mycelium", "fungus", "mycel"),
    LILY_PAD(111, 0, "Lily Pad", "lilypad", "waterlily"),
    NETHER_BRICK(112, 0, "Nether Brick", "netherbrick"),
    NETHER_BRICK_FENCE(113, 0, "Nether Brick Fence", "netherbrickfence", "netherfence"),
    NETHER_BRICK_STAIRS(114, 0, "Nether Brick Stairs", "netherbrickstairs", "netherbricksteps", "netherstairs", "nethersteps"),
    NETHER_WART(115, 0, "Nether Wart", "netherwart", "netherstalk"),
    ENCHANTMENT_TABLE(116, 0, "Enchantment Table", "enchantmenttable", "enchanttable"),
    BREWING_STAND(117, 0, "Brewing Stand", "brewingstand"),
    CAULDRON(118, 0, "Cauldron"),
    END_PORTAL(119, 0, "End Portal", "endportal", "blackstuff", "airportal", "weirdblackstuff"),
    END_PORTAL_FRAME(120, 0, "End Portal Frame", "endportalframe", "airportalframe", "crystalblock"),
    END_STONE(121, 0, "End Stone", "endstone", "enderstone", "endersand"),
    DRAGON_EGG(122, 0, "Dragon Egg", "dragonegg", "dragons"),
    REDSTONE_LAMP_OFF(123, 0, "Redstone Lamp (off)", "redstonelamp", "redstonelampoff", "rslamp", "rslampoff", "rsglow", "rsglowoff"),
    REDSTONE_LAMP_ON(124, 0, "Redstone Lamp (on)", "redstonelampon", "rslampon", "rsglowon"),
    DOUBLE_WOODEN_STEP(125, 0, "Double Wood Step", "doublewoodslab", "doublewoodstep"),
    WOODEN_STEP(126, 0, "Wood Step", "woodenslab", "woodslab", "woodstep", "woodhalfstep"),
    WOODEN_STEP_SPRUCE(126, 1, "Spruce Wood Step", "sprucewoodenslab", "sprucewoodslab", "sprucewoodstep", "spruceslab", "sprucestep"),
    WOODEN_STEP_BIRCH(126, 2, "Birch Wood Step", "birchwoodenslab", "birchwoodslab", "birchwoodstep", "birchslab", "birchstep"),
    WOODEN_STEP_JUNGLE(126, 3, "Jungle Wood Step", "junglewoodenslab", "junglewoodslab", "junglewoodstep", "jungleslab", "junglestep"),
    WOODEN_STEP_ACACIA(126, 4, "Acacia Wood Step", "acaciawoodenslab", "acaciawoodslab", "acaciawoodstep", "acaciaslab", "acaciastep"),
    WOODEN_STEP_DARK_OAK(126, 5, "Dark Oak Wood Step", "darkoakwoodenslab", "darkoakwoodslab", "darkoakwoodstep", "darkoakslab", "darkoakstep"),
    COCOA_PLANT(127, 0, "Cocoa Plant", "cocoplant", "cocoaplant"),
    SANDSTONE_STAIRS(128, 0, "Sandstone Stairs", "sandstairs", "sandstonestairs"),
    EMERALD_ORE(129, 0, "Emerald Ore", "emeraldore"),
    ENDER_CHEST(130, 0, "Ender Chest", "enderchest"),
    TRIPWIRE_HOOK(131, 0, "Tripwire Hook", "tripwirehook"),
    TRIPWIRE(132, 0, "Tripwire", "tripwire"),
    EMERALD_BLOCK(133, 0, "Emerald Block", "emeraldblock"),
    SPRUCE_WOOD_STAIRS(134, 0, "Spruce Wood Stairs", "sprucestairs", "sprucewoodstairs"),
    BIRCH_WOOD_STAIRS(135, 0, "Birch Wood Stairs", "birchstairs", "birchwoodstairs"),
    JUNGLE_WOOD_STAIRS(136, 0, "Jungle Wood Stairs", "junglestairs", "junglewoodstairs"),
	COMMAND_BLOCK(137, 0, "Command Block", "commandblock"),
	BEACON(138, 0, "Beacon", "beacon"),
	COBBLESTONE_WALL(139, 0, "Cobblestone Wall", "cobblestonewall", "cobblewall", "wall"),
	FLOWER_POT(140, 0, "Flower Pot", "flowerpot", "pot"),
	CARROTS(141, 0, "Carrots", "Carrots", "carrot"),
	POTATOES(142, 0, "Potatoes", "Potatoes", "potato"),
	WOODEN_BUTTON(143, 0, "Wooden Button", "woodenbutton", "woodbutton"),
	HEAD(144, 0, "Head", "head", "skull"),
	ANVIL(145, 0, "Anvil", "anvil"),
	TRAPPED_CHEST(146, 0, "Trapped Chest", "trappedchest"),
	WEIGHTED_PRESSURE_PLATE_LIGHT(147, 0, "Wighted Pressure Plate (light)", "weightedpressureplatelight"),
	WEIGHTED_PRESSURE_PLATE_HEAVY(148, 0, "Wighted Pressure Plate (heavy)", "weightedpressureplateheavy"),
	REDSTONE_COMPARATOR_OFF(149, 0, "Redstone Comparator", "redstonecomparator", "comparator"),
	REDSTONE_COMPARATOR_ON(150, 0, "Redstone Comparator (active)", "redstonecomparatoractive", "comparatoractive"),
	DAYLIGHT_SENSOR(151, 0, "Daylight Sensor", "daylightsensor", "daysensor"),
	REDSTONE_BLOCK(152, 0, "Redstone Block", "redstoneblock"),
	NETHER_QUARTZ_ORE(153, 0, "Nether Quartz Ore", "netherquartzore", "quartzore"),
	HOPPER(154, 0, "Hopper", "hopper"),
	QUARTZ_BLOCK(155, 0, "Quartz Block", "quartzblock"),
	QUARTZ_STAIRS(156, 0, "Quartz Stairs", "quartzstairs"),
	ACTIVATOR_RAIL(157, 0, "Activator Rail", "activatorrail"),
	DROPPER(158, 0, "Dropper", "dropper"),
	STAINED_CLAY_WHITE(159, 0, "White Stained Clay", "whiteclay", "whitestainedclay"),
	STAINED_CLAY_ORANGE(159, 1, "Orange Stained Clay", "orangeclay", "orangestainedclay"),
	STAINED_CLAY_MAGENTA(159, 2, "Magenta Stained Clay", "magentaclay", "magentastainedclay"),
	STAINED_CLAY_LIGHTBLUE(159, 3, "Light Blue Stained Clay", "lightblueclay", "lightbluestainedclay"),
	STAINED_CLAY_YELLOW(159, 4, "Yellow Stained Clay", "yellowclay", "yellowstainedclay"),
	STAINED_CLAY_LIME(159, 5, "Lime Stained Clay", "limeclay", "limestainedclay"),
	STAINED_CLAY_PINK(159, 6, "Pink Stained Clay", "pinkclay", "pinkstainedclay"),
	STAINED_CLAY_GRAY(159, 7, "Gray Stained Clay", "grayclay", "graystainedclay"),
	STAINED_CLAY_LIGHTGRAY(159, 8, "Light Gray Stained Clay", "lightgrayclay", "lightgraystainedclay"),
	STAINED_CLAY_CYAN(159, 9, "Cyan Stained Clay", "cyanclay", "cyanstainedclay"),
	STAINED_CLAY_PURPLE(159, 10, "Purple Stained Clay", "purpleclay", "purplestainedclay"),
	STAINED_CLAY_BLUE(159, 11, "Blue Stained Clay", "blueclay", "bluestainedclay"),
	STAINED_CLAY_BROWN(159, 12, "Brown Stained Clay", "brownclay", "brownstainedclay"),
	STAINED_CLAY_GREEN(159, 13, "Green Stained Clay", "greenclay", "greenstainedclay"),
	STAINED_CLAY_RED(159, 14, "Red Stained Clay", "redclay", "redstainedclay"),
	STAINED_CLAY_BLACK(159, 15, "Black Stained Clay", "blackclay", "blackstainedclay"),
	STAINED_GLASS_PANE_WHITE(160, 0, "White Stained Glass Pane", "whiteglasspane", "whitestainedglasspane"),
	STAINED_GLASS_PANE_ORANGE(160, 1, "Orange Stained Glass Pane", "orangeglasspane", "orangestainedglasspane"),
	STAINED_GLASS_PANE_MAGENTA(160, 2, "Magenta Stained Glass Pane", "magentaglasspane", "magentastainedglasspane"),
	STAINED_GLASS_PANE_LIGHTBLUE(160, 3, "Light Blue Stained Glass Pane", "lightblueglasspane", "lightbluestainedglasspane"),
	STAINED_GLASS_PANE_YELLOW(160, 4, "Yellow Stained Glass Pane", "yellowglasspane", "yellowstainedglasspane"),
	STAINED_GLASS_PANE_LIME(160, 5, "Lime Stained Glass Pane", "limeglasspane", "limestainedglasspane"),
	STAINED_GLASS_PANE_PINK(160, 6, "Pink Stained Glass Pane", "pinkglasspane", "pinkstainedglasspane"),
	STAINED_GLASS_PANE_GRAY(160, 7, "Gray Stained Glass Pane", "grayglasspane", "graystainedglasspane"),
	STAINED_GLASS_PANE_LIGHTGRAY(160, 8, "Light Gray Stained Glass Pane", "lightgrayglasspane", "lightgraystainedglasspane"),
	STAINED_GLASS_PANE_CYAN(160, 9, "Cyan Stained Glass Pane", "cyanglasspane", "cyanstainedglasspane"),
	STAINED_GLASS_PANE_PURPLE(160, 10, "Purple Stained Glass Pane", "purpleglasspane", "purplestainedglasspane"),
	STAINED_GLASS_PANE_BLUE(160, 11, "Blue Stained Glass Pane", "blueglasspane", "bluestainedglasspane"),
	STAINED_GLASS_PANE_BROWN(160, 12, "Brown Stained Glass Pane", "brownglasspane", "brownstainedglasspane"),
	STAINED_GLASS_PANE_GREEN(160, 13, "Green Stained Glass Pane", "greenglasspane", "greenstainedglasspane"),
	STAINED_GLASS_PANE_RED(160, 14, "Red Stained Glass Pane", "redglasspane", "redstainedglasspane"),
	STAINED_GLASS_PANE_BLACK(160, 15, "Black Stained Glass Pane", "blackglasspane", "blackstainedglasspane"),
	LEAVES_ACACIA(161, 0, "Acacia Leaves", "acacialeaves"),
	LEAVES_DARK_OAK(161, 1, "Dark Oak Leaves", "darkoakleaves", "darkleaves"),
	LOG_ACACIA(162, 0, "Acacia Log", "acacialog", "acaciatree", "acacia"),
	LOG_DARK_OAK(162, 1, "Dark Oak Log", "darkoaklog", "darklog", "darkoaktree", "darktree", "darkoak"),
	ACACIA_STAIRS(163, 0, "Acacia Wood Stairs", "acaciastairs", "acaciawoodstairs"),
	DARK_OAK_STAIRS(164, 1, "Dark Oak Stairs", "darkoakstairs", "darkstairs"),
	HAY_BLOCK(170, 0, "Hay Block", "hayblock"),
	CARPET(171, 0, "Carpet", "carpet", "whitecarpet"),
	CARPET_ORANGE(171, 1, "Orange Carpet", "orangecarpet"),
	CARPET_MAGENTA(171, 2, "Magenta Carpet", "magentacarpet"),
	CARPET_LIGHTBLUE(171, 3, "Light Blue Carpet", "lightbluecarpet"),
	CARPET_YELLOW(171, 4, "Yellow Carpet", "yellowcarpet"),
	CARPET_LIME(171, 5, "Lime Carpet", "limecarpet"),
	CARPET_PINK(171, 6, "Pink Carpet", "pinkcarpet"),
	CARPET_GRAY(171, 7, "Gray Carpet", "graycarpet"),
	CARPET_LIGHTGRAY(171, 8, "Light Gray Carpet", "lightgraycarpet"),
	CARPET_CYAN(171, 9, "Cyan Carpet", "cyancarpet"),
	CARPET_PURPLE(171, 10, "Purple Carpet", "purplecarpet"),
	CARPET_BLUE(171, 11, "Blue Carpet", "bluecarpet"),
	CARPET_BROWN(171, 12, "Brown Carpet", "browncarpet"),
	CARPET_GREEN(171, 13, "Green Carpet", "greencarpet"),
	CARPET_RED(171, 14, "Red Carpet", "redcarpet"),
	CARPET_BLACK(171, 15, "Black Carpet", "blackcarpet"),
	HARDENED_CLAY(172, 0, "Hardened Clay", "hardenedclay"),
	COAL_BLOCK(173, 0, "Coal Block", "coalblock"),
	PACKED_ICED(174, 0, "Packed Ice", "packedice", "packice", "icepack"),
	LARGE_FLOWER_SUNFLOWER(175, 0, "Sunflower", "sunflower"),
	LARGE_FLOWER_LILAC(175, 1, "Lilac", "lilac"),
	LARGE_FLOWER_TALLGRASS(175, 2, "Double Tallgrass", "doubletallgrass", "doublegrass"),
	LARGE_FLOWER_FERN(175, 3, "Fern", "fern"),
	LARGE_FLOWER_ROSE_BUSH(175, 4, "Rose Bush", "rosebush"),
	LARGE_FLOWER_PEONY(175, 5, "Peony", "peony");

	/**
	* Stores a map of the IDs for fast access.
	*/
    private static final Map<Integer, EnumBlock> idmetaMap = new HashMap<Integer, EnumBlock>();
    /**
	* Stores a map of the names for fast access.
	*/
    private static final Map<String, EnumBlock> lookup = new HashMap<String, EnumBlock>();

    private final int id;
    private final String name;
    private final String[] lookupKeys;
	private final int meta; //For blocks that use meta for different blocks, eg wood/wool

    static {
        for (EnumBlock type : EnumSet.allOf(EnumBlock.class)) {
        	//Cantor pairing function to combine id and meta into unique id
        	int idmeta = (int) (.5 * (type.id + type.meta) * (type.id + type.meta + 1) + type.meta);
            idmetaMap.put(idmeta, type);
            for (String key : type.lookupKeys) {
                lookup.put(key, type);
            }
        }
    }


	/**
	* Construct the type.
	*
	* @param id
	* @param name
	*/
    EnumBlock(int id, int meta, String name, String lookupKey) {
        this.id = id;
        this.name = name;
        this.meta =  meta;
        this.lookupKeys = new String[] { lookupKey };
    }

	/**
	* Construct the type.
	*
	* @param id
	* @param name
	*/
    EnumBlock(int id, int meta, String name, String... lookupKeys) {
        this.id = id;
        this.name = name;
        this.meta =  meta;
        this.lookupKeys = lookupKeys;
    }
    
    public static EnumBlock fromIDAndMeta(int id, int meta) {
    	meta = removeOrientationFromMeta(id, meta);
    	int idmeta = (int) (.5 * (id + meta) * (id + meta + 1) + meta);
    	return idmetaMap.get(idmeta);
    }

    /**
	* Return type from name. May return null.
	*
	* @param name
	* @return
	*/
    public static EnumBlock lookup(String name) {
        return lookup(name, true);
    }

    /**
	* Return type from name. May return null.
	*
	* @param name
	* @param fuzzy
	* @return
	*/
    public static EnumBlock lookup(String name, boolean fuzzy) {
        try {
            return fromIDAndMeta(Integer.parseInt(name),0);
        } catch (NumberFormatException e) {
            return StringUtil.lookup(lookup, name, fuzzy);
        }
    }

    /**
	* Get block numeric ID.
	*
	* @return
	*/
    public int getID() {
        return id;
    }
    
    public int getMeta() {
		return meta;
	}

    /**
	* Get user-friendly block name.
	*
	* @return
	*/
    public String getName() {
        return name;
    }

    public static final Set<Integer> isUsable = new HashSet<Integer>();
    static
    {
    	isUsable.add(DISPENSER.id);
    	isUsable.add(NOTE_BLOCK.id);
    	isUsable.add(CHEST.id);
    	isUsable.add(WORKBENCH.id);
    	isUsable.add(FURNACE.id);
    	isUsable.add(BURNING_FURNACE.id);
    	isUsable.add(WOODEN_DOOR.id);
    	isUsable.add(LEVER.id);
    	isUsable.add(STONE_BUTTON.id);
    	isUsable.add(JUKEBOX.id);
    	isUsable.add(REDSTONE_REPEATER_OFF.id);
    	isUsable.add(REDSTONE_REPEATER_ON.id);
    	isUsable.add(TRAP_DOOR.id);
    	isUsable.add(FENCE_GATE.id);
    	isUsable.add(ENCHANTMENT_TABLE.id);
    	isUsable.add(BREWING_STAND.id);
    	isUsable.add(CAULDRON.id);
    	isUsable.add(ENDER_CHEST.id);
    	isUsable.add(TRIPWIRE.id);
    	isUsable.add(TRIPWIRE_HOOK.id);
    	isUsable.add(COMMAND_BLOCK.id);
    	isUsable.add(BEACON.id);
    	isUsable.add(WOODEN_BUTTON.id);
    	isUsable.add(ANVIL.id);
    	isUsable.add(TRAPPED_CHEST.id);
    	isUsable.add(REDSTONE_COMPARATOR_ON.id);
    	isUsable.add(REDSTONE_COMPARATOR_OFF.id);
    	isUsable.add(HOPPER.id);
    	isUsable.add(DROPPER.id);
    }
    
    /** Block will get used if right-clicked. Must be sneaking in order to place against this block */
    public static boolean isUsable(int id)
    {
    	return isUsable.contains(id);
    }
    
    public static boolean isBuildable(int id)
    {
    	return (id != 0 && !isUsable.contains(id) && Block.getBlockById(id).getMaterial().isSolid());
    }
    
    public static final Set<Integer> needsMetaForItem = new HashSet<Integer>();
    static
    {
    	needsMetaForItem.add(DIRT.id);
    	needsMetaForItem.add(WOOD.id);
    	needsMetaForItem.add(SAPLING.id);
    	needsMetaForItem.add(SAND.id);
    	needsMetaForItem.add(LOG.id);
    	needsMetaForItem.add(LEAVES.id);
    	needsMetaForItem.add(LEAVES_ACACIA.id);
    	needsMetaForItem.add(SHRUB.id);
    	needsMetaForItem.add(CLOTH.id);
    	needsMetaForItem.add(FLOWER_POPPY.id);
    	needsMetaForItem.add(LARGE_FLOWER_SUNFLOWER.id);
    	needsMetaForItem.add(STEP.id);
    	needsMetaForItem.add(DOUBLE_STEP.id);
    	needsMetaForItem.add(WOODEN_STEP.id);
    	needsMetaForItem.add(SANDSTONE.id);
    	needsMetaForItem.add(TALL_GRASS.id);
    	needsMetaForItem.add(STONE_BRICK.id);
    	needsMetaForItem.add(COBBLESTONE_WALL.id);
    	needsMetaForItem.add(HEAD.id);
    	needsMetaForItem.add(QUARTZ_BLOCK.id);
    	needsMetaForItem.add(STAINED_CLAY_WHITE.id);
    	needsMetaForItem.add(CARPET.id);
    	needsMetaForItem.add(STAINED_GLASS_WHITE.id);
    	needsMetaForItem.add(STAINED_GLASS_PANE_WHITE.id);
    }
    
    /** Block requires extra metadata to select correct item in the inventory */
    public static boolean needsMetaForItem(int id)
    {
    	return needsMetaForItem.contains(id);
    }
    
    public static final Set<Integer> needsMetaForFacing = new HashSet<Integer>();
    static
    {
    	needsMetaForFacing.add(DISPENSER.id);
    	needsMetaForFacing.add(BED.id);
    	needsMetaForFacing.add(PISTON_STICKY_BASE.id);
    	needsMetaForFacing.add(PISTON_BASE.id);
    	needsMetaForFacing.add(WOODEN_STAIRS.id);
    	needsMetaForFacing.add(CHEST.id);
    	needsMetaForFacing.add(FURNACE.id);
    	needsMetaForFacing.add(SIGN_POST.id);
    	needsMetaForFacing.add(HEAD.id);
    	needsMetaForFacing.add(WOODEN_DOOR.id);
    	needsMetaForFacing.add(COBBLESTONE_STAIRS.id);
    	needsMetaForFacing.add(IRON_DOOR.id);
    	needsMetaForFacing.add(PUMPKIN.id);
    	needsMetaForFacing.add(JACK_O_LANTERN.id);
    	needsMetaForFacing.add(REDSTONE_REPEATER_OFF.id);
    	needsMetaForFacing.add(REDSTONE_REPEATER_ON.id);
    	needsMetaForFacing.add(FENCE_GATE.id);
    	needsMetaForFacing.add(BRICK_STAIRS.id);
    	needsMetaForFacing.add(STONE_BRICK_STAIRS.id);
    	needsMetaForFacing.add(NETHER_BRICK_STAIRS.id);
    	needsMetaForFacing.add(BREWING_STAND.id);
    	needsMetaForFacing.add(SANDSTONE_STAIRS.id);
    	needsMetaForFacing.add(ENDER_CHEST.id);
    	needsMetaForFacing.add(SPRUCE_WOOD_STAIRS.id);
    	needsMetaForFacing.add(BIRCH_WOOD_STAIRS.id);
    	needsMetaForFacing.add(JUNGLE_WOOD_STAIRS.id);
    	needsMetaForFacing.add(ANVIL.id);
    	needsMetaForFacing.add(TRAPPED_CHEST.id);
    	needsMetaForFacing.add(REDSTONE_COMPARATOR_OFF.id);
    	needsMetaForFacing.add(REDSTONE_COMPARATOR_ON.id);
    	needsMetaForFacing.add(QUARTZ_STAIRS.id);
    	needsMetaForFacing.add(ACACIA_STAIRS.id);
    	needsMetaForFacing.add(DARK_OAK_STAIRS.id);
    	needsMetaForFacing.add(DROPPER.id);
    }
    
    
    /** Block's metadata requires the player to face a specific direction */
    public static boolean needsMetaForFacing(int id)
    {
    	return needsMetaForFacing.contains(id);
    }
    
    /** Returns the direction player needs to face to orient a block properly. Can return null */
    public static EnumDirection getFacingDirection(BasicBlock block)
    {
    	switch(fromIDAndMeta(block.blockID,block.blockMeta))
    	{
    		case BED:
    			switch(block.blockMeta)
    			{
    				case 0: return EnumDirection.SOUTH;
    				case 1: return EnumDirection.WEST;
					case 2: return EnumDirection.NORTH;
					case 3: return EnumDirection.EAST;
    			}
    			break;
    			
    		case PISTON_BASE:
    		case PISTON_STICKY_BASE:
    			switch(block.blockMeta)
    			{
    				case 3: return EnumDirection.NORTH;
    				case 4: return EnumDirection.EAST;
					case 2: return EnumDirection.SOUTH;
					case 5: return EnumDirection.WEST;
					case 1: return EnumDirection.DOWN;
					case 0: return EnumDirection.UP;
    			}
    			break;
    		case WOODEN_STAIRS:
    		case COBBLESTONE_STAIRS:
    		case BRICK_STAIRS:
    		case STONE_BRICK_STAIRS:
    		case NETHER_BRICK_STAIRS:
    		case SANDSTONE_STAIRS:
    		case SPRUCE_WOOD_STAIRS:
    		case BIRCH_WOOD_STAIRS:
    		case JUNGLE_WOOD_STAIRS:
    		case QUARTZ_STAIRS:
    		case ACACIA_STAIRS:
    		case DARK_OAK_STAIRS:
	    		{
	    			int flip = (EnumDirection.getPlayerDirectionWithVertical() == EnumDirection.UP ? 4 : 0);
	    			switch(block.blockMeta & 3)
	    			{
	    				//Bitwise OR to add the upside down flag to the cardinal direction meta
	    				case 2: return EnumDirection.SOUTH;
	    				case 1: return EnumDirection.WEST;
						case 3: return EnumDirection.NORTH;
						case 0: return EnumDirection.EAST;
						default:
	    			}
	    		}
    			break;
    		case SIGN_POST:
    			switch(block.blockMeta)
    			{
    				case 0: return EnumDirection.NORTH;
    				case 4: return EnumDirection.EAST;
					case 8: return EnumDirection.SOUTH;
					case 12:return EnumDirection.WEST;
    			}
    			break;
    		case WOODEN_DOOR:
    		case IRON_DOOR:
    			switch(block.blockMeta)
    			{
    				case 0: return EnumDirection.EAST;
    				case 1: return EnumDirection.SOUTH;
    				case 2: return EnumDirection.WEST;
					case 3: return EnumDirection.NORTH;
    			}
    			break;
    		
    		case FURNACE:
    		case CHEST:
    			switch(block.blockMeta)
    			{
    				case 2: return EnumDirection.SOUTH;
    				case 5: return EnumDirection.WEST;
					case 3: return EnumDirection.NORTH;
					case 4: return EnumDirection.EAST;
    			}
    			break;
    		case DISPENSER:
    		case DROPPER:
    			switch(block.blockMeta)
    			{
    				case 2: return EnumDirection.SOUTH;
    				case 5: return EnumDirection.WEST;
					case 3: return EnumDirection.NORTH;
					case 4: return EnumDirection.EAST;
					case 0: return EnumDirection.UP;
					case 1: return EnumDirection.DOWN;
    			}
    			break;
    		
    		case PUMPKIN:
    		case JACK_O_LANTERN:
    			switch(block.blockMeta)
    			{
    				case 2: return EnumDirection.SOUTH;
    				case 3: return EnumDirection.WEST;
					case 0: return EnumDirection.NORTH;
					case 1: return EnumDirection.EAST;
    			}
    			break;
    		case REDSTONE_REPEATER_ON:
    		case REDSTONE_REPEATER_OFF:
    		case REDSTONE_COMPARATOR_ON:
    		case REDSTONE_COMPARATOR_OFF:
    			switch(block.blockMeta)
    			{
	    			case 2: return EnumDirection.SOUTH;
					case 3: return EnumDirection.WEST;
					case 0: return EnumDirection.NORTH;
					case 1: return EnumDirection.EAST;
    			}
    			break;
    			
    		case TRAP_DOOR:
	    		{
	    			switch(block.blockMeta & 3)
	    			{
		    			case 0: return EnumDirection.SOUTH;
						case 3: return EnumDirection.WEST;
						case 1: return EnumDirection.NORTH;
						case 2: return EnumDirection.EAST;
	    			}
	    			break;
	    		}

    		case FENCE_GATE:
    			switch(block.blockMeta)
    			{
	    			case 0: return EnumDirection.SOUTH;
					case 1: return EnumDirection.WEST;
					case 2: return EnumDirection.NORTH;
					case 3: return EnumDirection.EAST;
    			}
    			break;
    		case HEAD:
    			//TODO Need to get orientation from tileentity data
    			break;
    		case ANVIL:
    			switch(block.blockMeta & 3)
    			{
					case 0: return EnumDirection.WEST;
					case 1: return EnumDirection.NORTH;
					case 2: return EnumDirection.EAST;
					case 3: return EnumDirection.SOUTH;
    			}
    			break;
    		default:
    	}
    	
    	return null;
    }
    
    
    /** Returns direction, relative to given block location, of the block we need to click on to place the block correctly
     *  Will return null if no particular direction is required */
    public static EnumDirection getPlaceDirection(BasicBlock block)
    {
    	switch(fromIDAndMeta(block.blockID,block.blockMeta))
    	{
    		case SAPLING:
    		case SAPLING_SPRUCE:
    		case SAPLING_BIRCH:
    		case SAPLING_JUNGLE:
    		case SAPLING_ACACIA:
    		case SAPLING_DARK_OAK:
    		case BED:
    		case POWERED_RAIL:
    		case DETECTOR_RAIL:
    		case TALL_GRASS:
    		case DEAD_BUSH:
    		case YELLOW_FLOWER:
    		case FLOWER_POPPY:
    		case FLOWER_ORCHID:
    		case FLOWER_ALLIUM:
    		case FLOWER_AZURE_BLUET:
    		case FLOWER_TULIP_RED:
    		case FLOWER_TULIP_ORANGE:
    		case FLOWER_TULIP_WHITE:
    		case FLOWER_TULIP_PINK:
    		case FLOWER_OXEYE_DAISY:
    		case RED_MUSHROOM:
    		case BROWN_MUSHROOM:
    		case REDSTONE_WIRE:
    		case CROPS:
    		case SIGN_POST:
    		case WOODEN_DOOR:
    		case MINECART_TRACKS:
    		case STONE_PRESSURE_PLATE:
    		case IRON_DOOR:
    		case WOODEN_PRESSURE_PLATE:
    		case CACTUS:
    		case SUGAR_CANE:
    		case REDSTONE_REPEATER_ON:
    		case REDSTONE_REPEATER_OFF:
    		case REDSTONE_COMPARATOR_ON:
    		case REDSTONE_COMPARATOR_OFF:
    		case NETHER_WART:
    		case BREWING_STAND:
    		case LARGE_FLOWER_SUNFLOWER:
    		case LARGE_FLOWER_LILAC:
    		case LARGE_FLOWER_TALLGRASS:
    		case LARGE_FLOWER_FERN:
    		case LARGE_FLOWER_ROSE_BUSH:
    		case LARGE_FLOWER_PEONY:
    		case CARPET:
    			return EnumDirection.DOWN;

    		case LOG:
    		case LOG_SPRUCE:
    		case LOG_BIRCH:
    		case LOG_JUNGLE:
    		case LOG_ACACIA:
    		case LOG_DARK_OAK:
    			if((block.blockMeta & 4) > 0)
    			{
    				if(isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(block.getX() + 1, block.getY(), block.getZ())))){
    					return EnumDirection.EAST;
    				}
   					return EnumDirection.WEST;

    			}
    			else if((block.blockMeta & 8) > 0)
    			{
    				if(isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(block.getX(), block.getY(), block.getZ() - 1)))){
    					return EnumDirection.NORTH;
    				}
    				return EnumDirection.SOUTH;
    			}
    			if(isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(block.getX(), block.getY() + 1, block.getZ())))){
    				return EnumDirection.UP;
    			}
    			return EnumDirection.DOWN;
    		case TORCH:
    		case REDSTONE_TORCH_ON:
    		case REDSTONE_TORCH_OFF:
    			switch(block.blockMeta)
    			{
    				case 1: return EnumDirection.WEST;
    				case 2: return EnumDirection.EAST;
    				case 3: return EnumDirection.NORTH;
    				case 4: return EnumDirection.SOUTH;
    				case 5: return EnumDirection.DOWN;
    				default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),true,false));
    			}
    		
    		case LADDER:
    		case WALL_SIGN:
    			switch(block.blockMeta)
    			{
    				case 2: return EnumDirection.SOUTH;
    				case 3: return EnumDirection.NORTH;
    				case 4: return EnumDirection.EAST;
    				case 5: return EnumDirection.WEST;
    				default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),false,false));
    			}
    		case HOPPER:
    			switch(block.blockMeta)
    			{
    				case 2: return EnumDirection.NORTH;
    				case 3: return EnumDirection.SOUTH;
    				case 4: return EnumDirection.WEST;
    				case 5: return EnumDirection.EAST;
    				default: return EnumDirection.DOWN;
    			}
    		case LEVER:
    			switch(block.blockMeta & 7)
    			{
    				case 0:
    				case 7: 
    					return EnumDirection.UP;
    				case 1: return EnumDirection.WEST;
    				case 2: return EnumDirection.EAST;
    				case 3: return EnumDirection.NORTH;
    				case 4: return EnumDirection.SOUTH;
    				case 5:
    				case 6:
    					return EnumDirection.DOWN;
    				default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),true,true));
    			}
    		case STONE_BUTTON:
    			switch(block.blockMeta & 7)
    			{
	    			case 1: return EnumDirection.WEST;
	    			case 2: return EnumDirection.EAST;
	    			case 3: return EnumDirection.NORTH;
	    			case 4: return EnumDirection.SOUTH;
	    			default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),false,false));
    			}
    		case TRAP_DOOR:
    			switch(block.blockMeta & 3)
    			{
	    			case 0: return EnumDirection.SOUTH;
	    			case 1: return EnumDirection.NORTH;
	    			case 2: return EnumDirection.EAST;
	    			case 3: return EnumDirection.WEST;
	    			default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),false,false));
    			}
    		case VINE:
    			switch (block.blockMeta)
    			{
	    			case 1: return EnumDirection.SOUTH;
	    			case 2: return EnumDirection.WEST;
	    			case 4: return EnumDirection.NORTH;
	    			case 8: return EnumDirection.EAST;
	    			default: return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),false,false));
    			}
    		case HEAD:
    			switch(block.blockMeta)
    			{
	    			case 2: return EnumDirection.SOUTH;
					case 4: return EnumDirection.WEST;
					case 3: return EnumDirection.NORTH;
					case 5: return EnumDirection.EAST;
					default: return EnumDirection.DOWN;
    			}	
    		case STEP:
    		case STEP_SANDSTONE:
    		case STEP_COBBLESTONE:
    		case STEP_BRICK:
    		case STEP_STONE_BRICK:
    		case STEP_NETHER_BRICK:
    		case STEP_QUARTZ:
    		case WOODEN_STEP:
    		case WOODEN_STEP_SPRUCE:
    		case WOODEN_STEP_BIRCH:
    		case WOODEN_STEP_JUNGLE:
    		case WOODEN_STEP_ACACIA:
    		case WOODEN_STEP_DARK_OAK:
    			return ((block.blockMeta & 8) != 0 ? EnumDirection.UP : EnumDirection.DOWN);
    		
    		case WOODEN_STAIRS:
    		case COBBLESTONE_STAIRS:
    		case BRICK_STAIRS:
    		case STONE_BRICK_STAIRS:
    		case NETHER_BRICK_STAIRS:
    		case SANDSTONE_STAIRS:
    		case SPRUCE_WOOD_STAIRS:
    		case BIRCH_WOOD_STAIRS:
    		case JUNGLE_WOOD_STAIRS:
    		case QUARTZ_STAIRS:
    		case ACACIA_STAIRS:
    		case DARK_OAK_STAIRS:
    			if((block.blockMeta & 4) != 0){
    				return EnumDirection.UP;
    			}
    			
			default:
				return (getAnyPlaceableDirection(block.getX(),block.getY(),block.getZ(),true,true));
    			
    	}
    }
    
    public static EnumDirection getAnyPlaceableDirection(int x, int y, int z, boolean down, boolean up)
    {
    	return ((isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x, y - 1, z)))&& down) ? EnumDirection.DOWN :
			isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x + 1, y, z))) ? EnumDirection.EAST :
			isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x, y, z + 1))) ? EnumDirection.SOUTH :
			isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x - 1, y, z))) ? EnumDirection.WEST :
			isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x, y, z - 1))) ? EnumDirection.NORTH : 
			(isBuildable(Block.getIdFromBlock(PlecksCore.mc.theWorld.getBlock(x, y + 1, z))) && up) ? EnumDirection.UP : null);
    }
    
    /** Returns the metadata of a block based on current player orientation */
    public static int addDirectionToMeta(EnumBlock block)
    {
    	switch(block)
    	{
    		case LOG:
    		case LOG_SPRUCE:
    		case LOG_BIRCH:
    		case LOG_JUNGLE:
    		case LOG_ACACIA:
    		case LOG_DARK_OAK:
    			switch(EnumDirection.getPlayerDirectionWithVertical())
    			{
    				case UP:
    				case DOWN:
    					return block.getMeta();
    				case SOUTH:
					case NORTH: 
						return (block.getMeta() | 8);
					case EAST:
					case WEST:
						return (block.getMeta() | 4);
    			}
    			break;
    		case TORCH:
			case REDSTONE_TORCH_ON:
			case REDSTONE_TORCH_OFF:
				switch(EnumDirection.getPlayerDirectionWithVertical())
				{
					case SOUTH: return 4;
					case WEST:  return 1;
					case NORTH: return 3;
					case EAST:  return 2;
					default:    return 5;
				}
    		case BED:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 0;
    				case WEST:  return 1;
					case NORTH: return 2;
					case EAST:  return 3;
					default:
    			}
    			break;
    		case STEP:
    		case STEP_SANDSTONE:
    		case STEP_COBBLESTONE:
    		case STEP_BRICK:
    		case STEP_STONE_BRICK:
    		case STEP_NETHER_BRICK:
    		case STEP_QUARTZ:
    		case WOODEN_STEP:
    		case WOODEN_STEP_SPRUCE:
    		case WOODEN_STEP_BIRCH:
    		case WOODEN_STEP_JUNGLE:
    		case WOODEN_STEP_ACACIA:
    		case WOODEN_STEP_DARK_OAK:
    			return ((EnumDirection.getPlayerDirectionWithVertical() == EnumDirection.UP ? block.getMeta() | 8 : block.getMeta()));
    			
    		case PISTON_BASE:
    		case PISTON_STICKY_BASE:
    			switch(EnumDirection.getPlayerDirectionWithVertical())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 5;
					case NORTH: return 3;
					case EAST:  return 4;
					case UP:    return 0;
					case DOWN:  return 1;
    			}
    			break;
    		case WOODEN_STAIRS:
    		case COBBLESTONE_STAIRS:
    		case BRICK_STAIRS:
    		case STONE_BRICK_STAIRS:
    		case NETHER_BRICK_STAIRS:
    		case SANDSTONE_STAIRS:
    		case SPRUCE_WOOD_STAIRS:
    		case BIRCH_WOOD_STAIRS:
    		case JUNGLE_WOOD_STAIRS:
    		case QUARTZ_STAIRS:
    		case ACACIA_STAIRS:
    		case DARK_OAK_STAIRS:
	    		{
	    			int flip = (EnumDirection.getPlayerDirectionWithVertical() == EnumDirection.UP ? 4 : 0);
	    			switch(EnumDirection.getPlayerDirection())
	    			{
	    				//Bitwise OR to add the upside down flag to the cardinal direction meta
	    				case SOUTH: return 2 | flip;
	    				case WEST:  return 1 | flip;
						case NORTH: return 3 | flip;
						case EAST:  return 0 | flip;
						default:
	    			}
	    		}
    			break;
    		case SIGN_POST:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 0x8;
    				case WEST:  return 0xC;
					case NORTH: return 0x0;
					case EAST:  return 0x4;
					default:
    			}
    			break;
    		case WOODEN_DOOR:
    		case IRON_DOOR:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 1;
    				case WEST:  return 2;
					case NORTH: return 3;
					case EAST:  return 0;
					default:
    			}
    			break;
    		
    		case LADDER:
    		case WALL_SIGN:
    		case FURNACE:
    		case CHEST:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 5;
					case NORTH: return 3;
					case EAST:  return 4;
					default:
    			}
    			break;
    		case HOPPER:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 3;
    				case WEST:  return 4;
					case NORTH: return 2;
					case EAST:  return 5;
					default:
    			}
    			break;
    		case DISPENSER:
    		case DROPPER:
    			switch(EnumDirection.getPlayerDirectionWithVertical())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 5;
					case NORTH: return 3;
					case EAST:  return 4;
					case UP:    return 0;
					case DOWN:  return 1;
					default:
    			}
    			break;
    		case LEVER:
    			switch(EnumDirection.getPlayerDirectionWithVertical())
    			{
    				case SOUTH: return 4;
    				case WEST:  return 1;
					case NORTH: return 3;
					case EAST:  return 2;
					case UP:    return 7;
					case DOWN:  return 5;
    			}
    			break;
    		case STONE_BUTTON:
    		case WOODEN_BUTTON:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 4;
    				case WEST:  return 1;
					case NORTH: return 3;
					case EAST:  return 2;
					default:
    			}
    			break;
    		case PUMPKIN:
    		case JACK_O_LANTERN:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 3;
					case NORTH: return 0;
					case EAST:  return 1;
					default:
    			}
    			break;
    		case REDSTONE_REPEATER_ON:
    		case REDSTONE_REPEATER_OFF:
    		case REDSTONE_COMPARATOR_ON:
    		case REDSTONE_COMPARATOR_OFF:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 3;
					case NORTH: return 0;
					case EAST:  return 1;
					default:
    			}
    			break;
    			
    		case TRAP_DOOR:
	    		{
	    			int flip = (EnumDirection.getPlayerDirectionWithVertical() == EnumDirection.UP ? 0x8 : 0x0);
	    			switch(EnumDirection.getPlayerDirection())
	    			{
	    				case SOUTH: return 0 | flip;
	    				case WEST:  return 3 | flip;
						case NORTH: return 1 | flip;
						case EAST:  return 2 | flip;
						default:
	    			}
	    			break;
	    		}
    		case VINE:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 1;
    				case WEST:  return 2;
					case NORTH: return 4;
					case EAST:  return 8;
					default:
    			}
    			break;
    		case FENCE_GATE:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 0;
    				case WEST:  return 1;
					case NORTH: return 2;
					case EAST:  return 3;
					default:
    			}
    			break;
    		case TRIPWIRE_HOOK:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 3;
					case NORTH: return 0;
					case EAST:  return 1;
					default:
    			}
    			break;
    		case HEAD:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				case SOUTH: return 2;
    				case WEST:  return 4;
					case NORTH: return 3;
					case EAST:  return 5;
					default:
    			}
    			break;
    		case ANVIL:
    			switch(EnumDirection.getPlayerDirection())
    			{
    				
    				case WEST:  return 0;
					case NORTH: return 1;
					case EAST:  return 2;
					case SOUTH: return 3;
					default:
    			}
    			break;
    			
    		
		default:
			break;
    		
    	}
    	
    	return block.getMeta();
    }
    
    /** Returns the itemID that is used in the inventory **/
    public static int getItemID(BasicBlock block)
    {
    	if (block.blockID == CROPS.id) {
			return 295;
		} else if (block.blockID == SIGN_POST.id
				|| block.blockID == WALL_SIGN.id) {
			return 323;
		} else if (block.blockID == WOODEN_DOOR.id) {
			return 324;
		} else if (block.blockID == IRON_DOOR.id) {
			return 330;
		} else if (block.blockID == SUGAR_CANE.id) {
			return 338;
		} else if (block.blockID == BED.id) {
			return 355;
		} else if (block.blockID == REDSTONE_REPEATER_ON.id
				|| block.blockID == REDSTONE_REPEATER_OFF.id) {
			return 356;
		} else if (block.blockID == PUMPKIN_STEM.id) {
			return 361;
		} else if (block.blockID == MELON_STEM.id) {
			return 362;
		} else if (block.blockID == NETHER_WART.id) {
			return 372;
		} else if (block.blockID == BREWING_STAND.id) {
			return 379;
		} else if (block.blockID == CAULDRON.id) {
			return 380;
		} else if (block.blockID == HEAD.id) {
			return 397;
		} else if (block.blockID == REDSTONE_COMPARATOR_ON.id
				|| block.blockID == REDSTONE_COMPARATOR_OFF.id) {
			return 404;
		} else {
			return block.blockID;
		}
			
    }
    
    /** Returns the metadata of a block stripped of orientation data */
    public static int removeOrientationFromMeta(int id, int meta)
    {
    	if( id == LOG.id ||
    		id == LOG_ACACIA.id) {
    		return (meta & 3);
    	}
    	else if(id == STEP.id || 
    			id == WOODEN_STEP.id ||
    			id == DOUBLE_STEP.id || 
    			id == HEAD.id) {
    		return (meta & 7);
    	}
    	else if(needsMetaForItem(id))
    	{
    		return meta;
    	}
    	return 0;
    }

	public static final HashMap<Integer,Integer> metaToSide = new HashMap<Integer,Integer>();
    static
    {
    	
    }
    
}
