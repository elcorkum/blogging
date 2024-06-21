package com.elcorkum.post_api.service;


import com.elcorkum.post_api.entity.User;
import com.elcorkum.post_api.exception.ResourceNotFoundException;
import com.elcorkum.post_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long userId){
        verifyUser(userId);
        return userRepository.findById(userId).orElse(null);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User user){
        verifyUser(userId);
        for(User u: userRepository.findAll()){
           if( u.getId().equals(userId)){
               userRepository.save(user);
           }
        }
        return userRepository.findById(user.getId()).orElse(null);
    }

    public void deleteUser(Long userId){
        verifyUser(userId);
        userRepository.deleteById(userId);
    }


    protected void verifyUser(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new ResourceNotFoundException("User not found");
        }
    }
}
