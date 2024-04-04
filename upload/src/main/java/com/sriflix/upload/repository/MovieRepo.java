package com.sriflix.upload.repository;

import com.sriflix.upload.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie,String> {
    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE %:title%")
    List<Movie> getByTitle(String title);
    @Query("SELECT m FROM Movie m WHERE LOWER(m.genre) LIKE %:genre%")
    List<Movie> getByGenre(String genre);
}
