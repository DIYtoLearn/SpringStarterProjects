package com.upayan.firstspring.Controller;
import com.upayan.firstspring.Model.Users;
import com.upayan.firstspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    @Autowired
    private UserRepository userRepo ;


    @PostMapping("v1/AddUser")
    Users us1(@RequestBody Users us1){
        return userRepo.save(us1);
    }


    @GetMapping("v1/UserData")
    List<Users> getAllUsers(){
        return userRepo.findAll();
    }

}