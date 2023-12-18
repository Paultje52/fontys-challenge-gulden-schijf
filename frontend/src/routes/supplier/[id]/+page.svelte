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
  import type { ParsedSong } from "$lib/dto/ParsedSong";
  import type { Supplier } from "$lib/dto/Supplier";
  import fetchSongContributer from "$lib/fetchSongContributer";
  import songSearch from "$lib/songsSearch";

  let supplier: Supplier;
  let songs: ParsedSong[];
  let supplierId = $page.params.id;

  $: (async () => {
    if (typeof window === "undefined") return;
    supplier = await fetchSongContributer<Supplier>(
      "supplier",
      Number(supplierId)
    );
    songs = await songSearch({
      supplier: supplier.name
    });
  })();
</script>

{#if supplier === undefined}
  <div class="loader">
    <p>Laden...</p>
  </div>
{:else if supplier === null}
  <div class="loader">
    <p class="error">Leverancier niet gevonden</p>
  </div>
{:else}
  <div class="container">
    <h1>Leverancier informatie</h1>
    <p>
      ID: <b>{supplier.supplierId}</b> <br />
      Naam: <b>{supplier.name}</b> <br />
      Gemidelde levertijd: <b>{supplier.averageDeliveryTime} dagen</b>
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
