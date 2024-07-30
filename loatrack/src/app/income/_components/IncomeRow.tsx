import {IIncomeItemDto} from "@/lib/dtos";


export interface IIncomeRowProps{
  item: IIncomeItemDto
}

export default function IncomeRow({item}:IIncomeRowProps){

  return (
      <>
        <td>{item.itemName}{item.bound ? ' (Bound)' : ''}</td>
        <td suppressHydrationWarning={true}>{item.amount.toLocaleString()}</td>
      </>
  );
}