import {
  API_BASE,
  API_GET_CHAR_INV_TIER_V1, API_GET_ROSTER_INV_TIER_V1,
  API_UPDATE_CHAR_INV_V1, API_UPDATE_ROSTER_INV_V1,
  USER_ID
} from "@/lib/constants";
import {IInventoryPosFlatDto, IInventoryPresentationDto} from "@/lib/dtos";


export async function getCharInventoryForTier(charId: number, tier: number){
  const res = await fetch(API_BASE + API_GET_CHAR_INV_TIER_V1 + `?uuid=${USER_ID}&charId=${charId}&tier=${tier}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IInventoryPresentationDto[];
}

export async function getRosterInventoryForTier(tier: number){
  const res = await fetch(API_BASE + API_GET_ROSTER_INV_TIER_V1 + `?uuid=${USER_ID}&tier=${tier}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IInventoryPresentationDto[];
}

export async function mapInventoryToFlat(cip: IInventoryPresentationDto[]){
  return cip.map((x) => {
    return {amount: x.amount, itemId: x.item.id, itemName: x.item.name};
  });
}

export async function updateCharacterInventory(ci: IInventoryPosFlatDto[], charId: number){
  const res = await fetch(API_BASE + API_UPDATE_CHAR_INV_V1 + `?uuid=${USER_ID}&charId=${charId}`, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(ci),
    cache: 'no-cache'
  });
}

export async function updateRosterInventory(ri: IInventoryPosFlatDto[]){
  const res = await fetch(API_BASE + API_UPDATE_ROSTER_INV_V1 + `?uuid=${USER_ID}`, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(ri),
    cache: 'no-cache'
  });
}