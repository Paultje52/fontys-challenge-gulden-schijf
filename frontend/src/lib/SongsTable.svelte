<style>
  figure {
    width: fit-content;
    max-width: 94vw;
    margin: 0 auto;
  }
  td {
    white-space: nowrap;
  }
</style>

<script lang="ts">
  import type { ParsedSong } from "./dto/ParsedSong";
  import popup from "./util/popup";

  export let songs: ParsedSong[];

  let audioElements: Record<string, HTMLAudioElement> = {};
  let isAudioPlaying: Record<string, boolean> = {};

  function handlePopup(event: Event) {
    event.preventDefault();
    event.stopImmediatePropagation();
    event.stopPropagation();

    const link = event.target as HTMLAnchorElement;
    const url = link.href;

    popup(url, {
      width: 800,
      height: 1000
    });
  }
</script>

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
        <td>
          <a href="/band/{song.band.id}" on:click={handlePopup}>
            {song.band.name}
          </a>
        </td>
        <td>
          <a href="/supplier/{song.supplier.id}" on:click={handlePopup}>
            {song.supplier.name}
          </a>
        </td>
        <td>
          <a href="/manager/{song.manager.id}" on:click={handlePopup}>
            {song.manager.name}
          </a>
        </td>
      {/each}
    </tbody>
  </table>
</figure>
