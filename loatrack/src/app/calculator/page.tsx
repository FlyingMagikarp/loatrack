import {getCharacters} from "@/app/calculator/actions";
import CharSelector from "@/app/calculator/_components/CharSelector";


export default async function Calculator() {
  const chars = await getCharacters();

  return (
      <main>
        <div>
          <CharSelector chars={chars} />
        </div>
      </main>
  )
}