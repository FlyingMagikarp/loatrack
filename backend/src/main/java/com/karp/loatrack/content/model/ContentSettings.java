package com.karp.loatrack.content.model;

import com.karp.loatrack.roster.model.Character;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="t_char_content_settings")
public class ContentSettings {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_char", referencedColumnName = "id")
    private Character fkChar;

    private boolean clearCd;
    private boolean restedCd;
    private boolean clearGr;
    private boolean restedGr;
    private int unasLs;
}
