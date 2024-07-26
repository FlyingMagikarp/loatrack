package com.karp.loatrack.loot.model;

import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.item.model.Item;
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
@Table(name="t_loot")
public class Loot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "fk_content", referencedColumnName = "id")
    private Content fkContent;

    @ManyToOne
    @JoinColumn(name = "fk_item", referencedColumnName = "id")
    private Item fkItem;

    private int amount;
    private boolean bound;
}
