<style>
  figure {
    width: fit-content;
    max-width: 94vw;
    margin: 0 auto;
  }
  td {
    white-space: nowrap;
  }
  button {
    margin-bottom: 0;
    padding: 0.2rem 0.5rem;
  }
</style>

<script lang="ts">
  import type { ParsedSong } from "./dto/ParsedSong";
  import popup from "./util/popup";

  export let songs: ParsedSong[];

  let audioElement: HTMLAudioElement;
  let audioPlaying: false | number = false;

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

<audio bind:this={audioElement} autoplay />

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
        <tr>
          <td>{song.title}</td>
          <td>{song.duration}</td>
          <td>
            {#if audioPlaying === song.id}
              <button
                on:click={() => {
                  audioElement.pause();
                  audioPlaying = false;
                }}
                class="secondary"
              >
                Pauzeer
              </button>
            {:else}
              <button
                on:click={() => {
                  audioElement.setAttribute("src", `${song.mp3Path}.mp3?jwt=${localStorage.getItem("jwt")}`);
                  audioElement.onload = () => {
                    console.log(1);
                    audioElement.play();
                  };
                  audioPlaying = song.id;
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
        </tr>
      {/each}
    </tbody>
  </table>
</figure>
