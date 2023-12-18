<style>
  .container {
    margin-top: 1rem;
  }
  .loader {
    display: flex;
    justify-content: center;
    align-items: center;
    height: var(--app-height);
  }
  .error {
    color: var(--del-color);
  }
  h1 {
    margin-bottom: 0.5rem;
  }
</style>

<script lang="ts">
  import { page } from "$app/stores";
    import SongsTable from "$lib/SongsTable.svelte";
  import type { Band } from "$lib/dto/Band";
  import type { ParsedSong } from "$lib/dto/ParsedSong";
  import fetchSongContributer from "$lib/fetchSongContributer";
  import songSearch from "$lib/songsSearch";

  let band: Band;
  let songs: ParsedSong[];
  let bandId = $page.params.id;

  $: (async () => {
    if (typeof window === "undefined") return;
    band = await fetchSongContributer<Band>("band", Number(bandId));
    songs = await songSearch({
      title: null,
      band: band.name,
      manager: null,
      supplier: null
    });
  })();

  $: console.log(band);
</script>

{#if band === undefined}
  <div class="loader">
    <p>Laden...</p>
  </div>
{:else if band === null}
  <div class="loader">
    <p class="error">Band niet gevonden</p>
  </div>
{:else}
  <div class="container">
    <h1>Band informatie</h1>
    <p>
      ID: <b>{band.bandId}</b> <br />
      Naam: <b>{band.name}</b> <br />
      Heeft speciale instrumenten (anders dan drums, keyboard en (bas)gitaar):
      <b>{band.hasSpecialInstruments ? "Ja" : "Nee"}</b> <br />
    </p>

    {#if !songs}
      <center>
        <p><i>Liedjes laden...</i></p>
      </center>
    {:else}
      {#if songs.length === 0}
        <p>Deze artiest heeft nog <b class="error">geen</b> liedjes.</p>
      {:else}
        <p>Aantal liedjes: <b>{songs.length}</b></p>
        <SongsTable {songs} />
      {/if}
    {/if}
  </div>
{/if}
