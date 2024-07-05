package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.model.RosterInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RosterInventoryRepository extends JpaRepository<RosterInventory, Integer> {

    @Query("select ri from RosterInventory ri where ri.fkUser.id = ?1 order by ri.fkItem.id")
    List<RosterInventory> findAllRosterInventoryByUserId(UUID id);

    @Query("select ri from RosterInventory ri where ri.fkUser.id = ?1 and ri.fkItem.id = ?2 order by ri.fkItem.id")
    RosterInventory findRosterInventoryByItemId(UUID id, int itemId);
}
