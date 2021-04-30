package net.elicodes.bot.models;

import org.bukkit.scheduler.BukkitTask;

public class StaffUser {

    private String name;
    private String userID;

    private boolean autenticado;
    private boolean logado;
    private BukkitTask task;

    public StaffUser(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean isLogado() {
        return logado;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }
}
