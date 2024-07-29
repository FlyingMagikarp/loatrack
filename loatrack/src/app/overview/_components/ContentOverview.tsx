'use client'

import {getWeeklyClears, resetWeeklyClears} from "@/app/overview/actions";
import {ContentItem} from "@/app/overview/_components/ContentItem";
import {useRouter} from "next/navigation";


export default async function ContentOverview() {
  const router = useRouter();
  const data = await getWeeklyClears();

  function resetClears () {
    resetWeeklyClears();
    router.refresh();
  }

  return (
      <>
        <div className={"flex flex-row space-x-2"}>
          {data.map((x, idx) => (
              <div key={idx}>
                <ContentItem clearList={x}/>
              </div>
          ))}
        </div>
        <div>
          <button
              className="btn-secondary"
              onClick={resetClears}>Reset
          </button>
        </div>
      </>
)
  ;
}