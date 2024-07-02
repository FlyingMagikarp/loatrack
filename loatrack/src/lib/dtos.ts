export interface ICharacterDto {
  id: number,
  userId: string,
  name: string,
  ilvl: number,
  notes: string,
  notesOverview: string,
  classname: string,
}

export interface ICharacterInventoryPresentationDto {
  item: IItemDto,
  amount: number,
}

export interface ICharacterInventoryFlatDto {
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
