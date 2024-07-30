import {API_BASE, API_INCOME_V1} from "@/lib/constants";
import {IIncomeCharDto} from "@/lib/dtos";


export async function getIncome(){
  const res = await fetch(API_BASE + API_INCOME_V1, {cache: 'no-cache'});
  const data = await res.json();

  return data as IIncomeCharDto[];
}