'use client'

import { ICharacterDto } from '@/lib/dtos';
import { ClassIcon } from '@/components/ui/ClassIcon';
import { useRouter } from 'next/navigation';

interface IRosterRowProps {
  character: ICharacterDto,
}

export function RosterRow({ character }: IRosterRowProps) {
  const router = useRouter();

  return (
    <tr key={character.id} className="hover:bg-gray-500" onClick={() => router.push(`/character/${character.id}`)}>
      <td className="roster-table-cell">{<ClassIcon classString={character.classname} />}</td>
      <td className="roster-table-cell">{character.name}</td>
      <td className="roster-table-cell">{character.ilvl}</td>
      <td className="roster-table-cell">{character.notesOverview}</td>
    </tr>
  );
}