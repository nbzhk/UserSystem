package com.example.usersystem1.repositories;

import com.example.usersystem1.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town getDistinctByName(String name);
}
