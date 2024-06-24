'use client';

import { getCharacter } from './actions';
import { useSearchParams } from 'next/navigation';

export default async function CharacterPage() {
  const searchParams = useSearchParams();
  const charId = searchParams.get('charId') ?? '1';
  const character = await getCharacter(parseInt(charId, 0));

  return (
    <main className="flex flex-1 flex-col gap-4 p-4 md:gap-8 md:p-6">
      <div className="flex items-center">
        <h1 className="font-semibold text-lg md:text-2xl">Character</h1>
      </div>
      {character.name}
    </main>
  );
}
