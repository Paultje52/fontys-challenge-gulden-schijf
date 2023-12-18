<style>
  :root {
    --font-size: 1rem;
  }
  @media (min-width: 800px) {
    :root {
      --font-size: 1.08rem;
    }
  }
  @media (min-width: 1600px) {
    :root {
      --font-size: 1.1rem;
    }
  }
</style>

<script lang="ts">
  import "@picocss/pico";
  import { onDestroy, onMount } from "svelte";
  import { page } from "$app/stores";
  import { goto } from "$app/navigation";
  import decodeJwt from "$lib/util/decodeJwt";
  import renewJwt from "$lib/util/renewJwt";

  let renewTimeout: NodeJS.Timeout;

  onMount(() => {
    const jwt = localStorage.getItem("jwt");
    const decoded = jwt && decodeJwt(jwt);
    const isLoggedIn = jwt && decoded.exp > Date.now() / 1000;

    if (!isLoggedIn && !$page.url.pathname.startsWith("/auth")) {
      goto("/auth/login");
      return;
    }

    // Renew if the JWT is about to expire (1m)
    const nextRenewAt = decoded.exp * 1000 - 60 * 1000;

    renewTimeout = setTimeout(async () => {
      const success = await renewJwt();
      if (!success) {
        goto("/auth/login");
        return;
      }
    }, 30);
  });

  onDestroy(() => {
    clearTimeout(renewTimeout);
  });
</script>

<!-- The place for the rest of the content -->
<slot />
