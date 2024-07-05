import {getOverviewRosterData} from "@/app/overview/actions";
import OverViewItemTable from "@/app/overview/_components/OverviewItemTable";


export default async function RosterOverview() {
  const data = await getOverviewRosterData();

  return (
      <div>
        <OverViewItemTable data={data} isCharacter={true}/>
      </div>
  );
}