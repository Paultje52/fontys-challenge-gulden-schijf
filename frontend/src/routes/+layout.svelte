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
</script>

<!-- The place for the rest of the content -->
<slot />
