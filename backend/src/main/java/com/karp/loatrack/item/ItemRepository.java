package com.karp.loatrack.item;

import com.karp.loatrack.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("select i from Item i where i.id = ?1")
    Item findItemById(int id);
}
