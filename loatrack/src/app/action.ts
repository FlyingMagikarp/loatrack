import {API_BASE, API_ROSTER_V1, USER_ID} from "@/lib/constants";
import {ICharacterDto} from "@/lib/dtos";

export async function getCharacters(){
  const res = await fetch(API_BASE + API_ROSTER_V1 + '?userId=' + USER_ID, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto[];
}