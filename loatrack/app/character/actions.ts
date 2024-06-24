import { ICharacterDto } from '@/lib/dtos';
import { API_BASE, API_CHARACTER_V1 } from '@/lib/constants';
import axios, { AxiosResponse } from 'axios';

export async function getCharacter(charId: number) {
  const response: AxiosResponse<ICharacterDto> = await axios.get(
    API_BASE + API_CHARACTER_V1 + '?charId=' + charId
  );
  return response.data;
}
