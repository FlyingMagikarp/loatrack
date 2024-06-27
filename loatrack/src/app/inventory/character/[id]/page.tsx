import {getCharacter} from "@/app/character/action";
import CharacterInventory from "@/app/inventory/character/[id]/_components/CharacterInventory";


export default async function CharacterInventoryScreen ({params}:any){
  const character = await getCharacter(params.id);
  return (
      <main>
        <div className="bg-gray-700 rounded-lg relative m-10">
          <div className="flex items-start justify-between p-5 border-b rounded-t">
            <h3 className="text-xl font-semibold">
              {character.name} Inventory
            </h3>
          </div>
          <CharacterInventory character={character}/>
        </div>

      </main>
);
}