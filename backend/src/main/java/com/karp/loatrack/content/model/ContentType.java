package com.karp.loatrack.content.model;

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
@Table(name="t_content_type")
public class ContentType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String type;
}
