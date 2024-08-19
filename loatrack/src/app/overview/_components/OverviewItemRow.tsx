'use client'

import { IOverviewCharacterData } from '@/lib/dtos';
import { useRouter } from 'next/navigation';

interface IOverviewItemRowProps {
  data: IOverviewCharacterData,
  isCharacter?: boolean,
}

export function OverviewItemRow({ data, isCharacter = false }: IOverviewItemRowProps) {
  const router = useRouter();

  if(!data || data.inventory.length < 4) {
    console.log('Error Char inv')
    console.log(data);
    return <></>;
  }

  return (
      <>
        {isCharacter && (<td className="roster-table-cell" onClick={() => router.push(`/character/${data.charId}`)}>{data.name}</td>)}
        {!isCharacter && (<td className="roster-table-cell">{data.name}</td>)}
        <td className="roster-table-cell" suppressHydrationWarning={true}>{data.inventory[0].amount.toLocaleString()}</td>
        <td className="roster-table-cell" suppressHydrationWarning={true}>{data.inventory[1].amount.toLocaleString()}</td>
        <td className="roster-table-cell" suppressHydrationWarning={true}>{data.inventory[2].amount.toLocaleString()}</td>
        <td className="roster-table-cell" suppressHydrationWarning={true}>{data.inventory[3].amount.toLocaleString()}</td>
      </>
  );
}