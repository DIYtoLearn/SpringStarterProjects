package com.upayan.firstspring.Repository;

import com.upayan.firstspring.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {


}
