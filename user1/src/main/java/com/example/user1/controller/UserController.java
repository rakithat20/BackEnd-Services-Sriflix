package com.example.user1.controller;

import com.example.user1.data.Liked;
import com.example.user1.data.Movie;
import com.example.user1.data.User;
import com.example.user1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/submit")
    public User submitUserDetails(User user) {
       return userService.addUser(user);
    }
    @GetMapping("/count")
    public long getUserCount(){
        //return userService.getUserCount();
        return 8123323;
    }
    @GetMapping("/isadmin/{email}")
    public boolean isAdmin(@PathVariable String email){
        return userService.isAdmin(email);
    }
    @GetMapping("/getliked/{id}")
    public List<Movie> getLikedByUser(@PathVariable int id){
        return  userService.getLikedMovies(id);
    }
    @PostMapping("/addtoliked")
    public Liked addLikedMovie(@RequestBody Liked liked){
        return userService.addLiked(liked);
    }
    @PostMapping("/login")
    public User login(String email ,String password){
        return userService.login(email,password);
    }
}
