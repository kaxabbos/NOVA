package com.nova.models;

import com.nova.models.enums.Genre;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    main
    private long userid;
    private String name, poster;
    private int year;
    private float price;
    private Genre genre;

    public Games() {
    }

    public Games(String name, Genre genre) {
        this.name = name;
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
}
