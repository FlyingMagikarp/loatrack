import {IIncomeCharDto} from "@/lib/dtos";
import IncomeRow from "@/app/income/_components/IncomeRow";

export interface IIncomeTableProps {
  data: IIncomeCharDto,
}

export default async function IncomeTable({data}:IIncomeTableProps){

  return (
      <>
        <table className="table-fixed mb-5 border-b">
          <caption className={"text-xl font-bold mb-1"}>{data.charName}</caption>
          <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">Item</th>
            <th className="roster-table-header-cell">Amount</th>
          </tr>
          </thead>
          <tbody>
          {data && data.loot && data.loot.map((item) =>
              <tr key={item.itemId} className="hover:bg-gray-500">
                <IncomeRow item={item} />
              </tr>
          )}
          </tbody>
        </table>
      </>
  );
}