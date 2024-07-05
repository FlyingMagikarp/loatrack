import {
  API_BASE,
  API_OVERVIEW_ROSTER_V1,
  API_OVERVIEW_STORAGE_V1,
  API_OVERVIEW_TIER0_V1,
  USER_ID
} from "@/lib/constants";
import {IInventoryPosFlatDto, IOverviewCharacterData} from "@/lib/dtos";


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