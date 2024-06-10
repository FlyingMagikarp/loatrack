package com.karp.loatrack.roster;

import com.karp.loatrack.roster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
