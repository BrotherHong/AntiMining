package me.brotherhong.antimining.commands;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Messages;
import me.brotherhong.antimining.configs.Config;
import org.bukkit.entity.Player;

public abstract class SubCommand {

    protected AntiMining plugin;
    protected Config config;
    protected Messages messages;

    public SubCommand(AntiMining plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract String getUsage();
    public abstract boolean hasPermission(Player player);
    public abstract void execute(Player player, String[] args);

}
