import {API_BASE, API_GET_CHAR_INV_TIER, USER_ID} from "@/lib/constants";
import {ICharacterInventoryFlatDto, ICharacterInventoryPresentationDto} from "@/lib/dtos";


export async function getInventoryForTier(charId: number, tier: number){
  //@RequestParam UUID uuid, @RequestParam int charId, @RequestParam int tier
  const res = await fetch(API_BASE + API_GET_CHAR_INV_TIER + `?uuid=${USER_ID}&charId=${charId}&tier=${tier}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterInventoryPresentationDto[];
}

export async function mapInventoryToFlat(cip: ICharacterInventoryPresentationDto[]){
  return cip.map((x) => {
    return {amount: x.amount, itemId: x.item.id, itemName: x.item.name};
  });
}