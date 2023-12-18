import type { JwtContent } from "$lib/dto/JwtContent";

export default function decodeJwt(jwt: string): JwtContent {
  const [, payload] = jwt.split(".");
  const decoded = JSON.parse(atob(payload));
  return decoded;
}
