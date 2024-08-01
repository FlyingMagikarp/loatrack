import { API_BASE, API_ROSTER_V1, USER_ID } from '@/lib/constants';
import { ICharacterDto } from '@/lib/dtos';
import { RosterTable } from '@/app/roster/_components/RosterTable';
import Link from "next/link";

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
        {roster &&
          <RosterTable roster={roster}/>
        }

        <Link href={"/character/new"}>
          <button className="btn-primary" type="button">Add</button>
        </Link>
      </div>
    </main>
  );
}
