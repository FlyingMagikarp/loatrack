'use client'

import {ICharacterDto, ICharacterInventoryFlatDto, ICharacterInventoryPresentationDto} from '@/lib/dtos';
import { ClassIcon } from '@/components/ui/ClassIcon';
import { useRouter } from 'next/navigation';

interface IItemRowProps {
  item: ICharacterInventoryFlatDto,
}

export function ItemRow({ item }: IItemRowProps) {

  return (
    <tr key={item.itemId} className="hover:bg-gray-500">
      <td className="roster-table-cell">&nbsp;</td>
      <td className="roster-table-cell">{item.itemName}</td>
      <td className="roster-table-cell">{item.amount}</td>
    </tr>
  );
}