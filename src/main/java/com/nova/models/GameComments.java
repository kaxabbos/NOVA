package com.nova.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameComments {

    @Id
    private long gameid;
    private String username;
    private String date;
    private String comment;

    public GameComments() {
    }

    public GameComments(long gameid, String username, String date, String comment) {
        this.gameid = gameid;
        this.username = username;
        this.date = date;
        this.comment = comment;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
