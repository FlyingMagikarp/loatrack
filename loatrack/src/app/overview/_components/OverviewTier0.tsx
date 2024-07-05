import {IInventoryPosFlatDto} from "@/lib/dtos";

export interface IOverviewTier0Props{
  data: IInventoryPosFlatDto[],
}

export default async function OverviewTier0({data} : IOverviewTier0Props){

  return (
      <>
        <table className="table-fixed">
          <thead>
            <tr className="table-row">
              <th className="roster-table-header-cell">Gold</th>
              <th className="roster-table-header-cell">Silver</th>
            </tr>
          </thead>
          <tbody>
            <tr className="hover:bg-gray-500">
              <td className="roster-table-cell">{data[0].amount.toLocaleString()}</td>
              <td className="roster-table-cell">{data[1].amount.toLocaleString()}</td>
            </tr>
          </tbody>
        </table>
      </>
  );
}