package com.lausnbd.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lausnbd.backend.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailIgnoreCase(String email);


}
