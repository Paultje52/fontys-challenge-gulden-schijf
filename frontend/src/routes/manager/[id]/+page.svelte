<style>
  .container {
    margin: 1rem;
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
  import type { Manager } from "$lib/dto/Manager";
  import type { ParsedSong } from "$lib/dto/ParsedSong";
  import fetchSongContributer from "$lib/fetchSongContributer";
  import songSearch from "$lib/songsSearch";

  let manager: Manager;
  let songs: ParsedSong[];
  let managerId = $page.params.id;

  $: (async () => {
    if (typeof window === "undefined") return;
    manager = await fetchSongContributer<Manager>("manager", Number(managerId));
    songs = await songSearch({
      manager: manager.name
    });
  })();
</script>

{#if manager === undefined}
  <div class="loader">
    <p>Laden...</p>
  </div>
{:else if manager === null}
  <div class="loader">
    <p class="error">Manager niet gevonden</p>
  </div>
{:else}
  <div class="container">
    <h1>Manager informatie</h1>
    <p>
      ID: <b>{manager.managerId}</b> <br />
      Naam: <b>{manager.name}</b> <br />
      Kosten: <b>€{manager.hourlyRate}</b> per uur
    </p>

    <p>
      <b>Adresgegevens</b> <br />
      {manager.adressStreet}
      {manager.adressNumber} <br />
      {manager.adressZip}
      {manager.adressCity} <br />
      {manager.adressCountry} <br />
    </p>

    {#if !songs}
      <center>
        <p><i>Liedjes laden...</i></p>
      </center>
    {:else if songs.length === 0}
      <p>Deze artiest heeft nog <b class="error">geen</b> liedjes.</p>
    {:else}
      <p>Aantal liedjes: <b>{songs.length}</b></p>
      <SongsTable {songs} />
    {/if}
  </div>
{/if}
