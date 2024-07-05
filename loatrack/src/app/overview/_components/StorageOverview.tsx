import {getOverviewStorageData, getOverviewTier0Data} from "@/app/overview/actions";
import OverViewItemTable from "@/app/overview/_components/OverviewItemTable";
import OverviewTier0 from "@/app/overview/_components/OverviewTier0";


export default async function StorageOverview() {
  const data = await getOverviewStorageData();
  const dataT0 = await getOverviewTier0Data();

  return (
      <div>
        <OverviewTier0 data={dataT0}/>
        <br/>
        <OverViewItemTable data={data} isCharacter={false}/>
      </div>
  );
}