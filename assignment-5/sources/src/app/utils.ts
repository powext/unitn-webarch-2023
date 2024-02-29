export function imageExists(url: string) {
  let http = new XMLHttpRequest();

  http.open('HEAD', url, false);
  http.send();

  return http.status != 404;
}
