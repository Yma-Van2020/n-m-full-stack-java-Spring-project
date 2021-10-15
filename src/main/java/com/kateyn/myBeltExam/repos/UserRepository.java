package com.kateyn.myBeltExam.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kateyn.myBeltExam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
//	List<User> findAllByUsers(User user);
//	
//	List<User> findByUserNotContains(User user);
}