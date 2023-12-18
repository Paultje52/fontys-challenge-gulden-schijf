import type { Song } from "$lib/dto/Song";
import getApiUrl from "$lib/util/getApiUrl";

export default async function fetchSearch(search: {
  title: string;
  band: string;
  manager: string;
  supplier: string;
}): Promise<Song[]> {
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
