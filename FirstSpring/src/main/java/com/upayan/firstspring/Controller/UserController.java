package com.upayan.firstspring.Controller;
import com.upayan.firstspring.Exception.UserNotFoundException;
import com.upayan.firstspring.Model.Users;
import com.upayan.firstspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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

    @GetMapping("v1/UserData/{id}")
    Users getUserById(@PathVariable Integer id){
        return userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("v1/EditData/{id}")
    Users updateUser(@RequestBody Users us2,
                     @PathVariable Integer id) {

        return userRepo.findById(id)
                .map(user -> {

                    if (us2.getUserName() != null) {
                        user.setUserName(us2.getUserName());
                    }

                    if (us2.getEmailAddress() != null) {
                        user.setEmailAddress(us2.getEmailAddress());
                    }

                    return userRepo.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("v1/Delete/{id}")
    String DeleteUser(@PathVariable Integer id){
        if(!userRepo.existsById(id))
            throw new UserNotFoundException(id);

        userRepo.deleteById(id);
        return "User with "+id+" has been deleted ! \n data for User ";
    }
}