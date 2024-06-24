import { ICharacterDto } from '@/lib/dtos';
import {RosterRow} from '@/app/roster/_components/RosterRow';

export interface IRosterTable {
  roster: ICharacterDto[];
}

export function RosterTable({ roster }: IRosterTable) {

  return (
    <>
      <table className="table-fixed">
        <thead>
          <tr className="table-row">
            <th className="table-cell w-52 text-left border-collapse border border-slate-500 bg-gray-600">Class</th>
            <th className="table-cell w-52 text-left border-collapse border border-slate-500 bg-gray-600">Name</th>
            <th className="table-cell w-52 text-left border-collapse border border-slate-500 bg-gray-600">iLvL</th>
            <th className="table-cell w-52 text-left border-collapse border border-slate-500 bg-gray-600">Notes Overview</th>
            <th>&nbsp;</th>
          </tr>
        </thead>
        <tbody>
          {roster.map((character) => <RosterRow character={character} />)}
        </tbody>
      </table>
    </>
  );
}
