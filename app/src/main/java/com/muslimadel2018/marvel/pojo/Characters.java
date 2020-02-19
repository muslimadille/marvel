package com.muslimadel2018.marvel.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Characters {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;

    @SerializedName("comics")
    @Expose
    private Lists comics;

    @SerializedName("series")
    @Expose
    private Lists series;

    @SerializedName("stories")
    @Expose
    private Lists stories;

    @SerializedName("events")
    @Expose
    private Lists events;

    public Lists getSeries() {
        return series;
    }

    public void setSeries(Lists series) {
        this.series = series;
    }

    public Lists getStories() {
        return stories;
    }

    public void setStories(Lists stories) {
        this.stories = stories;
    }

    public Lists getEvents() {
        return events;
    }

    public void setEvents(Lists events) {
        this.events = events;
    }


    public Lists getComics() {
        return comics;
    }

    public void setComics(Lists comics) {
        this.comics = comics;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
