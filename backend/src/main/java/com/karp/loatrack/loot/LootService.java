package com.karp.loatrack.loot;

import com.karp.loatrack.item.ItemRepository;
import com.karp.loatrack.item.model.Item;
import com.karp.loatrack.loot.model.Loot;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LootService {
    private final LootRespository lootRepository;
    private final ItemRepository itemRepository;

    public List<Loot> getLootForContent(int contentId){
        return lootRepository.findLootByContentId(contentId);
    }

    public Item getLeapstoneForIlvl(int ilvl){
        Item leap = itemRepository.findItemById(Constants.ITEM_ID_T3_3_LEAP);
        if(ilvl <= Constants.TIER_3_1_END){
            leap = itemRepository.findItemById(Constants.ITEM_ID_T3_1_LEAP);
        } else if (ilvl <= Constants.TIER_3_2_END) {
            leap = itemRepository.findItemById(Constants.ITEM_ID_T3_2_LEAP);
        }

        return leap;
    }
}
