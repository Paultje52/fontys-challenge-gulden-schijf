import type { Song } from "$lib/dto/Song";
import getApiUrl from "$lib/util/getApiUrl";
import type { Band } from "./dto/Band";
import type { Manager } from "./dto/Manager";
import type { ParsedSong } from "./dto/ParsedSong";
import type { Supplier } from "./dto/Supplier";
import fetchSongContributer from "./fetchSongContributer";

type Params = {
  title?: string;
  band?: string;
  manager?: string;
  supplier?: string;
};

export default async function songSearch(
  search: Params
): Promise<ParsedSong[]> {
  const songs = await fetchSongs(search);
  return await Promise.all(songs.map(parseSong));
}

async function fetchSongs(search: Params): Promise<Song[]> {
  const urlParams = new URLSearchParams();
  for (const [key, value] of Object.entries(search)) {
    if (value) {
      urlParams.append(key, value);
    }
  }

  const response = await fetch(
    getApiUrl("/song/search?") + urlParams.toString(),
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem("jwt")
      }
    }
  );

  return response.ok ? await response.json() : [];
}

async function parseSong(song: Song): Promise<ParsedSong> {
  const [band, supplier, manager] = await Promise.all([
    fetchSongContributer<Band>("band", song.songBandID),
    fetchSongContributer<Supplier>("supplier", song.songSupplierId),
    fetchSongContributer<Manager>("manager", song.songManagerId)
  ]);

  return {
    id: song.songId,
    title: song.songTitle,
    duration: song.songDuration,
    mp3Path: song.songFilePath,
    year: song.songYear,
    band: {
      id: band.bandId,
      name: band.name
    },
    manager: {
      id: manager.managerId,
      name: manager.name
    },
    supplier: {
      id: supplier.supplierId,
      name: supplier.name
    }
  };
}
