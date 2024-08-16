import {IUpgradeItemDto} from "@/lib/dtos";


export interface ICalcRowProps{
  item: IUpgradeItemDto
}

export default function CalcRow({item}:ICalcRowProps){

  return (
      <tr>
        <td>{item.itemName.toLocaleString()}</td>
        <td>{item.base.toLocaleString()}</td>
        <td>{item.have.toLocaleString()}</td>
        <td>{item.diff.toLocaleString()}</td>
        <td>{item.income.toLocaleString()}</td>
        <td>{item.time.toLocaleString()}</td>
      </tr>
  );
}