import {IUpgradeDto} from "@/lib/dtos";
import CalcRow from "@/app/calculator/_components/CalcRow";

export interface ICalcTableProps {
  data: IUpgradeDto,
}

export default async function CalcTable({data}:ICalcTableProps){

  return (
      <>
        <table className="table-fixed mb-5 border-b">
          <caption className={"text-xl font-bold mb-1"}>{data.startIlvl} - {data.endIlvl}</caption>
          <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">Item</th>
            <th className="roster-table-header-cell">Needed</th>
            <th className="roster-table-header-cell">Have</th>
            <th className="roster-table-header-cell">Diff</th>
            <th className="roster-table-header-cell">Income</th>
            <th className="roster-table-header-cell">Weeks needed</th>
          </tr>
          </thead>
          <tbody>
                <CalcRow item={data.gold} />
                <CalcRow item={data.silver} />
                <CalcRow item={data.shards} />
                <CalcRow item={data.oreha} />
                <CalcRow item={data.leapstone} />
                <CalcRow item={data.reds} />
                <CalcRow item={data.blues} />
          </tbody>
        </table>
      </>
  );
}