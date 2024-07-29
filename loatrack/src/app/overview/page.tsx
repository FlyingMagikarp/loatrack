import {Collapse} from "@/components/ui/Collapse";
import RosterOverview from "@/app/overview/_components/RosterOverview";
import StorageOverview from "@/app/overview/_components/StorageOverview";
import ContentOverview from "@/app/overview/_components/ContentOverview";


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
        <Collapse title={'Content Window'} initialState={true}>
          <ContentOverview />
        </Collapse>
      </div>
    </main>
  );
}
