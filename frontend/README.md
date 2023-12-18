# Sveltekit-template

My Sveltekit template (using [NodeJS](https://nodejs.org) and [Yarn](https://yarnpkg.com/)). This template includes [PicoCSS](https://picocss.com/) and [Prisma](https://prisma.io/). It's built using [Vite](https://vitejs.dev/).

## .env

Rename `.env.example` to `.env` and fill in the environment variables.

## Scripts

This template includes the dev server, building, prettier, eslint and svelte-check. You can use the following yarn scripts.

- `yarn dev`
- `yarn build`
- `yarn start` (requires `yarn build`)
- Prettier: `yarn format` and `yarn format:fix`
- Eslint: `yarn lint` and `yarn lint:fix`
- Svelte-check: `yarn svelte:check`
- Svelte-kit syncing: `yarn svelte:sync`
- For [prisma commands](https://www.prisma.io/docs/reference/api-reference/command-reference), just put `yarn` before it (`yarn prisma format` for example).

## PWA

This template is PWA-ready. Fill in the blanks in `static/manifest.json` and add the following icons in the folder `static/icon`.

- logo.webp (256x256)
- logo.png (256x256)
- favicon.ico

## Other files to change

- Change `package.json` with your projects name
- Change the title and theme-color in `src/app.html`
- Change the theme_color in `static/manifest.json`

## PicoCSS

It's possible to change the default colours of PicoCSS. Go to [Pico's customization](https://picocss.com/docs/customization.html), select your favourite colour and paste the `overriding CSS variables` in `static/css.style.css`.
