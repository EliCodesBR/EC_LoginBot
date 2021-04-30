package net.elicodes.bot.bot;

import net.dv8tion.jda.api.entities.Activity;
import net.elicodes.bot.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.elicodes.bot.listeners.BotEvents;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;

    @Deprecated
    public static void load() {
        try {
            JDABuilder jdab = JDABuilder.createDefault(Main.getPlugin().getConfig().getString("Bot.token"));
            jdab.setAutoReconnect(true);
            jdab.setActivity(Activity.playing(Main.getPlugin().getConfig().getString("Messages.activity.initing").replace("@players", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("@max_players", String.valueOf(Bukkit.getMaxPlayers()))));
            jdab.addEventListeners(new BotEvents());
            jdab.setActivity(Activity.playing(Main.getPlugin().getConfig().getString("Messages.activity.init").replace("@players", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("@max_players", String.valueOf(Bukkit.getMaxPlayers()))));
            jda = jdab.build();
        } catch (LoginException e) {
            Bukkit.getConsoleSender().sendMessage("§c[EC_BotLogin] Não foi possível conectar ao bot, desligando o plugin...");
            Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());
        }
        /*try {
            jda = new JDABuilder(Main.getPlugin().getConfig().getString("Bot.token")).build();
            jda.setAutoReconnect(true);
            jda.getPresence().setActivity(Activity.playing(Main.getPlugin().getConfig().getString("Messages.activity.initing").replace("@players", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("@max_players", String.valueOf(Bukkit.getMaxPlayers()))));
            jda.addEventListener(new BotEvents());
            jda.getPresence().setActivity(Activity.playing(Main.getPlugin().getConfig().getString("Messages.activity.init").replace("@players", String.valueOf(Bukkit.getOnlinePlayers().size())).replace("@max_players", String.valueOf(Bukkit.getMaxPlayers()))));
        } catch (LoginException e) {
            Bukkit.getConsoleSender().sendMessage("§c[EC_BotLogin] Não foi possível conectar ao bot, desligando o plugin...");
            Main.getPlugin().getPluginLoader().disablePlugin(Main.getPlugin());
        }*/
    }
}
