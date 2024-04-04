package com.example.user1.data;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "likedfilms")
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imdbid")
    private String imdbid;
    @Column(name = "userid")
    private int userid;
    @Column(name = "likedat")
    private Timestamp likedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }
}
