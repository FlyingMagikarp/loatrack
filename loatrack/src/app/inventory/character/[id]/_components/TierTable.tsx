
export interface ITierTableProps{
  tier: number
}

export default async function TierTable({tier}:ITierTableProps){

  return (
      <>
        {tier}
      </>
  );
}