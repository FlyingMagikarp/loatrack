export interface ICharacterDto {
  id: number,
  userId: string,
  name: string,
  ilvl: number,
  notes: string,
  notesOverview: string,
  classname: string,
}

export interface IInventoryPresentationDto {
  item: IItemDto,
  amount: number,
}

export interface IInventoryPosFlatDto {
  itemId: number,
  itemName: string,
  amount: number,
}

export interface IItemDto{
  id: number,
  name: string,
  tier: ITierDto,
}

export interface ITierDto{
  id: number,
  name: string,
}

export interface IOverviewCharacterData{
  charId: number,
  name: string,
  inventory: IInventoryPosFlatDto[],
}

export interface IContentDto{
  id: number,
  name: string,
  hardmode: boolean,
}

export interface IWeeklyClearDto{
  charId: number,
  charName: string,
  raidId: number,
  raidName: string,
  hardmode: boolean,
  cleared: boolean,
}

export interface IContentSettingDto{
  charId: number,
  raidIds: number[],
  clearCD: boolean,
  restedCD: boolean,
  clearGR: boolean,
  restedGR: boolean,
  unasLeapstone: number,
}

export interface IIncomeCharDto{
  charId: number,
  charName: string,
  loot: IIncomeItemDto[],
}

export interface IIncomeItemDto{
  itemId: number,
  itemName: string,
  bound: boolean,
  amount: number,
}

