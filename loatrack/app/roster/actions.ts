import { ICharacterDto } from '@/lib/dtos';
import { API_BASE, API_ROSTER_V1 } from '@/lib/constants';
import axios, { AxiosResponse } from 'axios';

export async function getRoster(userId: string) {
  console.log(API_BASE + API_ROSTER_V1 + '?userId=' + userId);
  const response = await axios.get(
    API_BASE + API_ROSTER_V1 + '?userId=' + userId
  );
  console.log(response.data.data);
  return response.data.data;
}
