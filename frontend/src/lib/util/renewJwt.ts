import type { RenewJwt } from "$lib/dto/auth/RenewResponse";
import decodeJwt from "./decodeJwt";
import getApiUrl from "./getApiUrl";

export default async function renewJwt(): Promise<boolean> {
  const jwt = localStorage.getItem("jwt");
  if (!jwt) return false;

  const { exp } = decodeJwt(jwt);
  const now = Date.now() / 1000;
  if (now > exp) return false;

  const renewResponse = await fetchRenew(jwt);
  if (!renewResponse.success) return false;

  localStorage.setItem("jwt", renewResponse.jwt);
  localStorage.setItem("name", renewResponse.name);
  return true;
}

async function fetchRenew(jwt: string): Promise<RenewJwt> {
  const response = await fetch(getApiUrl("/auth/renew"), {
    method: "POST",
    headers: {
      Authorization: jwt
    }
  });

  if (!response.ok)
    return {
      success: false,
      name: null,
      jwt: null
    };

  return response.json();
}
