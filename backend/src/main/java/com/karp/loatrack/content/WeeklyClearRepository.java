package com.karp.loatrack.content;

import com.karp.loatrack.content.model.WeeklyClearOV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeeklyClearRepository extends JpaRepository<WeeklyClearOV, Integer> {
    @Query("select w from WeeklyClearOV w where w.fkChar.id = ?1 order by w.fkRaid.minIlvl")
    List<WeeklyClearOV> findByCharId(int charId);
}
