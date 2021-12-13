package com.nova.models;

import com.nova.models.enums.GBMB;
import com.nova.models.enums.Language;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameDescription {
    @Id
    private long gameid;

    private String dev, pub, os, proc, videocard, sound, description, file;

    private int ram, place;

    private float version;
    private String[] screenshots;
    private GBMB switchram, switchplace;
    private Language language;

    public GameDescription() {
    }

    public GameDescription(
            String dev, String pub, String os, String proc,
            String videocard, String sound, String file,
            int ram, int place, float version,
            GBMB switchram, GBMB switchplace, Language language
    ) {
        this.dev = dev;
        this.pub = pub;
        this.os = os;
        this.proc = proc;
        this.videocard = videocard;
        this.sound = sound;
        this.file = file;
        this.ram = ram;
        this.place = place;
        this.version = version;
        this.switchram = switchram;
        this.switchplace = switchplace;
        this.language = language;
    }

    public long getGameid() {
        return gameid;
    }

    public void setGameid(long gameid) {
        this.gameid = gameid;
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
