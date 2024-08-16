package com.karp.loatrack.calc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="t_upgrade")
public class Upgrade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int startIlvl;
    private int endIlvl;
    private int gold;
    private int silver;
    private int shards;
    private int oreha;
    private int leapstone;
    private int red;
    private int blue;
}
