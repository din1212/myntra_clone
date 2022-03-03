package com.clone.myntra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.myntra.repository.entity.User;

@Repository
public interface UserRespository extends  JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

}
