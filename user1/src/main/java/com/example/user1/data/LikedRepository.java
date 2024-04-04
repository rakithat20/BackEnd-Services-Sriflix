package com.example.user1.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikedRepository extends JpaRepository<Liked,Integer> {
    @Query("SELECT l FROM Liked l WHERE l.userid = :userid ORDER BY l.likedAt DESC")
    List<Liked> getLikedByUser(@Param("userid")int userid);
}
