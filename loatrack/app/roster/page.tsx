import { RosterTable } from './RosterTable';
import { getRoster } from './actions';
import { USER_ID } from '@/lib/constants';

export default async function RosterSettingsPage() {
  const roster = await getRoster(USER_ID);

  return (
    <main className="flex flex-1 flex-col gap-4 p-4 md:gap-8 md:p-6">
      <div className="flex items-center">
        <h1 className="font-semibold text-lg md:text-2xl">Roster</h1>
      </div>
      <RosterTable roster={roster} />
    </main>
  );
}
