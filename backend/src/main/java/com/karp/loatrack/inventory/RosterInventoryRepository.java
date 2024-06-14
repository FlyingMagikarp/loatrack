package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.model.RosterInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface RosterInventoryRepository extends JpaRepository<RosterInventory, Integer> {

    @Query("select ri from RosterInventory ri where ri.fkUser.id = ?1")
    RosterInventory findRosterInventoryById(UUID id);
}
