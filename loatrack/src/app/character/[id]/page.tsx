import { API_BASE, API_CHARACTER_V1, USER_ID } from '@/lib/constants';
import { ICharacterDto } from '@/lib/dtos';

async function getCharacter(charId: string){
  const res = await fetch(API_BASE + API_CHARACTER_V1 + '?charId=' + charId, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto;
}


export default async function Character({params}: any) {
  const character = await getCharacter(params.id);

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <h1>{character.name}</h1>

    </main>
  );
}
