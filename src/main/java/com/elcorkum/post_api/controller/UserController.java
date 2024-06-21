package com.elcorkum.post_api.controller;


import com.elcorkum.post_api.entity.User;
import com.elcorkum.post_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * create a user
     * get user by id
     * get all users
     * update user
     * delete user
     */

    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getExistingUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        Iterable<User> allUsers = userService.getUsers();
        if(!allUsers.iterator().hasNext())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateExistingUser(@PathVariable Long userId, @RequestBody User user){
        User updatedUser = userService.updateUser(userId, user);
        if(updatedUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteExistingUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        User user = userService.getUser(userId);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
