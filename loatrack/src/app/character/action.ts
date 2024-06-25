import {ICharacterDto} from "@/lib/dtos";
import {API_BASE, API_UPDATE_CHARACTER_V1} from "@/lib/constants";

export async function updateCharacterInfo(character: ICharacterDto) {
  const res = await fetch(API_BASE + API_UPDATE_CHARACTER_V1, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(character),
    cache: 'no-cache'
  });
  console.log('res');
  console.log(res);
}