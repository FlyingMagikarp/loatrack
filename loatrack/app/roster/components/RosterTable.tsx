import { ICharacterDto } from '@/lib/dtos';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow
} from '@/components/ui/table';
import { Button } from '@/components/ui/button';
import { Logo } from '@/components/icons';
import { ClassIcon } from '@/components/ui/ClassIcon';
import Link from 'next/link';

export interface IRosterTable {
  roster: ICharacterDto[];
}

export function RosterTable({ roster }: IRosterTable) {
  const RosterRow = (character: ICharacterDto) => {
    return (
      <TableRow>
        <TableCell className="font-medium">
          {
            <Link href={'/character?charId=' + character.id}>
              <ClassIcon classString={character.classname} />
            </Link>
          }
        </TableCell>
        <TableCell className="md:table-cell">{character.name}</TableCell>
        <TableCell className="md:table-cell">{character.ilvl}</TableCell>
        <TableCell className="md:table-cell">
          {character.notesOverview}
        </TableCell>
      </TableRow>
    );
  };

  return (
    <>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="max-w-[150px]">Class</TableHead>
            <TableHead className="md:table-cell">Name</TableHead>
            <TableHead className="md:table-cell">iLvL</TableHead>
            <TableHead className="md:table-cell">Notes Overview</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>{roster.map((character) => RosterRow(character))}</TableBody>
      </Table>
      <Button size="icon" variant="secondary">
        Add
      </Button>
    </>
  );
}
