package me.constantindev.antiantixray.Commands;

import me.constantindev.antiantixray.Etc.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SetDelay extends Base {
    public SetDelay() {
        super("SetDelay", new String[]{"setdelay", "sdelay", "delay", "d"}, "Sets the delay between packets sent");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a number as argument."), false);
            return;
        }
        String delay = args[1];
        long delayI;
        try {
            delayI = Long.parseLong(delay);
        } catch (Exception ex) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Please provide a VALID number as argument."), false);
            return;
        }
        Config.delay = delayI;
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.of("[AAX] Set new delay to " + delayI), false);
        super.run(args);
    }
}
