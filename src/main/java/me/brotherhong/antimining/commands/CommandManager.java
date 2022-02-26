package me.brotherhong.antimining.commands;

import me.brotherhong.antimining.AntiMining;
import me.brotherhong.antimining.Messages;
import me.brotherhong.antimining.commands.subcommands.AddMaterialCommand;
import me.brotherhong.antimining.commands.subcommands.ReloadCommand;
import me.brotherhong.antimining.commands.subcommands.RemoveMaterialCommand;
import me.brotherhong.antimining.configs.Config;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager implements TabExecutor {

    private AntiMining plugin;
    private Config config;
    private Messages messages;

    private static List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(AntiMining plugin) {
        this.plugin = plugin;
        this.config = plugin.getMyConfig();
        this.messages = plugin.getMessages();

        subCommands.add(new ReloadCommand(plugin));
        subCommands.add(new AddMaterialCommand(plugin));
        subCommands.add(new RemoveMaterialCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return true;
        }
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        for (SubCommand subCommand : subCommands) {
            if (subCommand.getName().equalsIgnoreCase(args[0])) {
                if (!subCommand.hasPermission(player)) {
                    messages.sendNoPermission(player);
                    return true;
                }
                subCommand.execute(player, args);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (SubCommand subCommand : subCommands) {
                String commandName = subCommand.getName();
                if (commandName.startsWith(args[0])) {
                    result.add(commandName);
                }
            }
            return result;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                result.add("<Material>");
            }
            else if (args[0].equalsIgnoreCase("remove")) {
                result = getSimilarMaterial(args[1]);
            }
            return result;
        }
        return null;
    }

    private List<String> getSimilarMaterial(String material) {
        return config.getDisabledOre().stream()
                .map(Material::name)
                .filter(name -> name.startsWith(material.toUpperCase()))
                .collect(Collectors.toList());
    }
}
