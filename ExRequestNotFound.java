public class ExRequestNotFound extends Exception {
    public ExRequestNotFound() {
        super("Request record is not found.");
    }

    public ExRequestNotFound(String msg) {
        super(msg);
    }
}
