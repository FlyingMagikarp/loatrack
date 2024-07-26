package com.karp.loatrack.content;

import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {
    @Query("select c from Content c where c.minIlvl <= ?1 and c.id != 206 and c.id != 208 and c.type.id = "+Constants.CONTENT_TYPE_RAID+" order by c.minIlvl asc")
    List<Content> findByILvl(int iLvl);

    @Query("select c from Content c where c.type.id = ?1 and c.minIlvl <= ?2 order by c.minIlvl asc")
    List<Content> findByTypeAndMaxIlvl(int type, int iLvl);
}
