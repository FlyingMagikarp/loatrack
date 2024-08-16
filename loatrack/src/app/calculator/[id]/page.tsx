import CharSelector from "@/app/calculator/_components/CharSelector";
import {getCharacters, getUpgrade} from "@/app/calculator/actions";
import CalcTable from "@/app/calculator/_components/CalcTable";


export default async function CalculatorChar({params}: any) {
  const chars = await getCharacters();
  const upgradeDto = await getUpgrade(params.id)

  return (
      <main>
        <div>
          <CharSelector chars={chars} selectedCharId={params.id}/>
        </div>
        <div>
          <CalcTable data={upgradeDto} />
        </div>
      </main>
  );
}
