'use client'

import { IInventoryPosFlatDto } from '@/lib/dtos';
import React from "react";

interface IItemRowProps {
  item: IInventoryPosFlatDto,
  updateInv: (invPos: IInventoryPosFlatDto) => void,
}

export function ItemRow({ item, updateInv }: IItemRowProps) {
  const [amount, setAmount] = React.useState<number>(item.amount)

  const updateValue = (newVal: string) => {
    item.amount = parseInt(newVal) || 0;
    setAmount(parseInt(newVal));
    updateInv(item);
  }

  return (
      <>
        <td className="roster-table-cell">&nbsp;</td>
        <td className="roster-table-cell">{item.itemName}</td>
        <td className="roster-table-cell">
          <input type="text" name="char-name" id="char-name"
                 className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600  p-2.5"
                 placeholder="Amount" required value={amount} onChange={e => updateValue(e.target.value)}/>
        </td>
      </>
  );
}