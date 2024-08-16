import {API_BASE, API_CALC_V1_UPGRADE, API_ROSTER_V1, USER_ID} from "@/lib/constants";
import {ICharacterDto, IInventoryPosFlatDto, IUpgradeDto} from "@/lib/dtos";

export async function getCharacters(){
  const res = await fetch(API_BASE + API_ROSTER_V1 + `?userId=${USER_ID}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto[];
}


// get upgrade list
// get income
// get have
// get math
export async function getUpgrade(charId: number){
  const res = await fetch(API_BASE + API_CALC_V1_UPGRADE + `?charId=${charId}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IUpgradeDto;
}

