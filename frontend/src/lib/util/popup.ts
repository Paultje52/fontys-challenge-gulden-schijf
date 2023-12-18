export default function popup(
  path: string,
  options?: { width?: number; height?: number }
) {
  const parsedPath = new URL(path, window.location.href);
  path = parsedPath.hash ? `${path.split("#")[0]}#${path.split("#")[1]}` : path;

  const dualScreenLeft =
    window.screenLeft !== undefined ? window.screenLeft : window.screenX;
  const dualScreenTop =
    window.screenTop !== undefined ? window.screenTop : window.screenY;

  const width = window.innerWidth
    ? window.innerWidth
    : document.documentElement.clientWidth
    ? document.documentElement.clientWidth
    : screen.width;
  const height = window.innerHeight
    ? window.innerHeight
    : document.documentElement.clientHeight
    ? document.documentElement.clientHeight
    : screen.height;

  const popupWidth = options?.width ?? 600;
  const popupHeight = options?.height ?? 800;

  const systemZoom = width / window.screen.availWidth;
  const left = (width - popupWidth) / 2 / systemZoom + dualScreenLeft;
  const top = (height - popupHeight) / 2 / systemZoom + dualScreenTop;
  const newWindow = window.open(
    path,
    path,
    `
    scrollbars=no,
    width=${popupWidth / systemZoom}, 
    height=${popupHeight / systemZoom}, 
    top=${top}, 
    left=${left}
    `
  );

  if (window.focus) newWindow.focus();
}
