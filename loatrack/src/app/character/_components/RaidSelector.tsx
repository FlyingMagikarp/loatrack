
import {IContentDto} from "@/lib/dtos";

interface IRaidSelectorProps {
  raids: IContentDto[];
  selectedRaid: number;
  raidCount: number;
  updateRaidSelection: (raidId: number, raidCount: number)=>void;
}

export default function RaidSelector({raids, selectedRaid, raidCount, updateRaidSelection}: IRaidSelectorProps){
  return (
      <>
        <label>{"Raid "+raidCount}</label>
        <select
            name={"raid-selector"+raidCount}
            id={"raid-selector"+raidCount}
            className={"text-black"}
            defaultValue={selectedRaid}
            value={selectedRaid}
            onChange={(val) => updateRaidSelection(parseInt(val.target.value), raidCount)}>
          <option key={-1} value={-1}>None/Skip</option>
          {raids.map(x =>
              <option key={x.id} value={x.id}>
                {x.name}{x.hardmode ? ' HM' : ' NM'}
              </option>)
          }
        </select>
      </>
  )
}