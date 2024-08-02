import {API_BASE, API_ROSTER_V1, USER_ID} from "@/lib/constants";
import {ICharacterDto} from "@/lib/dtos";

export async function getCharacters(){
  const res = await fetch(API_BASE + API_ROSTER_V1 + '?userId=' + USER_ID, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto[];
}

export async function showSnackbar(){
  let snackbar = document.getElementById("snackbar");
  if(!snackbar){return}
  snackbar.style.display = "block";
  setTimeout(function() {
    snackbar.style.display = "none";
  }, 2000);
}