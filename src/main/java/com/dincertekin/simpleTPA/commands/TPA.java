package com.dincertekin.simpleTPA.commands;

import com.dincertekin.simpleTPA.SimpleTPA;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TPA implements CommandExecutor {

    public static HashMap<Player, Player> TPARequest = new HashMap<Player, Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("command-usage")));
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("player-not-found")));
                    return true;
                }

                if (TPARequest.containsKey(target)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("already-requested")));
                    return true;
                }

                TPARequest.put(target, player);
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("tpa-request").replaceAll("%player%", player.getName())));
                target.getWorld().playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1);

                TextComponent acceptButton = new TextComponent(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("accept-button-text")));
                // acceptButton.setColor(ChatColor.GREEN.asBungee());
                // acceptButton.setBold(true);
                acceptButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));
                acceptButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("accept-button-hover"))).create()));

                TextComponent space = new TextComponent(" ");

                TextComponent denyButton = new TextComponent(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("deny-button-text")));
                // denyButton.setColor(ChatColor.RED.asBungee());
                // denyButton.setBold(true);
                denyButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));
                denyButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("deny-button-hover"))).create()));

                TextComponent message = new TextComponent();
                message.addExtra(acceptButton);
                message.addExtra(space);
                message.addExtra(denyButton);

                target.spigot().sendMessage(message);
            }
        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleTPA.getInstance().getConfig().getString("only-players")));
        return false;
    }
}
