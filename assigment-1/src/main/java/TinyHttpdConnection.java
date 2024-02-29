import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

class TinyHttpdConnection extends Thread {

    ArrayList<Route> routes = new ArrayList<>();

    Socket sock;

    PrintStream ps = null;
    OutputStream out = null;

    TinyHttpdConnection(Socket s) {
        routes.add(new Route("", "index.html", (Map<String, String> params, OutputCallback callback) -> {
            callback.onSuccess(null);
        }));
        routes.add(new Route("favicon.ico", "favicon.ico", (Map<String, String> params, OutputCallback callback) -> {
            callback.onSuccess(null);
        }));
        routes.add(new Route("process", null, (Map<String, String> params, OutputCallback callback) -> {
            try {
                if (params.get("par1") == null) throw new Exception("Invalid parameters.");
                String output = CmdProcessBuilder.execute(new String[]{params.get("par1")});
                callback.onSuccess(output);
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
                callback.onError(e.toString());
            }
        }));

        sock = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    @Override
    public void run() {
        System.out.println("=========");
        System.out.println("Connected on port " + sock.getPort());
        try {
            // OPEN SOCKETS FOR READING AND WRITING
            BufferedReader d = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = sock.getOutputStream();
            ps = new PrintStream(out);
            // READ REQUEST
            String req = d.readLine();
            if (req == null) return;
            System.out.println("Request: " + req);
            // READ REQUEST HEADERS
            String head = d.readLine();
            while(head != null && head.length() > 0) {
                System.out.println("Header: " + head);
                head = d.readLine();
            }

            // PARSE REQUEST
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                if ((req = st.nextToken()).startsWith("/")) {
                    req = req.substring(1);
                }

                Map<String, String> params = new HashMap<>();
                if (req.contains("?")) {
                    String rawArgs = req.split("\\?")[1];
                    req = req.split("\\?")[0];
                    String[] rawParams = rawArgs.split("&");
                    for (String param : rawParams) {
                        params.put(param.split("=")[0], param.split("=")[1]);
                    }
                }

                Route route = this.getRoute(req);
                if (route == null) {
                    this.onErrorResponse();
                    return;
                }

                route.task.run(params, new OutputCallback() {
                    @Override
                    public void onSuccess(String output) {
                        onSuccessResponse(route, output);
                    }

                    @Override
                    public void onError(String err) {
                        onErrorResponse(route);
                    }
                });
            } else {
                onErrorResponse();
            }
        } catch (Exception e) { // Let's catch all generic I/O troubles
            this.onErrorResponse();
        } finally { // the following code is ALWAYS executed
            try {
                // let's close all channels
                this.ps = null;
                out.close();
                this.out = null;
                sock.close();
            } catch (IOException ex) {
                System.out.println("I/O error on close" + ex);
            }
        }
    }

    private Route getRoute(String token) {
        Optional<Route> correspondingRoute = this.routes.stream().filter((route) -> route.path.equals(token)).findFirst();
        return correspondingRoute.orElse(null);
    }

    private void onSuccessResponse(Route route, String output) {
        try {
            System.out.println("Output: " + output);

            FileInputStream fis = null;
            byte[] data = null;

            if (route.resource != null) {
                fis = new FileInputStream("Documents/" + route.resource);
                data = new byte[fis.available()];
                fis.read(data);
            } else {
                data = this.buildHtml(output);
            }

            int responseLength = data.length;

            // Sending Headers
            ps.print("HTTP/1.1 200 OK\r\n");
            ps.print("Content-Length: " + responseLength + "\r\n");
            ps.print("Content-Type: text/html\r\n");
            ps.print("\r\n");

            out.write(data);
            if (fis != null)
                fis.close();
        } catch (FileNotFoundException e) {
            ps.print("HTTP/1.1 404 Not Found \r\n\r\n");
            System.out.println("404 Not Found: " + route.path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void onErrorResponse() {
        ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
        System.out.println("400 Bad Request");
    }

    private void onErrorResponse(Route route) {
        ps.print("HTTP/1.1 400 Bad Request\r\n\r\n");
        System.out.println("400 Bad Request: " + route.path);
    }

    private byte[] buildHtml(String output) {
        String res = "";

        res += "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head style=\"text-align: center\">" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Assignment 1</title>" +
                "</head>" +
                "<body style=\"text-align: center\">";

        res += "<h3>Output: " + output + "</h3>";

        res += "<br><br><br><br>" +
                "</body>" +
                "</html>";

        return res.getBytes();
    }
}
