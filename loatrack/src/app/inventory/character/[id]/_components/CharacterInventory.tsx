import {ICharacterDto} from "@/lib/dtos";
import {Collapse} from "@/components/ui/Collapse";
import TierTable from "@/app/inventory/character/[id]/_components/TierTable";


export interface ICharacterInventoryProps {
  character: ICharacterDto
}

export default async function CharacterInventory({character}:ICharacterInventoryProps){


  return (
      <div className="p-6 space-y-6">
        <Collapse title={'Tier 0'}>
          <TierTable tier={0}/>
        </Collapse>

        <Collapse title={'Tier 1'}>
          child1
        </Collapse>

        <Collapse title={'Tier 2'}>
          child2
        </Collapse>

        <Collapse title={'Tier 3'}>
          child3
        </Collapse>
      </div>
  );
}