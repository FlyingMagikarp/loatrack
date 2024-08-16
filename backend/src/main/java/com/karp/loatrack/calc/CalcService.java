package com.karp.loatrack.calc;

import com.karp.loatrack.calc.dto.UpgradeDto;
import com.karp.loatrack.calc.dto.UpgradeItemDto;
import com.karp.loatrack.calc.model.Upgrade;
import com.karp.loatrack.income.IncomeService;
import com.karp.loatrack.inventory.InventoryService;
import com.karp.loatrack.inventory.dto.InventoryPresentationDto;
import com.karp.loatrack.item.ItemRepository;
import com.karp.loatrack.roster.RosterService;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalcService {
    private final RosterService rosterService;
    private final InventoryService inventoryService;
    private final IncomeService incomeService;
    private final UpgradeRepository upgradeRepository;
    private final ItemRepository itemRepository;

    public UpgradeDto getUpgrades(int charId){
        Character character = rosterService.getCharacterById(charId);
        // get needed base
        Upgrade base = upgradeRepository.findByIlvl(character.getIlvl());

        // get current have
        Upgrade have = getCurrentHave(charId);

        // set diff
        Upgrade diff = getDiff(base, have);

        // income
        Upgrade inc = incomeService.getCharAndRosterIncome(charId);

        UpgradeDto dto = combineEverything(base, have, diff, inc);
        dto.startIlvl = base.getStartIlvl();
        dto.endIlvl = base.getEndIlvl();

        return dto;
    }

    private UpgradeDto combineEverything(Upgrade base, Upgrade have, Upgrade diff, Upgrade inc){
        UpgradeDto upgradeDto = new UpgradeDto();

        UpgradeItemDto oreha = new UpgradeItemDto();
        oreha.itemId = Constants.ITEM_ID_T3_OREHA;
        oreha.itemName = itemRepository.findItemById(Constants.ITEM_ID_T3_OREHA).getName();
        oreha.base = base.getOreha();
        oreha.have = have.getOreha();
        oreha.income = inc.getOreha();
        oreha.diff = diff.getOreha();
        oreha.time = 0;
        upgradeDto.oreha = oreha;

        UpgradeItemDto gold = new UpgradeItemDto();
        gold.itemId = Constants.ITEM_ID_GOLD;
        gold.itemName = itemRepository.findItemById(Constants.ITEM_ID_GOLD).getName();
        gold.base = base.getGold();
        gold.have = have.getGold();
        gold.income = inc.getGold();
        gold.diff = diff.getGold();
        gold.time = (int) Math.ceil((double) diff.getGold() / inc.getGold());
        upgradeDto.gold = gold;

        UpgradeItemDto silver = new UpgradeItemDto();
        silver.itemId = Constants.ITEM_ID_SILVER;
        silver.itemName = itemRepository.findItemById(Constants.ITEM_ID_SILVER).getName();
        silver.base = base.getSilver();
        silver.have = have.getSilver();
        silver.income = inc.getSilver();
        silver.diff = diff.getSilver();
        silver.time = (int) Math.ceil((double) diff.getSilver() / inc.getSilver());
        upgradeDto.silver = silver;

        UpgradeItemDto shards = new UpgradeItemDto();
        shards.itemId = Constants.ITEM_ID_T3_SHARDS;
        shards.itemName = itemRepository.findItemById(Constants.ITEM_ID_T3_SHARDS).getName();
        shards.base = base.getShards();
        shards.have = have.getShards();
        shards.income = inc.getShards();
        shards.diff = diff.getShards();
        shards.time = (int) Math.ceil((double) diff.getShards() / inc.getShards());
        upgradeDto.shards = shards;

        UpgradeItemDto leapstone = new UpgradeItemDto();
        leapstone.itemId = Constants.ITEM_ID_T3_3_LEAP;
        leapstone.itemName = itemRepository.findItemById(Constants.ITEM_ID_T3_3_LEAP).getName();
        leapstone.base = base.getLeapstone();
        leapstone.have = have.getLeapstone();
        leapstone.income = inc.getLeapstone();
        leapstone.diff = diff.getLeapstone();
        leapstone.time = (int) Math.ceil((double) diff.getLeapstone() / inc.getLeapstone());
        upgradeDto.leapstone = leapstone;

        UpgradeItemDto red = new UpgradeItemDto();
        red.itemId = Constants.ITEM_ID_T3_3_RED;
        red.itemName = itemRepository.findItemById(Constants.ITEM_ID_T3_3_RED).getName();
        red.base = base.getRed();
        red.have = have.getRed();
        red.income = inc.getRed();
        red.diff = diff.getRed();
        red.time = (int) Math.ceil((double) diff.getRed() / inc.getRed());
        upgradeDto.reds = red;

        UpgradeItemDto blue = new UpgradeItemDto();
        blue.itemId = Constants.ITEM_ID_T3_3_BLUE;
        blue.itemName = itemRepository.findItemById(Constants.ITEM_ID_T3_3_BLUE).getName();
        blue.base = base.getBlue();
        blue.have = have.getBlue();
        blue.income = inc.getBlue();
        blue.diff = diff.getBlue();
        blue.time = (int) Math.ceil((double) diff.getBlue() / inc.getBlue());
        upgradeDto.blues = blue;

        return upgradeDto;
    }

    private Upgrade getDiff(Upgrade base, Upgrade have){
        Upgrade upgrade = new Upgrade();

        upgrade.setOreha(base.getOreha());

        upgrade.setGold(Math.max(0, base.getGold() - have.getGold()));
        upgrade.setSilver(Math.max(0, base.getSilver() - have.getSilver()));
        upgrade.setShards(Math.max(0, base.getShards() - have.getShards()));

        upgrade.setLeapstone(Math.max(0, base.getLeapstone() - have.getLeapstone()));
        upgrade.setRed(Math.max(0, base.getRed() - have.getRed()));
        upgrade.setBlue(Math.max(0, base.getBlue() - have.getBlue()));

        return upgrade;
    }

    private Upgrade getCurrentHave(int charId){
        List<InventoryPresentationDto> rosterInventory = inventoryService.getRosterInventory(Constants.USER_UUID);
        List<InventoryPresentationDto> charInventory = inventoryService.getCharInventory(Constants.USER_UUID, charId);

        Upgrade u = new Upgrade();

        u.setOreha(0);

        u.setGold(rosterInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_GOLD).findFirst().get().amount);
        u.setSilver(rosterInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_SILVER).findFirst().get().amount);
        u.setShards(charInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_SHARDS).findFirst().get().amount);

        u.setLeapstone(
                rosterInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_LEAP).findFirst().get().amount +
                charInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_LEAP).findFirst().get().amount);
        u.setRed(
                rosterInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_RED).findFirst().get().amount +
                charInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_RED).findFirst().get().amount);
        u.setBlue(
                rosterInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_BLUE).findFirst().get().amount +
                charInventory.stream().filter(x -> x.item.getId() == Constants.ITEM_ID_T3_3_BLUE).findFirst().get().amount);

        return u;
    }
}
