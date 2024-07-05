import {Collapse} from "@/components/ui/Collapse";
import RosterOverview from "@/app/overview/_components/RosterOverview";
import StorageOverview from "@/app/overview/_components/StorageOverview";


export default async function Roster() {
  return (
    <main>
      <div>
        <Collapse title={'Roster'} initialState={true}>
          <RosterOverview />
        </Collapse>
        <Collapse title={'Storage'} initialState={true}>
          <StorageOverview />
        </Collapse>
      </div>
    </main>
  );
}
