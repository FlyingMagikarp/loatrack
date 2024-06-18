import { ICharacterDto } from '@/lib/dtos';
import { API_BASE, API_ROSTER_V1 } from '@/lib/constants';
import axios, { AxiosResponse } from 'axios';

export async function getRoster(userId: string) {
  const response: AxiosResponse<ICharacterDto[]> = await axios.get(
    API_BASE + API_ROSTER_V1 + '?userId=' + userId
  );
  return response.data;
}
