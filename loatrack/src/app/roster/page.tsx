import { API_BASE, API_ROSTER_V1, USER_ID } from '@/lib/constants';
import { ICharacterDto } from '@/lib/dtos';
import RosterRow from '@/app/roster/_components/RosterRow';
import { RosterTable } from '@/app/roster/_components/RosterTable';

async function getRoster(userId: string){
  const res = await fetch(API_BASE + API_ROSTER_V1 + '?userId=' + userId, {cache: 'no-cache'});
  const data = await res.json();
  return data as ICharacterDto[];
}


export default async function Roster() {
  const roster = await getRoster(USER_ID);

  return (
    <main>
      <div>
        <RosterTable roster={roster}/>
      </div>
    </main>
  );
}
