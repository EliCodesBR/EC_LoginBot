package net.elicodes.bot;

import net.elicodes.bot.bot.Bot;
import net.elicodes.bot.commands.BotLoginCMD;
import net.elicodes.bot.controllers.BotController;
import net.elicodes.bot.listeners.AuthMeJoinEvent;
import net.elicodes.bot.listeners.BukkitEvents;
import net.elicodes.bot.listeners.OnJoinEvent;
import net.elicodes.bot.listeners.nLoginJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private BotController controller;

    public static Main getPlugin() {
        return Main.getPlugin(Main.class);
    }

    @Override
    @Deprecated
    public void onEnable() {
        saveDefaultConfig();
        Bot.load();
        controller = new BotController();
        BotController.loadUsers();
        Bukkit.getPluginManager().registerEvents(new BukkitEvents(), this);
        String pluginName = getConfig().getString("Plugin de login");
        switch (pluginName.toLowerCase()) {
            case "nLogin":
                if (pluginIsEnabled(pluginName)) {
                    Bukkit.getPluginManager().registerEvents(new nLoginJoinEvent(), this);
                } else {
                    Bukkit.getPluginManager().disablePlugin(this);
                }
                break;
            case "AuthMe":
                if (pluginIsEnabled(pluginName)) {
                    Bukkit.getPluginManager().registerEvents(new AuthMeJoinEvent(), this);
                } else {
                    Bukkit.getPluginManager().disablePlugin(this);
                }
                break;
            default:
                Bukkit.getPluginManager().registerEvents(new OnJoinEvent(), this);
                break;
        }
        getCommand("botlogin").setExecutor(new BotLoginCMD());
    }

    public boolean pluginIsEnabled(String pluginName) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            return false;
        } else {
            return plugin.isEnabled();
        }
    }

    public BotController getController() {
        return controller;
    }
}
