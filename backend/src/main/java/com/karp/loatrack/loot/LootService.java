package com.karp.loatrack.loot;

import com.karp.loatrack.loot.model.Loot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LootService {
    private final LootRespository lootRepository;

    public List<Loot> getLootForContent(int contentId){
        return lootRepository.findLootByContentId(contentId);
    }

}
