package net.elicodes.bot.controllers;

import net.elicodes.bot.Main;
import net.elicodes.bot.models.StaffUser;
import net.elicodes.bot.nms.Actionbar;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class BotController {

    public static HashMap<String, StaffUser> users = new HashMap<>();
    public static HashMap<String, StaffUser> usersByID = new HashMap<>();
    public static HashMap<String, String> messages = new HashMap<>();

    public static void loadUsers() {
        for (String s : Main.getPlugin().getConfig().getConfigurationSection("Users").getKeys(false)) {
            StaffUser staffUser = new StaffUser(s, Main.getPlugin().getConfig().getString("Users." + s + ".user-id"));
            users.put(s, staffUser);
            usersByID.put(staffUser.getUserID(), staffUser);
        }
    }

    public void startTimer(StaffUser staffUser) {
        Player player = Bukkit.getPlayerExact(staffUser.getName());
        int seconds = Main.getPlugin().getConfig().getInt("Messages.time-autenticar.tempo");
        BukkitTask task = new BukkitRunnable() {
            int localSeconds = seconds;
            public void run() {
                if (localSeconds == 0) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            staffUser.setTask(null);
                            player.kickPlayer(Main.getPlugin().getConfig().getString("Messages.demorou").replace("&", "§"));
                        }
                    }.runTaskLater(Main.getPlugin(), 1L);
                    cancel();
                }else {
                    player.playSound(player.getLocation(), Sound.valueOf(Main.getPlugin().getConfig().getString("Messages.time-autenticar.sound")), 1.0F, 1.0F);
                    Actionbar.send(player, Main.getPlugin().getConfig().getString("Messages.time-autenticar.message").replace("[", "").replace("]", "").replace("@tempo", localSeconds + " segundo(s)").replace("&", "§"));
                    localSeconds--;
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(), 0L, 20L);
        staffUser.setTask(task);
    }

    public boolean isStaffUser(String name) {
        return users.containsKey(name);
    }

    public boolean isStaffUserByID(String userId) {
        return usersByID.containsKey(userId);
    }

    public void autenticar(StaffUser staffUser) {
        staffUser.setAutenticado(true);
        if(staffUser.getTask() != null) staffUser.getTask().cancel();
        staffUser.setTask(null);
    }

    public StaffUser getStaffUser(String name) {
        return users.get(name);
    }

    public StaffUser getStaffUserByID(String userId) {
        return usersByID.get(userId);
    }
}
