package me.brotherhong.antimining;

import me.brotherhong.antimining.commands.CommandManager;
import me.brotherhong.antimining.configs.Config;
import me.brotherhong.antimining.listeners.OreBrokenListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiMining extends JavaPlugin {

    private Config config;
    private Messages messages;

    public AntiMining() {
        config = new Config(this);
        messages = new Messages(this);
    }

    @Override
    public void onEnable() {
        getCommand("antimining").setExecutor(new CommandManager(this));
        getServer().getPluginManager().registerEvents(new OreBrokenListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Config getMyConfig() {
        return config;
    }

    public Messages getMessages() {
        return messages;
    }
}
