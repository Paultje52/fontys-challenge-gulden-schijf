<style>
  main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    min-height: calc(100vh - 8rem);
  }

  footer {
    margin: 2rem auto;
  }
</style>

<script>
  import { page } from "$app/stores";
</script>

<svelte:head>
  <meta name="robots" content="none" />
</svelte:head>

<nav class="container-fluid">
  <ul>
    <li>
      <a href="/">D'n Gulden Schijf</a>
    </li>
  </ul>
  <ul>
    <li>
      <a href="/auth/register">
        {#if $page.route?.id === "/auth/register"}
          <b>Registreren</b>
        {:else}
          Registreren
        {/if}
      </a>
    </li>
    <li>
      <a href="/auth/login">
        {#if $page.route?.id === "/auth/login"}
          <b>Inloggen</b>
        {:else}
          Inloggen
        {/if}
      </a>
    </li>
  </ul>
</nav>

<main class="container">
  <article class="grid">
    <!-- The place for the rest of the content -->
    <slot />
  </article>
</main>

<footer class="container">
  <small>
    © {new Date().getFullYear()} D'n Gulden Schijf
  </small>
</footer>
