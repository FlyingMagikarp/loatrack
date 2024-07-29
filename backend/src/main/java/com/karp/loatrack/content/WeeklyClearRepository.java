package com.karp.loatrack.content;

import com.karp.loatrack.content.model.WeeklyClearOV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WeeklyClearRepository extends JpaRepository<WeeklyClearOV, Integer> {
    @Query("select w from WeeklyClearOV w where w.fkChar.id = ?1 order by w.fkRaid.minIlvl")
    List<WeeklyClearOV> findByCharId(int charId);

    @Modifying
    @Transactional
    @Query("delete from WeeklyClearOV w where w.fkChar.id = ?1")
    void deleteByCharId(int charId);

    @Modifying
    @Transactional
    @Query("update WeeklyClearOV w set w.cleared = ?1 where w.fkChar.id = ?2 and w.fkRaid.id = ?3")
    void updateWeeklyClear(boolean cleared, int charId, int raidId);

    @Modifying
    @Transactional
    @Query("update WeeklyClearOV w set w.cleared = false")
    void resetWeeklyClear();
}
