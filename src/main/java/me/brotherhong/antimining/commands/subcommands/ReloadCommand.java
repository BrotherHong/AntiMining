package me.brotherhong.antimining.commands.subcommands;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Permissions;
import me.brotherhong.antimining.commands.SubCommand;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    public ReloadCommand(AntiMining plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload this plugin.";
    }

    @Override
    public String getUsage() {
        return "/am reload";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.OP);
    }

    @Override
    public void execute(Player player, String[] args) {
        config.reload();
        messages.sendReload(player);
    }
}
