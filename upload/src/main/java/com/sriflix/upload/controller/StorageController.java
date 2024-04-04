package com.sriflix.upload.controller;

import com.sriflix.upload.entity.Movie;
import com.sriflix.upload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Video")
@CrossOrigin(origins = "http://localhost:3000")

public class StorageController {
    @Autowired
    private StorageService storageService;
    @GetMapping(path = "/movies") // To display all Movies
    public List<Movie> getAllMovies(){
        return storageService.getAllMovies();
    }
    @PostMapping(path = "/upload") // To upload movies (Admin only)
    public ResponseEntity<String> uploadVideo(
            @RequestParam(value = "video") MultipartFile file,
            @RequestPart("data") String data

    ){
        return new ResponseEntity<>(storageService.uploadVideo(file,data), HttpStatus.OK);
    }
    @GetMapping(path = "/search/{title}")//To get movie by title
    public List<Movie> getBytitle(@PathVariable String title){
       return storageService.getByTitle(title);
    }
    @GetMapping(path = "/watch/{imdbID}")//To get movie by imdb id
    public Optional<Movie> getById(@PathVariable String imdbID) {
        return storageService.getById(imdbID);
    }
    @GetMapping(path = "/toprated")//To get top movies
    public List<Movie> getTopRated(){
        return storageService.getTopRated();
    }
    @GetMapping(path = "/genre/{genre}")//Categorize by genre
    public List<Movie>getByGenre(@PathVariable String genre){
        return storageService.getByGenre(genre);
    }
    @DeleteMapping(path = "delete/{name}")// Delete movie (Admin only)
    public ResponseEntity<String> deleteFile(@PathVariable String name){
        return new ResponseEntity<>(storageService.deleteVideo(name),HttpStatus.OK);
    }
}
