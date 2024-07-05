import RosterInventory from "@/app/inventory/roster/_components/RosterInventory";

export default async function InventoryRoster() {

  return (
      <main>
        <div className="bg-gray-700 rounded-lg relative m-10">
          <div className="flex items-start justify-between p-5 border-b rounded-t">
            <h3 className="text-xl font-semibold">
              Roster Inventory
            </h3>
          </div>
          <RosterInventory />
        </div>
      </main>
  );
}