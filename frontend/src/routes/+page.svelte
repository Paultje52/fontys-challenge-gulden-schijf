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

  figure {
    width: fit-content;
    max-width: 94vw;
    margin: 0 auto;
  }
</style>

<script lang="ts">
  import type { Band } from "$lib/dto/Band";
  import type { Manager } from "$lib/dto/Manager";
  import type { ParsedSong } from "$lib/dto/ParsedSong";
  import type { Song } from "$lib/dto/Song";
  import type { Supplier } from "$lib/dto/Supplier";
  import fetchSearch from "./fetchSearch";
  import fetchSongContributer from "./fetchSongContributer";

  let isLoading = false;
  let songs: ParsedSong[];
  let fetchTime: number;
  let audioElements: Record<string, HTMLAudioElement> = {};
  let isAudioPlaying: Record<string, boolean> = {};

  async function handleSubmit(event: SubmitEvent) {
    const start = Date.now();
    event.preventDefault();

    isLoading = true;
    songs = undefined;

    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    const search = {
      title: formData.get("title") as string,
      band: formData.get("band") as string,
      manager: formData.get("manager") as string,
      supplier: formData.get("supplier") as string
    };

    const result = await fetchSearch(search);
    songs = await Promise.all(result.map((result) => parseSong(result)));

    isLoading = false;
    fetchTime = Date.now() - start;
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
  <figure>
    <table>
      <thead>
        <tr>
          <th>Titel</th>
          <th>Tijdsduur</th>
          <th>MP3</th>
          <th>Jaar van uitgifte</th>
          <th>Band</th>
          <th>Leverancier</th>
          <th>Manager</th>
        </tr>
      </thead>
      <tbody>
        {#each songs as song}
          <td>{song.title}</td>
          <td>{song.duration}</td>
          <td>
            <audio
              src="{song.mp3Path}.mp3?jwt={localStorage.getItem('jwt')}"
              bind:this={audioElements[song.id]}
            />
            {#if isAudioPlaying[song.id]}
              <button
                on:click={() => {
                  audioElements[song.id].pause();
                  isAudioPlaying[song.id] = false;
                }}
              >
                Pauzeer
              </button>
            {:else}
              <button
                on:click={() => {
                  audioElements[song.id].play();
                  isAudioPlaying[song.id] = true;
                }}
              >
                Speel af
              </button>
            {/if}
          </td>
          <td>{song.year}</td>
          <td><a href="/band/{song.band.id}">{song.band.name}</a></td>
          <td>
            <a href="/supplier/{song.supplier.id}">{song.supplier.name}</a>
          </td>
          <td><a href="/manager/{song.manager.id}">{song.manager.name}</a></td>
        {/each}
      </tbody>
    </table>
  </figure>
{/if}
