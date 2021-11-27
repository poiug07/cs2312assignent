public class ExItemNotAvailable extends ExInvalidOperation {
    public ExItemNotAvailable() {
        super("Item not available.");
    }

    public ExItemNotAvailable(String msg) {
        super(msg);
    }
}
