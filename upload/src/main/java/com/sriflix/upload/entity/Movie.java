package com.sriflix.upload.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Movie")
public class Movie {
    @Id @Column(name = "imdbID")
    String imdbId ;
    @Column(name = "Title")
    String title;
    @Column(name = "Year")
    String year;
    @Column(name="Released")
    String released ;
    @Column(name="Runtime")
    String runtime;
    @Column(name="Genre")
    String genre;
    @Column(name="Actors")
    String actors;
    @Column(name="Plot")
    String plot;
    @Column(name="Country")
    String country;
    @Column(name="Poster")
    String poster;
    @Column(name="imdbrating")
    String imdbRating;
    @Column(name="CDNPath")
    String cdnPath;
    @Column(name="Backdrop")
    String backdrop;

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbRatings() {
        return imdbRating;
    }

    public void setImdbRatings(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getCdnPath() {
        return cdnPath;
    }

    public void setCdnPath(String cdnPath) {
        this.cdnPath = cdnPath;
    }
}
