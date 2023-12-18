import type { Song } from "$lib/dto/Song";
import getApiUrl from "$lib/util/getApiUrl";
import type { ParsedSong } from "./dto/ParsedSong";

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

function parseSong(song: Song): ParsedSong {
  return {
    id: song.songId,
    title: song.songTitle,
    duration: song.songDuration,
    mp3Path: song.songFilePath,
    year: song.songYear,
    band: {
      id: song.songBandId,
      name: song.songBandName
    },
    manager: {
      id: song.songManagerId,
      name: song.songManagerName
    },
    supplier: {
      id: song.songSupplierId,
      name: song.songSupplierName
    }
  };
}
