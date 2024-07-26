package com.karp.loatrack.content.model;

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
@Table(name="t_content")
public class Content {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int minIlvl;
    private String name;
    private boolean hardmode;
    @ManyToOne
    @JoinColumn(name = "fk_type", referencedColumnName = "id")
    private ContentType type;
}
