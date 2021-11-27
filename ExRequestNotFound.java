public class ExRequestNotFound extends ExObjectNotFound {
    public ExRequestNotFound() {
        super("Request record is not found.");
    }

    public ExRequestNotFound(String msg) {
        super(msg);
    }
}
