package com.karp.loatrack.roster;

import com.karp.loatrack.roster.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Character, Integer> {
}
