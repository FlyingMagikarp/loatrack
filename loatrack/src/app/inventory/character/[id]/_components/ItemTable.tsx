import {ItemRow} from "@/app/inventory/character/[id]/_components/ItemRow";
import {ICharacterInventoryFlatDto, ICharacterInventoryPresentationDto} from "@/lib/dtos";


export interface IItemTableProps {
  inv: ICharacterInventoryFlatDto[]
}

export default async function ItemTable({inv}:IItemTableProps){

  return (
      <>
        <table className="table-fixed">
          <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">&nbsp;</th>{/*Item icon*/}
            <th className="roster-table-header-cell">Item</th>
            <th className="roster-table-header-cell">Amount</th>
            <th>&nbsp;</th>
          </tr>
          </thead>
          <tbody>
            {inv.map((item) => <ItemRow item={item}/>)}
          </tbody>
        </table>
      </>
  );
}