'use client'
import {ICharacterDto} from "@/lib/dtos";
import {useRouter} from "next/navigation";

interface ICharSelectorProps {
  chars: ICharacterDto[];
  selectedCharId?: number;
}

export default function CharSelector({chars, selectedCharId}: ICharSelectorProps){
  const router = useRouter();

  return (
      <>
        <label>{"Character: "}</label>
        <select
            name={"char-selector"}
            id={"char-selector"}
            className={"text-black"}
            defaultValue={selectedCharId}
            value={selectedCharId}
            onChange={(val) => router.push(`/calculator/${val.target.value}`)}>
          <option key={-1} value={-1}>None</option>
          {chars.map(x =>
              <option key={x.id} value={x.id}>
                {x.name}
              </option>)
          }
        </select>
      </>
  )
}