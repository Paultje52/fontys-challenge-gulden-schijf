<style>
  .container {
    margin-top: 1rem;
    overflow-x: hidden;
  }
  .flex {
    display: flex;
    gap: 0 1rem;
  }
  .flex div {
    width: 25%;
  }
  @media only screen and (max-width: 768px) {
    .flex {
      flex-wrap: wrap;
      justify-content: center;
    }
    .flex div {
      width: calc(50% - 0.5rem);
    }
  }
  @media only screen and (max-width: 480px) {
    .flex div {
      width: 100%;
    }
  }

  form {
    margin-bottom: 3rem;
  }
</style>

<script lang="ts">
  import SongsTable from "$lib/SongsTable.svelte";
  import type { ParsedSong } from "$lib/dto/ParsedSong";
  import songSearch from "$lib/songsSearch";

  let isLoading = false;
  let songs: ParsedSong[];
  let fetchTime: number;

  async function handleSubmit(event: SubmitEvent) {
    const start = Date.now();
    event.preventDefault();

    isLoading = true;
    songs = undefined;

    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    songs = await songSearch({
      title: formData.get("title") as string,
      band: formData.get("band") as string,
      manager: formData.get("manager") as string,
      supplier: formData.get("supplier") as string
    });

    isLoading = false;
    fetchTime = Date.now() - start;
  }
</script>

<div class="container">
  <hgroup>
    <h1>Zoeken</h1>
    <h2>Zoek naar liedjes op basis van één of meerdere criteria.</h2>
  </hgroup>

  <form on:submit={handleSubmit}>
    <div class="flex">
      <div>
        <label for="title">Titel</label>
        <input
          type="text"
          id="title"
          name="title"
          placeholder="Titel"
          autocomplete="off"
        />
      </div>

      <div>
        <label for="band">Bandnaam</label>
        <input
          type="text"
          id="band"
          name="band"
          placeholder="Bandnaam"
          autocomplete="off"
        />
      </div>

      <div>
        <label for="manager">Manager</label>
        <input
          type="text"
          id="manager"
          name="manager"
          placeholder="Manager"
          autocomplete="off"
        />
      </div>

      <div>
        <label for="supplier">Leverancier</label>
        <input
          type="text"
          id="supplier"
          name="supplier"
          placeholder="Leverancier"
          autocomplete="off"
        />
      </div>
    </div>

    <center>
      <button type="submit" aria-busy={isLoading}>Zoeken</button>
    </center>
  </form>
</div>

{#if !songs}
  <p />
{:else if songs.length === 0}
  <center>
    <p><i>Geen liedjes gevonden</i></p>
  </center>
{:else}
  <div class="container">
    <p>
      Aantal resultaten: <b>{songs.length}</b> <br />
      Tijd nodig om te zoeken: <b>{fetchTime}ms</b>
    </p>
  </div>
  <SongsTable {songs} />
{/if}
