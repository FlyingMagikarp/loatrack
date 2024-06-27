import {getCharacter} from "@/app/character/action";


export default async function InventoryCharacter ({params}:any){
  const character = await getCharacter(params.id);
  return (
      <main>
        Inventory Character {character.name}
      </main>
  );
}