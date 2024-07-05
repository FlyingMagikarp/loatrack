import { IInventoryPosFlatDto} from "@/lib/dtos";
import {Collapse} from "@/components/ui/Collapse";
import ItemTable from "@/app/inventory/_components/ItemTable";
import {TIER_0_ID, TIER_3_ID} from "@/lib/constants";
import { getRosterInventoryForTier, mapInventoryToFlat} from "@/app/inventory/actions";


export interface IRosterInventoryProps {
}

export default async function RosterInventory(){
  const invTier0 : IInventoryPosFlatDto[] = await mapInventoryToFlat(await getRosterInventoryForTier(TIER_0_ID));
  const invTier3 : IInventoryPosFlatDto[] = await mapInventoryToFlat(await getRosterInventoryForTier(TIER_3_ID));

  return (
      <div className="p-6 space-y-6">
        <Collapse title={'Tier 0'} initialState={true}>
          <ItemTable inv={invTier0}/>
        </Collapse>
        <Collapse title={'Tier 3'} initialState={true}>
          <ItemTable inv={invTier3}/>
        </Collapse>
      </div>
  );
}