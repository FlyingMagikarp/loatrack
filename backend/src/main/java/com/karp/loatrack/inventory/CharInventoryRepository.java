package com.karp.loatrack.inventory;

import com.karp.loatrack.inventory.model.CharInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharInventoryRepository extends JpaRepository<CharInventory, Integer> {

    @Query("select ci from CharInventory ci where ci.fkCharacter.id = ?1")
    List<CharInventory> findAllByCharacterId(int characterId);

    @Query("select ci from CharInventory ci where ci.fkCharacter.id = ?1 and ci.fkItem.id = ?2")
    CharInventory findCharInventoryToUpdate(int characterId, int itemId);
}
