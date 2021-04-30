package net.elicodes.bot.commands;

import net.elicodes.bot.Main;
import net.elicodes.bot.models.StaffUser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BotLoginCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lb, String[] a) {
        if (!s.hasPermission(Main.getPlugin().getConfig().getString("Permissao do comando"))) {
            s.sendMessage(Main.getPlugin().getConfig().getString("Messages.no-permission").replace("&", "§"));
            return true;
        }
        if (a.length < 3) {
            s.sendMessage("§r ");
            s.sendMessage("     §e§lAJUDA     ");
            s.sendMessage("§r ");
            s.sendMessage(" §a/botlogin autenticar <player> <senha>.");
            s.sendMessage(" §a/botlogin desautenticar <player> <senha>.");
            s.sendMessage("§r ");
            return true;
        }

        if (a[0].equalsIgnoreCase("autenticar")) {
            Player target = Bukkit.getPlayerExact(a[1]);
            if (target == null) {
                s.sendMessage(Main.getPlugin().getConfig().getString("Messages.player-not-exists").replace("&", "§"));
                return true;
            }
            if (Main.getPlugin().getController().isStaffUser(target.getName())) {
                StaffUser staffUser = Main.getPlugin().getController().getStaffUser(target.getName());
                if (!staffUser.isLogado()) {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.not-logged").replace("&", "§"));
                    return true;
                }
                String senha = Main.getPlugin().getConfig().getString("Senha");
                String senhaInserida = a[2];
                if (!senhaInserida.equals(senha)) {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.invalid-password").replace("&", "§"));
                    return true;
                }
                if (!staffUser.isAutenticado()) {
                    target.sendMessage(Main.getPlugin().getConfig().getString("Messages.autenticado").replace("&", "§"));
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.cmd-autenticado").replace("@player", target.getName()).replace("&", "§"));
                } else {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.already-autenticado").replace("&", "§"));
                }
            } else {
                s.sendMessage(Main.getPlugin().getConfig().getString("Messages.player-not-staff").replace("&", "§"));
            }
        } else if (a[0].equalsIgnoreCase("desautenticar")) {
            Player target = Bukkit.getPlayerExact(a[1]);
            if (target == null) {
                s.sendMessage(Main.getPlugin().getConfig().getString("Messages.player-not-exists").replace("&", "§"));
                return true;
            }
            if(Main.getPlugin().getController().isStaffUser(target.getName())) {
                StaffUser staffUser = Main.getPlugin().getController().getStaffUser(target.getName());
                if (!staffUser.isLogado()) {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.not-logged").replace("&", "§"));
                    return true;
                }
                String senha = Main.getPlugin().getConfig().getString("Senha");
                String senhaInserida = a[2];
                if (!senhaInserida.equals(senha)) {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.invalid-password").replace("&", "§"));
                    return true;
                }
                if (staffUser.isAutenticado()) {
                    target.kickPlayer(Main.getPlugin().getConfig().getString("Messages.desautenticado").replace("&", "§"));
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.cmd-desautenticado").replace("@player", target.getName()).replace("&", "§"));
                } else {
                    s.sendMessage(Main.getPlugin().getConfig().getString("Messages.already-not-autenticado").replace("&", "§"));
                }
            }else {
                s.sendMessage(Main.getPlugin().getConfig().getString("Messages.player-not-staff").replace("&", "§"));
            }
        } else {
            s.sendMessage("§r ");
            s.sendMessage("     §e§lAJUDA     ");
            s.sendMessage("§r ");
            s.sendMessage(" §a/botlogin logar <player> <senha>.");
            s.sendMessage(" §a/botlogin deslogar <player> <senha>.");
            s.sendMessage("§r ");
        }
        return false;
    }
}
