import {IOverviewCharacterData} from "@/lib/dtos";
import {OverviewItemRow} from "@/app/overview/_components/OverviewItemRow";

export interface IOverviewItemTableProps {
  data: IOverviewCharacterData[];
  isCharacter?: boolean;
}

export default async function OverViewItemTable({data, isCharacter = false}:IOverviewItemTableProps) {


  return (
      <>
        <table className="table-fixed">
          <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">Name</th>
            <th className="roster-table-header-cell">Shards</th>
            <th className="roster-table-header-cell">Leaps</th>
            <th className="roster-table-header-cell">Red</th>
            <th className="roster-table-header-cell">Blue</th>
          </tr>
          </thead>
          <tbody>
            {data.map((character) =>
                <tr key={character.charId} className="hover:bg-gray-500">
                  <OverviewItemRow data={character} isCharacter={isCharacter}/>
                </tr>
            )}
          </tbody>
        </table>
      </>
  );
}