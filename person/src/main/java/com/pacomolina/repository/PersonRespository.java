package com.pacomolina.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pacomolina.entity.User;

import java.util.Optional;

@Repository
public interface PersonRespository extends JpaRepository<User, Integer> {


	 Optional<User> findByusername(String username);
	    boolean existsByusername(String username);
	    boolean existsByEmail(String email);
    


}
