package me.brotherhong.antimining.commands.subcommands;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Permissions;
import me.brotherhong.antimining.commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveMaterialCommand extends SubCommand {

    public RemoveMaterialCommand(AntiMining plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove a material from disabled list";
    }

    @Override
    public String getUsage() {
        return "/am remove <Material>";
    }

    @Override
    public boolean hasPermission(Player player) {
        return player.hasPermission(Permissions.OP);
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 2) {
            messages.send(player, getUsage());
            return;
        }

        String matName = args[1];
        Material mat = Material.matchMaterial(matName);

        if (mat == null) {
            messages.sendMaterialNotFound(player);
            return;
        }

        if (!isMaterialDisabled(mat)) {
            messages.sendNotInList(player);
            return;
        }

        removeMaterial(mat);
        messages.sendSuccessRemove(player, mat.name());
    }

    private void removeMaterial(Material material) {
        List<Material> matList = config.getDisabledOre();
        matList.remove(material);

        config.getConfig().set("disabled-ore", getNameList(matList));
        config.saveAndReload();
    }

    private List<String> getNameList(List<Material> list) {
        return list.stream().map(Material::name).collect(Collectors.toList());
    }

    private boolean isMaterialDisabled(Material material) {
        return config.getDisabledOre().contains(material);
    }
}
