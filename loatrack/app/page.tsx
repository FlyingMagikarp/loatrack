export default async function IndexPage() {
  const resp = await fetch('https://api.sampleapis.com/wines/reds');
  const json = await resp.json();

  return (
    <main className="flex flex-1 flex-col p-4 md:p-6">
      <div className="flex items-center mb-8">
        <h1 className="font-semibold text-lg md:text-2xl">Root</h1>
      </div>
      <div className="w-full mb-4">
        Lorem Ipsum is simply dummy text of the printing and typesetting in
      </div>
      <div>
        {json.toString()}
      </div>
    </main>
  );
}
