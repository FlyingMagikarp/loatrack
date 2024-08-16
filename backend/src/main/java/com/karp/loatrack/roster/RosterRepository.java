package com.karp.loatrack.roster;

import com.karp.loatrack.roster.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RosterRepository extends JpaRepository<Character, Integer> {

    @Query("select c from Character c where c.fkUser.id = ?1 order by c.ilvl desc")
    List<Character> findAllByUserId(UUID userId);
}
