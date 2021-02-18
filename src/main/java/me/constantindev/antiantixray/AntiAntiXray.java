package me.constantindev.antiantixray;

import me.constantindev.antiantixray.Etc.*;
import me.constantindev.antiantixray.GUI.ProgressBar;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class AntiAntiXray implements ClientModInitializer {
    public static KeyBind remove = new KeyBind(Config.kcScan);
    public static KeyBind createGhosts = new KeyBind(Config.kcRemove);
    public static List<RefreshingJob> jobs = new ArrayList<>();

    public static void scanArea(int rad, long delayInMS) {
        ProgressBar progressBar = new ProgressBar();
        MinecraftClient.getInstance().getToastManager().add(progressBar);
        RefreshingJob rfj = new RefreshingJob(new Runner(rad, delayInMS, progressBar));
        jobs.add(rfj);
    }

    @Override
    public void onInitializeClient() {
        Logger.info("Loading and initializing AAX...");
    }
}
