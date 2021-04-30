package net.elicodes.bot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.elicodes.bot.Main;
import net.elicodes.bot.bot.Bot;
import net.elicodes.bot.controllers.BotController;
import net.elicodes.bot.models.StaffUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class BukkitEvents extends ListenerAdapter implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(PlayerLoginEvent e) {
        if (Bot.jda == null) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Main.getPlugin().getConfig().getString("Messages.nao-iniciou").replace("&", "§"));
        }
    }

    @EventHandler
    void evento(PlayerQuitEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            staffUser.setAutenticado(false);
            if(staffUser.getTask() != null) {
                staffUser.getTask().cancel();
                staffUser.setTask(null);
            }
        }
    }

    @EventHandler
    void evento(PlayerMoveEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setTo(e.getFrom());
        }
    }

    @EventHandler
    void evento(AsyncPlayerChatEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler
    void evento(PlayerCommandPreprocessEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) {
                e.setCancelled(true);
                System.out.println("aaa");
            }
        }
    }

    @EventHandler
    void evento(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if(Main.getPlugin().getController().isStaffUser(player.getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(player.getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler
    void evento(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) return;
        Player player = (Player) e.getDamager();
        if(Main.getPlugin().getController().isStaffUser(player.getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(player.getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(PlayerInteractAtEntityEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(PlayerInteractEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(PlayerInteractEntityEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(BlockBreakEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    void evento(BlockPlaceEvent e) {
        if(Main.getPlugin().getController().isStaffUser(e.getPlayer().getName())) {
            StaffUser staffUser = Main.getPlugin().getController().getStaffUser(e.getPlayer().getName());
            if(!staffUser.isAutenticado()) e.setCancelled(true);
        }
    }
}


