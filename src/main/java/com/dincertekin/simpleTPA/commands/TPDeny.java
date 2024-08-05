package com.dincertekin.simpleTPA.commands;

import com.dincertekin.simpleTPA.SimpleTPA;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPDeny implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                if (TPA.TPARequest.containsKey(sender)) {
                    Player player = (Player) sender;
                    Player target = TPA.TPARequest.get(player);

                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("tpdeny").replaceAll("%player%", player.getName())));
                    target.playSound(target.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 10, 1);

                    TPA.TPARequest.remove(player);
                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("dont-have-request")));
            }
        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("only-players")));
        return false;
    }
}
