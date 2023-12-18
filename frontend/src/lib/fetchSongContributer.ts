import getApiUrl from "$lib/util/getApiUrl";

export default async function fetchSongContributer<T>(
  type: "band" | "supplier" | "manager",
  id: number
): Promise<T> {
  const res = await fetch(getApiUrl(`/${type}/${id}`), {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("jwt")
    }
  });

  return res.ok ? await res.json() : null;
}
