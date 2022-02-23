package me.brotherhong.antimining;

import me.brotherhong.antimining.configs.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    private AntiMining plugin;
    private String prefix;
    private Config config;

    public Messages(AntiMining plugin) {
        this.plugin = plugin;
        config = plugin.getMyConfig();
        prefix = trans(config.getPrefix()) + " ";
    }

    public static String trans(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.no-permission")));
    }

    public void sendWarning(Player player) {
        player.sendMessage(prefix + trans(config.getConfig().getString("messages.warning")));
    }

    public void sendReload(Player player) {
        player.sendMessage(prefix + trans("&aReload Complete."));
    }

}
