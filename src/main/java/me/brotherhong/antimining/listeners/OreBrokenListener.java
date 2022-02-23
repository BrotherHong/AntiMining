package me.brotherhong.antimining.listeners;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Permissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class OreBrokenListener extends MyListener {

    public OreBrokenListener(AntiMining plugin) {
        super(plugin);
    }

    @EventHandler
    public void onOreBreak(BlockBreakEvent event) {
        if (!isOre(event.getBlock().getType()))
            return;
        if (!isTargetWorld(event.getBlock().getWorld().getName()))
            return;
        Player player = event.getPlayer();
        if (player.hasPermission(Permissions.OP))
            return;
        event.setDropItems(false);
        event.setExpToDrop(0);
        messages.sendWarning(player);
    }

    private boolean isTargetWorld(String worldName) {
        return config.getTargetWorld().contains(worldName);
    }

    private boolean isOre(Material type) {
        return config.getDisabledOre().contains(type);
    }
}
