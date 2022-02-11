package com.nova.models;

import com.nova.models.enums.GBMB;
import com.nova.models.enums.Genre;
import com.nova.models.enums.Language;

public class GameAll {
    private long id;

    private long userid;
    private String name, poster;
    private int year;
    private float price;
    private Genre genre;

    private String dev, pub, os, proc, videocard, sound, description, file;

    private int ram, place;

    private float version;
    private String[] screenshots;
    private GBMB switchram, switchplace;
    private Language language;

    public GameAll() {
    }

    public GameAll(Games g, GameDescription gd) {
        this.id = g.getId();
        this.userid = g.getUserid();
        this.name = g.getName();
        this.poster = g.getPoster();
        this.year = g.getYear();
        this.price = g.getPrice();
        this.genre = g.getGenre();

        this.version = gd.getVersion();
        this.dev = gd.getDev();
        this.pub = gd.getPub();
        this.os = gd.getOs();
        this.proc = gd.getProc();
        this.videocard = gd.getVideocard();
        this.sound = gd.getSound();
        this.description = gd.getDescription();
        this.file = gd.getFile();
        this.ram = gd.getRam();
        this.place = gd.getPlace();
        this.version = gd.getVersion();
        this.screenshots = gd.getScreenshots();
        this.switchram = gd.getSwitchram();
        this.switchplace = gd.getSwitchplace();
        this.language = gd.getLanguage();
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String[] getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String[] screenshots) {
        this.screenshots = screenshots;
    }

    public GBMB getSwitchram() {
        return switchram;
    }

    public void setSwitchram(GBMB switchram) {
        this.switchram = switchram;
    }

    public GBMB getSwitchplace() {
        return switchplace;
    }

    public void setSwitchplace(GBMB switchplace) {
        this.switchplace = switchplace;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
