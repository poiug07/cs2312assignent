public class ExObjectNotFound extends Exception {
    // General exception for object or relationship not found.
    public ExObjectNotFound() {
        super("Object not found!");
    }

    public ExObjectNotFound(String msg) {
        super(msg);
    }
}