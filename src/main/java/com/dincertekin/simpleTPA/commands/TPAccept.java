package com.dincertekin.simpleTPA.commands;

import com.dincertekin.simpleTPA.SimpleTPA;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAccept implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                if (TPA.TPARequest.containsKey(sender)) {
                    Player player = (Player) sender;
                    Player target = TPA.TPARequest.get(player);

                    player.teleport(target);
                    player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 10, 1);

                    String title = String.format(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("tpa-title")));
                    String subtitle = String.format(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("tpa-subtitle")), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                    target.sendTitle(title, subtitle, 10, 30, 10);

                    TPA.TPARequest.remove(player);
                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("dont-have-request")));
            }
        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("only-players")));
        return false;
    }
}
