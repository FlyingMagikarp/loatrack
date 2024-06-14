package com.karp.loatrack.item;

import com.karp.loatrack.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
