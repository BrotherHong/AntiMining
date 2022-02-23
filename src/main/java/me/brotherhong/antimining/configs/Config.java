package me.brotherhong.antimining.configs;

import me.brotherhong.antimining.AntiMining;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config extends ConfigManager {

    private String prefix;
    private List<String> targetWorld;
    private List<Material> disabledOre;

    public Config(AntiMining plugin) {
        super(plugin, "config.yml");
        load();
    }

    @Override
    protected void load() {
        prefix = getConfig().getString("prefix");
        targetWorld = getConfig().getStringList("anti-mining-world");
        disabledOre = getConfig().getStringList("disabled-ore")
                            .stream()
                            .map(d -> Material.getMaterial(d.toUpperCase()))
                            .collect(Collectors.toList());
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getTargetWorld() {
        return targetWorld;
    }

    public List<Material> getDisabledOre() {
        if (disabledOre == null) {
            disabledOre = new ArrayList<>();
        }
        return disabledOre;
    }
}
