package me.brotherhong.antimining.listeners;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Messages;
import me.brotherhong.antimining.configs.Config;
import org.bukkit.event.Listener;

public class MyListener implements Listener {

    protected AntiMining plugin;
    protected Config config;
    protected Messages messages;

    public MyListener(AntiMining plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }
}
