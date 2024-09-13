package com.karp.loatrack.content;

import com.karp.loatrack.content.model.ContentSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContentSettingsRepository extends JpaRepository<ContentSettings, Integer> {
    @Query("select cs from ContentSettings cs where cs.fkChar.id = ?1")
    List<ContentSettings> findSettingsByCharId(int charId);

    @Modifying
    @Transactional
    @Query("delete from ContentSettings cs where cs.fkChar.id = ?1")
    void deleteSettingsByCharId(int charId);
}
