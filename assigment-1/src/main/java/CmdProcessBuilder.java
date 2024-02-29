import java.io.*;

public class CmdProcessBuilder {
    public static String execute(String args[]) throws InterruptedException, IOException {
        String arguments = "";
        for (String arg : args)
            arguments = arguments.concat(" " + arg);

        String filePath = "out/artifacts/processes_jar/processes.jar";

        ProcessBuilder builder = new ProcessBuilder("java", "-jar", filePath, arguments);

        final Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String output = "";
        String line;
        while ((line = br.readLine()) != null) {
            output = output.concat(line);
        }
        return output;
    }
}