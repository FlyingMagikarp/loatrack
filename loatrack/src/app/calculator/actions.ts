import {API_BASE, API_CALC_V1, API_CALC_V1_ITEMS, API_ROSTER_V1, USER_ID} from "@/lib/constants";
import {ICharacterDto, IInventoryPosFlatDto} from "@/lib/dtos";


export async function getCalculations(charId: number){
  const res = await fetch(API_BASE + API_CALC_V1 + `?charId=${charId}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IInventoryPosFlatDto[];
}

export async function getCharacters(){
  const res = await fetch(API_BASE + API_ROSTER_V1 + `?userId=${USER_ID}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto[];
}

export async function getItems(charId: number, matList: IInventoryPosFlatDto[]){
  const res = await fetch(API_BASE + API_CALC_V1_ITEMS + `?charId=${charId}`, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(matList),
    cache: 'no-cache'
  });
}