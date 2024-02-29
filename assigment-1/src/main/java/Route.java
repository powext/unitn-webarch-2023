public class Route {
    public String path;
    public String resource;
    public ProcessRunnable task;

    public Route(String path, String resource, ProcessRunnable task) {
        this.path = path;
        this.resource = resource;
        this.task = task;
    }
}
