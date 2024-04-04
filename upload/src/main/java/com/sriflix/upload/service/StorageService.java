package com.sriflix.upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sriflix.upload.entity.Movie;
import com.sriflix.upload.repository.MovieRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service @Slf4j
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${distribution.domain}")
    private String cdnDomain;
    @Autowired
    private AmazonS3 s3Client;
    @Autowired
    private MovieRepo movieRepo;
    @PersistenceContext
    private EntityManager entityManager;
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }
    public String uploadVideo(MultipartFile file, String data ){
        File fileObj = convertMultiPartFileToFile(file);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> jsonData = objectMapper.readValue(data, new TypeReference<Map<String, String>>() {});

            // Step 2: Create a new Movie object and set its properties
            Movie movie = new Movie();
            movie.setImdbId(jsonData.get("imdbID"));
            movie.setTitle(jsonData.get("Title"));
            movie.setYear(jsonData.get("Year"));
            movie.setReleased(jsonData.get("Released"));
            movie.setRuntime(jsonData.get("Runtime"));
            movie.setGenre(jsonData.get("Genre"));
            movie.setActors(jsonData.get("Actors"));
            movie.setPlot(jsonData.get("Plot"));
            movie.setCountry(jsonData.get("Country"));
            movie.setPoster(jsonData.get("Poster"));
            movie.setImdbRatings(jsonData.get("imdbRating"));
            movie.setCdnPath(cdnDomain + "/" + jsonData.get("imdbID")); // Assuming cdnDomain is defined somewhere
            movie.setBackdrop(jsonData.get("Backdrop"));

            // Step 3: Perform operations with the movie object (e.g., save to S3 and database)
            s3Client.putObject(bucketName, movie.getImdbId(), fileObj);
            fileObj.delete();
            movieRepo.save(movie);

            return "File Upload Successful!";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Error processing JSON data.";
        }

       // System.out.println(Data);


    }
    public List<Movie> getByTitle(String title){
        return movieRepo.getByTitle(title);
    }
    public Optional<Movie> getById(String imdbID){
        return movieRepo.findById(imdbID);
//        System.out.println(newMovie.getTitle());
//        return newMovie;

    }
    public List<Movie> getTopRated(){
        Sort sortByRating = Sort.by(Sort.Direction.DESC, "imdbRating");
        return movieRepo.findAll(sortByRating);
    }
    public List<Movie> getByGenre(String genre){
        return movieRepo.getByGenre(genre);
    }
    public String deleteVideo(String name){
        List <Movie> movies = movieRepo.getByTitle(name);
        Movie movie = movies.getFirst();
        System.out.println(movie.getImdbId());
        s3Client.deleteObject(bucketName,movie.getImdbId());
        movieRepo.deleteById(movie.getImdbId());
        return movie.getImdbId() + " Deleted succesfully";
    }
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}


