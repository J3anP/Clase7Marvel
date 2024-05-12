package org.example.clase7labmarvel.repository;

import org.example.clase7labmarvel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
