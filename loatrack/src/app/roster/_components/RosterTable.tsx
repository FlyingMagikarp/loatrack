import { ICharacterDto } from '@/lib/dtos';
import {RosterRow} from '@/app/roster/_components/RosterRow';

export interface IRosterTable {
  roster: ICharacterDto[];
}

export async function RosterTable({ roster }: IRosterTable) {

  return (
    <>
      <table className="table-fixed">
        <thead>
          <tr className="table-row">
            <th className="roster-table-header-cell">Class</th>
            <th className="roster-table-header-cell">Name</th>
            <th className="roster-table-header-cell">iLvL</th>
            <th className="roster-table-header-cell">Notes Overview</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
        {/* eslint-disable-next-line react/jsx-key */}
          {roster && roster.map((character) => <RosterRow character={character} />)}
        </tbody>
      </table>
    </>
  );
}
