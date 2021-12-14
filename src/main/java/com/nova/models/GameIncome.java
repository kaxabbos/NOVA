package com.nova.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameIncome {
    @Id
    private long gameid;

    private long userid;
    private String gamename;
    private float income, price;
    private int count;

    public GameIncome() {
    }

    public GameIncome(String gamename, float price) {
        this.gamename = gamename;
        this.price = price;
        this.income = 0;
        this.count = 0;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
