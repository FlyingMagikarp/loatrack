package com.karp.loatrack.income;

import com.karp.loatrack.content.ContentRepository;
import com.karp.loatrack.content.ContentService;
import com.karp.loatrack.content.dto.WeeklyClearDto;
import com.karp.loatrack.content.model.Content;
import com.karp.loatrack.loot.LootService;
import com.karp.loatrack.loot.model.Loot;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeService {
    private final ContentRepository contentRepository;
    private final ContentService contentService;
    private final LootService lootService;

    public Content getHighestClearableCD(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_CD, iLvl);
        return cdList.getLast();
    }

    public Content getHighestClearableGR(int iLvl){
        List<Content> cdList = contentRepository.findByTypeAndMaxIlvl(Constants.CONTENT_TYPE_GR, iLvl);
        return cdList.getLast();
    }

    public Integer getGoldFromRaids(int charId){
        List<WeeklyClearDto> weeklyRaids = contentService.getWeeklyClearStatusForCharId(charId);
        List<Loot> loot = new ArrayList<>();
        for(WeeklyClearDto dto : weeklyRaids){
            loot.addAll(lootService.getLootForContent(dto.raidId));
        }

        return loot.stream().mapToInt(Loot::getAmount).sum();
    }
}
