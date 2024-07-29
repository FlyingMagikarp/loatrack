package com.karp.loatrack.loot;

import com.karp.loatrack.loot.model.Loot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LootRespository extends JpaRepository<Loot, Integer> {
    @Query("select l from Loot l where l.fkContent.id = ?1")
    List<Loot> findLootByContentId(int contentId);
}
