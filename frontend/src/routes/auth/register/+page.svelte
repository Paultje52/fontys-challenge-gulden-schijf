<style>
  button {
    width: 100% !important;
    margin-top: 1rem;
  }
  .error {
    color: var(--del-color);
  }
</style>

<script lang="ts">
  import { goto } from "$app/navigation";
  import type { RegisterResponse } from "$lib/dto/RegisterResponse";
  import getApiUrl from "$lib/util/getApiUrl";

  let isLoading = false;
  let showError = false;

  async function onSubmit(event: SubmitEvent) {
    event.preventDefault();
    isLoading = true;

    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    const username = formData.get("username") as string;
    const password = formData.get("password") as string;

    const RegisterResponse = await fetchRegister(username, password);
    if (RegisterResponse.success === false) {
      isLoading = false;
      showError = true;
      return;
    }

    localStorage.setItem("name", RegisterResponse.name);
    localStorage.setItem("jwt", RegisterResponse.jwt);

    isLoading = false;
    goto("/");
  }

  async function fetchRegister(
    userName: string,
    password: string
  ): Promise<RegisterResponse> {
    const response = await fetch(getApiUrl("/auth/register"), {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ userName, password })
    });

    if (!response.ok)
      return {
        success: false,
        name: null,
        jwt: null
      };

    return await response.json();
  }
</script>

<div>
  <hgroup>
    <h1>Register</h1>
    <h2>Register om door te gaan</h2>
  </hgroup>

  <form on:submit={onSubmit}>
    <label for="username">Gebruikersnaam</label>
    <input
      type="text"
      id="username"
      name="username"
      placeholder="Gebruikersnaam"
      required
    />

    <label for="password">Wachtwoord</label>
    <input
      type="password"
      id="password"
      name="password"
      placeholder="Wachtwoord"
      autocomplete="current-password"
      required
    />

    {#if showError}
      <p class="error">Gebruikersnaam bestaat al</p>
    {/if}

    <button type="submit" aria-busy={isLoading}>Register</button>
  </form>
</div>
