package com.url.shortener.repo;

import com.url.shortener.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByUsername(String username);
}
