package com.example.user1.service;

import com.example.user1.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private LikedRepository likedRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public long getUserCount(){
        return userRepository.count();
    }
    public User login(String email, String password) {
        User user = userRepository.getByMail(email);

        if (user != null && user.getMail().equals(email) && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }


    public boolean isAdmin(String email){
        User user = userRepository.getByMail(email);
        System.out.println(user.getRole());
        return user != null && "admin".equals(user.getRole());
    }

    public List<Movie> getLikedMovies(int id){
        List<Liked> likedMovies = likedRepository.getLikedByUser(id);
        List<Movie> movieList = likedMovies.stream()
                .map(this::getLikedFromUpload)
                .collect(Collectors.toList());

        for (Movie movie : movieList) {
            System.out.println(movie.getTitle());
        }

        return movieList;
    }

    public Liked addLiked(Liked liked){
        liked.setLikedAt(Timestamp.valueOf(LocalDateTime.now()));
        return likedRepository.save(liked);
    }

    private Movie getLikedFromUpload(Liked liked){
        System.out.println(liked.getImdbid());
        String movieServiceUrl = discoveryClient.getInstances("movie").get(0).getUri().toString()+"/sriflix/Video/watch/"+liked.getImdbid();
        String response = restTemplate.getForObject(movieServiceUrl, String.class);
        System.out.println("Response from movie service: " + response);
        return restTemplate.getForObject(movieServiceUrl,Movie.class);
    }
}