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

    private String
            name,
            dev,
            pub,
            os,
            proc,
            videocard,
            sound,
            description,
            file;

    private String poster;
    private String[] screenshots;

    private int
            year,
            ram,
            place,
            count;

    private GBMB switchram, switchplace;

    private float version, price, income;

    private Genre genre;
    private Language language;

    public Games() {
    }

    public Games(
            String name, String dev, String pub,
            int year, float version, float price, Genre genre, Language language, String os,
            String proc, String videocard, String sound, int ram, int place,
            GBMB switchram, GBMB switchplace, String file
    ) {
        this.name = name;
        this.dev = dev;
        this.pub = pub;
        this.year = year;
        this.version = version;
        this.price = price;
        this.genre = genre;
        this.language = language;
        this.os = os;
        this.proc = proc;
        this.videocard = videocard;
        this.sound = sound;
        this.ram = ram;
        this.place = place;
        this.switchram = switchram;
        this.switchplace = switchplace;
        this.file = file;
        this.count = 0;
        this.income = 0;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String[] getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String[] screenshots) {
        this.screenshots = screenshots;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }
}
