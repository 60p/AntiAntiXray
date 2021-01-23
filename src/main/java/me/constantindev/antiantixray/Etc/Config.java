package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.Commands.Manager;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class Config {
    public static int rad = 5;
    public static long delay = 10;
    public static Manager manager = new Manager();
    public static boolean scanAll = false;
    public static boolean auto = false;
    public static int mtreshold = 5;
    public static Block[] checkblocks = {Blocks.OBSIDIAN, Blocks.CLAY, Blocks.MOSSY_COBBLESTONE,
            Blocks.DIAMOND_ORE, Blocks.REDSTONE_ORE, Blocks.IRON_ORE, Blocks.COAL_ORE, Blocks.LAPIS_ORE,
            Blocks.GOLD_ORE, Blocks.EMERALD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE};
    public static int kcScan = GLFW.GLFW_KEY_Y;
    public static int kcRemove = GLFW.GLFW_KEY_V;


}


