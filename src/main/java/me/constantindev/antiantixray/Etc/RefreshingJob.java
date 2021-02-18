package me.constantindev.antiantixray.Etc;

import me.constantindev.antiantixray.GUI.ProgressBar;

public class RefreshingJob {
    public ProgressBar progress;
    public Runner refresher;
    public Thread runner;

    public RefreshingJob(Runner refresher) {
        this.refresher = refresher;
        this.progress = refresher.progressBar;
        this.runner = new Thread(refresher);
        this.runner.start();

    }

    public void cancel() {
        RenderHelper.queue.clear();
        refresher.isRunning = false;
        this.progress.done = true;
    }
}
