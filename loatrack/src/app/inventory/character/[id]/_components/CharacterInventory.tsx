import {ICharacterDto} from "@/lib/dtos";


export interface ICharacterInventoryProps {
  character: ICharacterDto
}

export default async function CharacterInventory({character}:ICharacterInventoryProps){


  return (
      <div className="p-6 space-y-6">
        
      </div>
  );
}