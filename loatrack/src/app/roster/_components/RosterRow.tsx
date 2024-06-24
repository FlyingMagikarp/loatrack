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
      <td className="table-cell w-52 border-collapse border border-slate-500">{<ClassIcon classString={character.classname} />}</td>
      <td className="table-cell w-52 border-collapse border border-slate-500">{character.name}</td>
      <td className="table-cell w-52 border-collapse border border-slate-500">{character.ilvl}</td>
      <td className="table-cell w-52 border-collapse border border-slate-500">{character.notesOverview}</td>
    </tr>
  );
}