'use client'

import {ItemRow} from "@/app/inventory/_components/ItemRow";
import {IInventoryPosFlatDto} from "@/lib/dtos";
import {updateCharacterInventory, updateRosterInventory} from "@/app/inventory/actions";
import {useRouter} from "next/navigation";


export interface IItemTableProps {
  inv: IInventoryPosFlatDto[],
  charId?: number,
}

export default function ItemTable({inv, charId}:IItemTableProps){
  const router = useRouter();

  function updateInventory (invPos: IInventoryPosFlatDto) {
    let idx = inv.findIndex(x => x.itemId == invPos.itemId)
    if(idx > -1){
      inv[idx].amount = invPos.amount;
    }
  }

  async function saveInventory (){
    if (charId){
      await updateCharacterInventory(inv, charId).then(() => router.refresh());
    } else {
      await updateRosterInventory(inv).then(() => router.refresh());
    }
  }

  return (
      <>
        <table className="table-fixed">
          <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">&nbsp;</th>
            {/*Item icon*/}
            <th className="roster-table-header-cell">Item</th>
            <th className="roster-table-header-cell">Amount</th>
            <th>&nbsp;</th>
          </tr>
          </thead>
          <tbody>
          {inv.map((item) =>
              <tr key={item.itemId} className="hover:bg-gray-500">
                <ItemRow item={item} updateInv={updateInventory}/>
              </tr>
          )}
          </tbody>
        </table>
        <button
            className="btn-primary"
            onClick={saveInventory}>Save
        </button>
      </>
  );
}