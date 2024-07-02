'use client'

import {useRouter} from "next/navigation";
import {FormEvent, useEffect, useState} from "react";
import {ICharacterDto} from "@/lib/dtos";
import {USER_ID} from "@/lib/constants";
import {deleteCharacter, updateCharacterInfo} from "@/app/character/action";

export interface ICharacterEditInfoFormProps {
  character?: ICharacterDto,
}

export default function CharacterEditInfoForm({ character }: ICharacterEditInfoFormProps) {
  const router = useRouter();

  const [name, setName] = useState<string>('');
  const [classname, setClassname] = useState<string>('');
  const [ilvl, setIlvl] = useState<number>(0);
  const [notesOverview, setNotesOverview] = useState<string>('');
  const [notes, setNotes] = useState<string>('');

  useEffect(() => {
    if(character){
      setName(character.name);
      setClassname(character.classname);
      setIlvl(character.ilvl);
      setNotesOverview(character.notesOverview);
      setNotes(character.notes);
    }
  }, [character]);



  async function onSave(){
    let data:ICharacterDto = {
      id: character ? character.id : -1,
      userId: USER_ID,

      name: name,
      classname: classname,
      ilvl: ilvl,
      notesOverview: notesOverview,
      notes: notes
    }

    await updateCharacterInfo(data).then(() =>{
      if(!character) {
        router.push('/roster');
      }
      router.refresh();
    });
  }

  async function onDelete(){
    if (character){
      await deleteCharacter(character.id).then(() => {router.push('/roster'); router.refresh();});
    }
  }

  async function onEditInv(){
    if (character){
      router.push(`/inventory/character/${character.id}`);
    }

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

              <div className="grid grid-cols-6 gap-6">
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="char-name" className="text-sm font-medium text-gray-900 block mb-2">Name</label>
                  <input type="text" name="char-name" id="char-name"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="Character Name" required value={name} onChange={e => setName(e.target.value)}/>
                </div>
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="class-name" className="text-sm font-medium text-gray-900 block mb-2">Class</label>
                  <input type="text" name="class-name" id="class-name"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="Paladin" required value={classname} onChange={e => setClassname(e.target.value)}/>
                </div>
                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="ilvl" className="text-sm font-medium text-gray-900 block mb-2">iLvL</label>
                  <input type="text" name="ilvl" id="ilvl"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="1580" required value={ilvl} onChange={e => setIlvl(Number(e.target.value))}/>
                </div>


                <div className="col-span-6 sm:col-span-3">
                  <label htmlFor="notes-overview" className="text-sm font-medium text-gray-900 block mb-2">Notes
                    Overview</label>
                  <input type="text" name="notes-overview" id="notes-overview"
                         className="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                         placeholder="tldr" value={notesOverview} onChange={e => setNotesOverview(e.target.value)}/>
                </div>
                <div className="col-span-full">
                  <label htmlFor="notes"
                         className="text-sm font-medium text-gray-900 block mb-2">Notes</label>
                  <textarea name="notes" id="notes" rows="6"
                            className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-4"
                            placeholder="Notes..." value={notes} onChange={e => setNotes(e.target.value)}></textarea>
                </div>
              </div>

            <div className="flex flex-row justify-between">
              <div className="">
                <div className="">
                  <button
                      className="btn-primary"
                      onClick={onSave}>Save
                  </button>
                  {character &&
                      <div className="">
                          <button
                              className="btn-secondary"
                              onClick={onEditInv}>Edit Inventory
                          </button>
                      </div>

                  }
                </div>
              </div>


              {character &&
                  <div className="">
                      <button
                          className="btn-destructive"
                          onClick={onDelete}>Delete
                      </button>
                  </div>

              }

          </div>

        </div>
      </div>
</main>
)
}