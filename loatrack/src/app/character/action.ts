import {ICharacterDto, IContentDto, IContentSettingDto} from "@/lib/dtos";
import {
  API_BASE,
  API_CHARACTER_V1,
  API_CONTENT_RAIDS_V1,
  API_CONTENT_SETTINGS_V1,
  API_UPDATE_CHARACTER_V1
} from "@/lib/constants";

export async function updateCharacterInfo(character: ICharacterDto) {
  const res = await fetch(API_BASE + API_UPDATE_CHARACTER_V1, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(character),
    cache: 'no-cache'
  });
}

export async function deleteCharacter(charId: number) {
  const res = await fetch(API_BASE + API_CHARACTER_V1 + `?charId=${charId}`, {
    method: 'DELETE',
    cache: 'no-cache'
  });
}

export async function getCharacter(charId: number){
  const res = await fetch(API_BASE + API_CHARACTER_V1 + `?charId=${charId}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto;
}

export async function getClearableRaids(iLvl: number){
  const res = await fetch(API_BASE + API_CONTENT_RAIDS_V1 + `?iLvl=${iLvl}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IContentDto[];
}


export async function getContentSettings(charId: number){
  const res = await fetch(API_BASE + API_CONTENT_SETTINGS_V1 + `?charId=${charId}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IContentSettingDto;
}