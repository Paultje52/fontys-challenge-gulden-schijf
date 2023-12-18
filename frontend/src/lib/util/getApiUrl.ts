// @ts-expect-error - Ignore the fact that PUBLIC_API_URL is not defined, it is defined in the env
import { PUBLIC_API_URL as apiUrl } from "$env/static/public";

export default function getApiUrl(path: string): string {
  const url = new URL(path, apiUrl);
  return url.toString();
}
