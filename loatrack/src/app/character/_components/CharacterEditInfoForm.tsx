'use client'

import {useRouter} from "next/navigation";
import {useEffect, useState} from "react";
import {ICharacterDto, IContentDto, IContentSettingDto} from "@/lib/dtos";
import {USER_ID} from "@/lib/constants";
import {
  deleteCharacter,
  getClearableRaids,
  getContentSettings,
  updateCharacterInfo, updateContentSettingsAndRaids
} from "@/app/character/action";
import RaidSelector from "@/app/character/_components/RaidSelector";

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
  //content settings
  const [clearCD, setClearCD] = useState<boolean>(false);
  const [restedCD, setRestedCD] = useState<boolean>(false);
  const [clearGR, setClearGR] = useState<boolean>(false);
  const [restedGR, setRestedGR] = useState<boolean>(false);
  const [unasLS, setUnasLS] = useState<number>(0);
  const [raid1, setRaid1] = useState<number>(-1);
  const [raid2, setRaid2] = useState<number>(-1);
  const [raid3, setRaid3] = useState<number>(-1);
  const [raidsList, setRaidsList] = useState<IContentDto[]>([]);

  useEffect(() => {
    if(character){
      setName(character.name);
      setClassname(character.classname);
      setIlvl(character.ilvl);
      setNotesOverview(character.notesOverview);
      setNotes(character.notes);
      setCharContentSettings(character.id, character.ilvl);
    }
  }, [character]);

  async function setCharContentSettings(charId: number, iLvl: number){
    const cs = await getContentSettings(charId)
    setClearCD(cs.clearCD);
    setRestedCD(cs.restedCD);
    setClearGR(cs.clearGR);
    setRestedGR(cs.restedGR);
    setUnasLS(cs.unasLeapstone);
    if (cs.raidIds){
      setRaid1(cs.raidIds[0]??-1);
      setRaid2(cs.raidIds[1]??-1);
      setRaid3(cs.raidIds[2]??-1);
    }

    const raids = await getClearableRaids(iLvl);
    setRaidsList(raids);
  }

  async function onSave(){
    let dataCharacter:ICharacterDto = {
      id: character ? character.id : -1,
      userId: USER_ID,

      name: name,
      classname: classname,
      ilvl: ilvl,
      notesOverview: notesOverview,
      notes: notes
    }

    await handleContenUpdate();

    await updateCharacterInfo(dataCharacter).then(() =>{
      if(!character) {
        router.push('/roster');
      }
      router.refresh();
    });
  }

  async function handleContenUpdate(){
    if (!character){
      return;
    }
    let dataContentSettings:IContentSettingDto = {
      charId: character.id,
      clearCD: clearCD,
      restedCD: restedCD,
      clearGR: clearGR,
      restedGR: restedGR,
      unasLeapstone: unasLS,
      raidIds: [raid1, raid2, raid3]
    }

    await updateContentSettingsAndRaids(dataContentSettings);
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

  async function updateRaidSelection(raidId: number, raidCount: number){
    if (raidCount == 1){
      setRaid1(raidId);
    }

    if (raidCount == 2){
      setRaid2(raidId);
    }

    if (raidCount == 3){
      setRaid3(raidId);
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
                <label htmlFor="char-name" className="text-sm font-medium text-white block mb-2">Name</label>
                <input type="text" name="char-name" id="char-name"
                       className="shadow-sm bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                       placeholder="Character Name" required value={name} onChange={e => setName(e.target.value)}/>
              </div>
              <div className="col-span-6 sm:col-span-3">
                <label htmlFor="class-name" className="text-sm font-medium text-white block mb-2">Class</label>
                <input type="text" name="class-name" id="class-name"
                       className="shadow-sm bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                       placeholder="Paladin" required value={classname} onChange={e => setClassname(e.target.value)}/>
              </div>
              <div className="col-span-6 sm:col-span-3">
                <label htmlFor="ilvl" className="text-sm font-medium text-white block mb-2">iLvL</label>
                <input type="text" name="ilvl" id="ilvl"
                       className="shadow-sm bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                       placeholder="1580" required value={ilvl} onChange={e => setIlvl(Number(e.target.value))}/>
              </div>


              <div className="col-span-6 sm:col-span-3">
                <label htmlFor="notes-overview" className="text-sm font-medium text-white block mb-2">Notes
                  Overview</label>
                <input type="text" name="notes-overview" id="notes-overview"
                       className="shadow-sm bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                       placeholder="tldr" value={notesOverview} onChange={e => setNotesOverview(e.target.value)}/>
              </div>
              <div className="col-span-full">
                <label htmlFor="notes"
                       className="text-sm font-medium text-white block mb-2">Notes</label>
                <textarea name="notes" id="notes" rows={6}
                          className="bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-4"
                          placeholder="Notes..." value={notes} onChange={e => setNotes(e.target.value)}></textarea>
              </div>
            </div>


            {/*CD*/}
            {character &&
            <div className="grid grid-cols-6 gap-6 border-t-2 p-5">
              <div className="space-x-3">
                <label htmlFor="clearCD" className="ms-2 text-sm font-medium text-white">Clear Chaos Dungeons?</label>
                <input type="checkbox" name="clearCD" id="clearCD"
                       className="w-4 h-4 text-blue-600 rounded ring-offset-gray-800 bg-gray-700 border-gray-600"
                       checked={clearCD} onChange={e => setClearCD(e.target.checked)}/>
              </div>
              {clearCD &&
                  <div className="space-x-3">
                      <label htmlFor="restedCD" className="ms-2 text-sm font-medium text-white">Rested Chaos
                          Dungeons?</label>
                      <input type="checkbox" name="restedCD" id="restedCD"
                             className="w-4 h-4 text-blue-600 rounded ring-offset-gray-800 bg-gray-700 border-gray-600"
                             checked={restedCD} onChange={e => setRestedCD(e.target.checked)}/>
                  </div>
              }
            </div>
            }
            {/*GR*/}
            {character &&
            <div className="grid grid-cols-6 gap-6 border-t-2 p-5">
              <div className="space-x-3">
                <label htmlFor="clearGR" className="ms-2 text-sm font-medium text-white">Clear Guardian Raid?</label>
                <input type="checkbox" name="clearGR" id="clearGR"
                       className="w-4 h-4 text-blue-600 rounded ring-offset-gray-800 bg-gray-700 border-gray-600"
                       checked={clearGR} onChange={e => setClearGR(e.target.checked)}/>
              </div>
              {clearGR &&
                  <div className="space-x-3">
                      <label htmlFor="restedGR" className="ms-2 text-sm font-medium text-white">Rested Guardian
                          Raid?</label>
                      <input type="checkbox" name="restedGR" id="restedGR"
                             className="w-4 h-4 text-blue-600 rounded ring-offset-gray-800 bg-gray-700 border-gray-600"
                             checked={restedGR} onChange={e => setRestedGR(e.target.checked)}/>
                  </div>
              }
            </div>
            }
            {/*UNAS*/}
            {character &&
            <div className="grid grid-cols-6 gap-6 border-t-2 p-5">
              <div className="col-span-6 sm:col-span-3">
                <label htmlFor="unasLS" className="text-sm font-medium text-white block mb-2">Unas Leapstones</label>
                <input type="number" name="unasLS" id="unasLS"
                       className="w-20 shadow-sm bg-gray-50 border border-gray-300 text-black sm:text-sm rounded-lg focus:ring-cyan-600 focus:border-cyan-600 block w-full p-2.5"
                       placeholder="0" required value={unasLS} onChange={e => setUnasLS(Number(e.target.value))}/>
              </div>
            </div>
            }
            {/*Raids*/}
            {character &&
            <div className="grid grid-cols-6 gap-6 border-t-2 p-5">
              <div className="flex flex-col">
                <RaidSelector raids={raidsList} selectedRaid={raid1} raidCount={1} updateRaidSelection={updateRaidSelection}/>
                <RaidSelector raids={raidsList} selectedRaid={raid2} raidCount={2} updateRaidSelection={updateRaidSelection}/>
                <RaidSelector raids={raidsList} selectedRaid={raid3} raidCount={3} updateRaidSelection={updateRaidSelection}/>
              </div>
            </div>
            }

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