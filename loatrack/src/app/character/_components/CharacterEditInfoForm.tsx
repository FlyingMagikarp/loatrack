'use client'

import {useRouter} from "next/navigation";
import {FormEvent} from "react";
import {ICharacterDto} from "@/lib/dtos";
import {USER_ID} from "@/lib/constants";
import {updateCharacterInfo} from "@/app/character/action";

export interface ICharacterEditInfoFormProps {
  character?: ICharacterDto,
}

export default function CharacterEditInfoForm({ character }: ICharacterEditInfoFormProps) {
  const router = useRouter();

  async function onSubmit(event: FormEvent<HTMLFormElement>){
    event.preventDefault();

    const formData = new FormData(event.currentTarget);

    let data:ICharacterDto = {
      id: character ? character.id : -1,
      userId: USER_ID,

      name: formData.get("char-name")?.toString()??'',
      classname: formData.get("class-name")?.toString()??'',
      ilvl: Number(formData.get("ilvl")?.toString() ??'0'),
      notesOverview: formData.get("notes-overview")?.toString()??'',
      notes: formData.get("notes")?.toString()??''
    }

    await updateCharacterInfo(data).then(()=>router.push('/roster'));

  }

  return (
      <main>
        <div className="bg-gray-700 rounded-lg relative m-10">
          <div className="flex items-start justify-between p-5 border-b rounded-t">
            <h3 className="text-xl font-semibold">
              {character ? character.name : 'New Character'}
            </h3>
          </div>

          <div className="p-6 space-y-6">
            <form onSubmit={onSubmit}>
              <div className="grid grid-cols-6 gap-6">
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="char-name" className="text-sm font-medium text-gray-900 block mb-2">Name</label>
                  <input type="text" name="char-name" id="char-name"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="Character Name" required defaultValue={character?.name}/>
                </div>
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="class-name" className="text-sm font-medium text-gray-900 block mb-2">Class</label>
                  <input type="text" name="class-name" id="class-name"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="Paladin" required defaultValue={character?.classname}/>
                </div>
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="ilvl" className="text-sm font-medium text-gray-900 block mb-2">iLvL</label>
                  <input type="text" name="ilvl" id="ilvl"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="1580" required defaultValue={character?.ilvl}/>
                </div>


                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="notes-overview" className="text-sm font-medium text-gray-900 block mb-2">Notes
                    Overview</label>
                  <input type="text" name="notes-overview" id="notes-overview"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="tldr" defaultValue={character?.notesOverview}/>
                </div>
                <div className="col-span-full">
                  <label htmlFor="notes"
                         className="text-sm font-medium text-gray-900 block mb-2">Notes</label>
                  <textarea name="notes" id="notes" rows="6"
                            className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-4"
                            placeholder="Notes..." defaultValue={character?.notes}></textarea>
                </div>
              </div>

              <div>
                <button
                    className="btn-primary"
                    type="submit">Save
                </button>
              </div>
            </form>
          </div>
        </div>
      </main>
  )
}