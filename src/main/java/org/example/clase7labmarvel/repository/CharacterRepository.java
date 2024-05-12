package org.example.clase7labmarvel.repository;

import org.example.clase7labmarvel.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findByNameIgnoreCaseContaining(String search);

}
