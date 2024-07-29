'use client'

import {IWeeklyClearDto} from '@/lib/dtos';
import {updateWeeklyClear} from "@/app/overview/actions";
import {useRouter} from "next/navigation";

interface IContentRowProps {
  clearList: IWeeklyClearDto[],
}

export async function ContentItem({ clearList}: IContentRowProps) {
  const router = useRouter();

  async function updateData(clearData: IWeeklyClearDto, cleared: boolean){
    clearData.cleared = cleared
    await updateWeeklyClear(clearData).then(() => {
      router.refresh();
    });
  }

  return (
      <div className={"grow border-2 rounded"}>
        <div className={"border-b"}>{clearList[0].charName}</div>
        <ul className={"space-y-2 p-2"}>
        {clearList.map((x, idx) => (
            <li key={idx} className={"flex justify-between"}>
              <span className={"mr-2"}>{x.raidName}{x.hardmode ? ' HM' : ' NM'}</span>
              <input type="checkbox" name="restedGR" id="restedGR"
                     className="mt-1 w-4 h-4 text-blue-600 rounded ring-offset-gray-800 bg-gray-700 border-gray-600"
                     checked={clearList[idx].cleared} onChange={e => {updateData(clearList[idx], e.target.checked)}}/>
            </li>
        ))}
        </ul>
      </div>
  );
}