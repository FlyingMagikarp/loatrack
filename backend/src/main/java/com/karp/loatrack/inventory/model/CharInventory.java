package com.karp.loatrack.inventory.model;

import com.karp.loatrack.item.model.Item;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.roster.model.User;
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
@Table(name="t_char_inventory")
public class CharInventory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_item", referencedColumnName = "id")
    private Item fkItem;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "fk_character", referencedColumnName = "id")
    private Character fkCharacter;
}
