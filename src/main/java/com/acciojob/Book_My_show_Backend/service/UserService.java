package com.acciojob.Book_My_show_Backend.service;

import com.acciojob.Book_My_show_Backend.Repository.UserRepository;
import com.acciojob.Book_My_show_Backend.Requests.AddUserRequest;
import com.acciojob.Book_My_show_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String addUser(AddUserRequest userRequest){

        User user = User.builder().age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .name(userRequest.getName())
                .mobileNo(userRequest.getMobileNo())
                .build();

        user = userRepository.save(user);
        return "User has been saved to the Db with userId "+user.getUserId();
    }
}
