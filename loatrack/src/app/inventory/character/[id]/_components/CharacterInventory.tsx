import {ICharacterDto, IInventoryPosFlatDto} from "@/lib/dtos";
import {Collapse} from "@/components/ui/Collapse";
import ItemTable from "@/app/inventory/_components/ItemTable";
import {TIER_3_ID} from "@/lib/constants";
import {getCharInventoryForTier, mapInventoryToFlat} from "@/app/inventory/actions";


export interface ICharacterInventoryProps {
  character: ICharacterDto
}

export default async function CharacterInventory({character}:ICharacterInventoryProps){
  const invTier3 : IInventoryPosFlatDto[] = await mapInventoryToFlat(await getCharInventoryForTier(character.id, TIER_3_ID));

  return (
      <div className="p-6 space-y-6">
        <Collapse title={'Tier 3'} initialState={true}>
          <ItemTable inv={invTier3} charId={character.id}/>
        </Collapse>
      </div>
  );
}