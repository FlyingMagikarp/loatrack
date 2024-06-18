import Image from 'next/image';
import aeromancer from '../../components/icons/classes/aeromancer.webp';
import arcanist from '../../components/icons/classes/arcanist.webp';
import artillerist from '../../components/icons/classes/artillerist.webp';
import artist from '../../components/icons/classes/artist.webp';
import bard from '../../components/icons/classes/bard.webp';
import berserker from '../../components/icons/classes/berserker.webp';
import breaker from '../../components/icons/classes/breaker.webp';
import deadeye from '../../components/icons/classes/deadeye.webp';
import deathblade from '../../components/icons/classes/deathblade.webp';
import destroyer from '../../components/icons/classes/destroyer.webp';
import glavier from '../../components/icons/classes/glavier.webp';
import gunlancer from '../../components/icons/classes/gunlancer.webp';
import gunslinger from '../../components/icons/classes/gunslinger.webp';
import machinist from '../../components/icons/classes/machinist.webp';
import paladin from '../../components/icons/classes/paladin.webp';
import reaper from '../../components/icons/classes/reaper.webp';
import scrapper from '../../components/icons/classes/scrapper.webp';
import shadowhunter from '../../components/icons/classes/shadowhunter.webp';
import sharpshooter from '../../components/icons/classes/sharpshooter.webp';
import slayer from '../../components/icons/classes/slayer.webp';
import sorceress from '../../components/icons/classes/sorceress.webp';
import souleater from '../../components/icons/classes/souleater.webp';
import soulfist from '../../components/icons/classes/soulfist.webp';
import striker from '../../components/icons/classes/striker.webp';
import summoner from '../../components/icons/classes/summoner.webp';
import wardancer from '../../components/icons/classes/wardancer.webp';

export interface IClassIcon {
  classString: string;
}

export function ClassIcon({ classString }: IClassIcon) {
  if (classString === '' || classString == null) {
    return null;
  }

  const cs = classString.toLowerCase();
  let ic = paladin;

  if (cs === 'aeromancer') {
    ic = aeromancer;
  } else if (cs === 'arcanist') {
    ic = arcanist;
  } else if (cs === 'artillerist') {
    ic = artillerist;
  } else if (cs === 'artist') {
    ic = artist;
  } else if (cs === 'bard') {
    ic = bard;
  } else if (cs === 'berserker') {
    ic = berserker;
  } else if (cs === 'breaker') {
    ic = breaker;
  } else if (cs === 'deadeye') {
    ic = deadeye;
  } else if (cs === 'deathblade') {
    ic = deathblade;
  } else if (cs === 'destroyer') {
    ic = destroyer;
  } else if (cs === 'glavier') {
    ic = glavier;
  } else if (cs === 'gunlancer') {
    ic = gunlancer;
  } else if (cs === 'gunslinger') {
    ic = gunslinger;
  } else if (cs === 'machinist') {
    ic = machinist;
  } else if (cs === 'paladin') {
    ic = paladin;
  } else if (cs === 'reaper') {
    ic = reaper;
  } else if (cs === 'scrapper') {
    ic = scrapper;
  } else if (cs === 'shadowhunter') {
    ic = shadowhunter;
  } else if (cs === 'sharpshooter') {
    ic = sharpshooter;
  } else if (cs === 'slayer') {
    ic = slayer;
  } else if (cs === 'sorceress') {
    ic = sorceress;
  } else if (cs === 'souleater') {
    ic = souleater;
  } else if (cs === 'soulfist') {
    ic = soulfist;
  } else if (cs === 'striker') {
    ic = striker;
  } else if (cs === 'summoner') {
    ic = summoner;
  } else if (cs === 'wardancer') {
    ic = wardancer;
  }

  return (
    <>
      <Image src={ic} width={50} height={50} alt={'Classicon ' + classString} />
    </>
  );
}
