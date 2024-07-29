import {
  API_BASE, API_CHARACTER_V1, API_CONTENT_SETTINGS_UPDATE_V1,
  API_OVERVIEW_ROSTER_V1,
  API_OVERVIEW_STORAGE_V1,
  API_OVERVIEW_TIER0_V1, API_OVERVIEW_WEEKLY_CLEAR_RESET_V1, API_OVERVIEW_WEEKLY_CLEAR_V1,
  USER_ID
} from "@/lib/constants";
import {ICharacterDto, IInventoryPosFlatDto, IOverviewCharacterData, IWeeklyClearDto} from "@/lib/dtos";


export async function getOverviewRosterData(){
  const res = await fetch(API_BASE + API_OVERVIEW_ROSTER_V1 + `?userId=${USER_ID}`, {cache: 'no-cache'});
  const data = await res.json();
  return data as IOverviewCharacterData[];
}

export async function getOverviewStorageData() {
  const res = await fetch(API_BASE + API_OVERVIEW_STORAGE_V1 + `?userId=${USER_ID}`, {cache: 'no-cache'});
  const data = await res.json();

  return data as IOverviewCharacterData[];
}

export async function getOverviewTier0Data(){
  const res = await fetch(API_BASE + API_OVERVIEW_TIER0_V1 + `?userId=${USER_ID}`, {cache: 'no-cache'});
  const data = await res.json();

  return data as IInventoryPosFlatDto[];
}

export async function getWeeklyClears(){
  const res = await fetch(API_BASE + API_OVERVIEW_WEEKLY_CLEAR_V1, {cache: 'no-cache'});
  const data = await res.json();

  return data as IWeeklyClearDto[][];
}

export async function resetWeeklyClears(){
  await fetch(API_BASE + API_OVERVIEW_WEEKLY_CLEAR_RESET_V1, {cache: 'no-cache'});
}

export async function updateWeeklyClear(clear: IWeeklyClearDto){
  const res = await fetch(API_BASE + API_OVERVIEW_WEEKLY_CLEAR_V1, {
    method: 'POST',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify(clear),
    cache: 'no-cache'
  });
}