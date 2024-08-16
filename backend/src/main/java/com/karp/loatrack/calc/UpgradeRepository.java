package com.karp.loatrack.calc;

import com.karp.loatrack.calc.model.Upgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UpgradeRepository extends JpaRepository<Upgrade, Integer> {

    @Query("select u from Upgrade u where u.endIlvl > ?1 and u.startIlvl <= ?1 order by u.endIlvl asc limit 1")
    Upgrade findByIlvl(int ilvl);
}
