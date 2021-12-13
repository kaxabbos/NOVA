package com.nova.models;

import com.nova.models.enums.GBMB;
import com.nova.models.enums.Genre;
import com.nova.models.enums.Language;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Games() {
    }

    public Games(String name, int year, float price, Genre genre) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userId) {
        this.userid = userId;
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

    public void addDescription(GameDescription gd){
        dev = gd.getDev();
        pub = gd.getPub();
        os = gd.getOs();
        proc = gd.getProc();
        videocard = gd.getVideocard();
        sound = gd.getSound();
        description = gd.getDescription();
        file = gd.getFile();
        ram = gd.getRam();
        place = gd.getPlace();
        version = gd.getVersion();
        screenshots = gd.getScreenshots();
        switchram = gd.getSwitchram();
        switchplace = gd.getSwitchplace();
        language = gd.getLanguage();
    }

    public String getDev() {
        return dev;
    }

    public String getPub() {
        return pub;
    }

    public String getOs() {
        return os;
    }

    public String getProc() {
        return proc;
    }

    public String getVideocard() {
        return videocard;
    }

    public String getSound() {
        return sound;
    }

    public String getDescription() {
        return description;
    }

    public String getFile() {
        return file;
    }

    public int getRam() {
        return ram;
    }

    public int getPlace() {
        return place;
    }

    public float getVersion() {
        return version;
    }

    public String[] getScreenshots() {
        return screenshots;
    }

    public GBMB getSwitchram() {
        return switchram;
    }

    public GBMB getSwitchplace() {
        return switchplace;
    }

    public Language getLanguage() {
        return language;
    }
}
