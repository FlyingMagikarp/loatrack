import { API_BASE, API_CHARACTER_V1 } from '@/lib/constants';
import { ICharacterDto } from '@/lib/dtos';
import CharacterEditInfoForm from "@/app/character/_components/CharacterEditInfoForm";

async function getCharacter(charId: string){
  const res = await fetch(API_BASE + API_CHARACTER_V1 + '?charId=' + charId, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto;
}


export default async function Character({params}: any) {
  const character = await getCharacter(params.id);

  return (
      <main>
        <CharacterEditInfoForm character={character}/>
      </main>
  );
}
