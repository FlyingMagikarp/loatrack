import {redirect} from "next/navigation";
import {getCharacters} from "@/app/action";

export default async function Home() {
  const roster = await getCharacters();
  if(roster && roster.length > 0){
    redirect('/overview')
  } else {
    redirect(`/roster`)
  }

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      ._.
    </main>
  );
}
