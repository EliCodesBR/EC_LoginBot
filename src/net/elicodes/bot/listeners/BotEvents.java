package net.elicodes.bot.listeners;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.elicodes.bot.Main;
import net.elicodes.bot.controllers.BotController;
import net.elicodes.bot.models.StaffUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class BotEvents extends ListenerAdapter implements Listener {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        User user = e.getUser();
        if (user == null || user.isBot()) return;
        if (Main.getPlugin().getController().isStaffUserByID(user.getId()) && BotController.messages.containsKey(e.getMessageId()) && BotController.messages.get(e.getMessageId()).contains(e.getUserId())) {
            String emoji = e.getReactionEmote().getEmoji();
            String userID = e.getUserId();

            if(!BotController.messages.containsKey(e.getMessageId())) return;
            if (BotController.messages.get(e.getMessageId()).contains(userID)) {
                StaffUser staffUser = Main.getPlugin().getController().getStaffUserByID(userID);
                if(!staffUser.getUserID().contains(userID)) return;
                Player player = Bukkit.getPlayerExact(staffUser.getName());
                if(player == null) return;
                if (player.isOnline()) {
                    switch (emoji) {
                        case "❌":
                            if (!staffUser.isAutenticado() && staffUser.isLogado()) {
                                BotController.messages.remove(e.getMessageId());
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        player.kickPlayer(Main.getPlugin().getConfig().getString("Messages.rejeitou").replace("&", "§"));
                                    }
                                }.runTaskLater(Main.getPlugin(), 5L);
                                e.getTextChannel().deleteMessageById(e.getMessageId()).complete();
                                staffUser.setLogado(false);
                            }
                            break;
                        case "✅":
                            if (!staffUser.isAutenticado()) {
                                Main.getPlugin().getController().autenticar(staffUser);
                                BotController.messages.remove(e.getMessageId());
                                e.getTextChannel().deleteMessageById(e.getMessageId()).complete();
                                player.getPlayer().sendMessage(Main.getPlugin().getConfig().getString("Messages.autenticado").replace("&", "§"));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
