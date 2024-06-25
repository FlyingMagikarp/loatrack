import CharacterEditInfoForm from "@/app/character/_components/CharacterEditInfoForm";
import {getCharacter} from "@/app/character/action";


export default async function Character({params}: any) {
  const character = await getCharacter(params.id);

  return (
      <main>
        <CharacterEditInfoForm character={character}/>
      </main>
  );
}
