import getApiUrl from "$lib/util/getApiUrl";

export const GET = async ({ params, url }): Promise<Response> => {
  const res = await fetch(getApiUrl(`/song/${params.id}.mp3`), {
    headers: {
      Authorization: url.searchParams.get("jwt").toString()
    }
  });

  if (!res.ok) {
    return new Response(await res.text(), {
      status: res.status,
      statusText: res.statusText,
      headers: {
        "Content-Type": res.headers.get("Content-Type")
      }
    });
  }

  return new Response(await res.blob(), {
    status: res.status,
    statusText: res.statusText,
    headers: {
      "Content-Type": res.headers.get("Content-Type"),
      "Content-Length": res.headers.get("Content-Length"),
      "Content-Disposition": res.headers.get("Content-Disposition")
    }
  });
};
