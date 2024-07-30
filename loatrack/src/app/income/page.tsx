import IncomeTable from "@/app/income/_components/IncomeTable";
import {getIncome} from "@/app/income/actions";


export default async function Income() {
  const data = await getIncome();

  return (
      <main>
        <div>
          {data.map(x => <div key={x.charId}><IncomeTable data={x}/></div>)}
        </div>
      </main>
  )
}