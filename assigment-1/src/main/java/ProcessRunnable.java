import java.util.Map;

public interface ProcessRunnable {
    void run(Map<String, String> input, OutputCallback callback);
}
