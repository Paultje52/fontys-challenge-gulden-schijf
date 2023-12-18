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
  import type { LoginResponse } from "$lib/dto/auth/LoginResponse";
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

    const loginResponse = await fetchLogin(username, password);
    if (loginResponse.success === false) {
      isLoading = false;
      showError = true;
      return;
    }

    localStorage.setItem("name", loginResponse.name);
    localStorage.setItem("jwt", loginResponse.jwt);

    isLoading = false;
    goto("/");
  }

  async function fetchLogin(
    userName: string,
    password: string
  ): Promise<LoginResponse> {
    const response = await fetch(getApiUrl("/auth/login"), {
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
    <h1>Login</h1>
    <h2>Login om door te gaan</h2>
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
      <p class="error">Email en/of wachtwoord is onjuist</p>
    {/if}

    <button type="submit" aria-busy={isLoading}>Login</button>
  </form>
</div>
