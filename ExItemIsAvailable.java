public class ExItemIsAvailable extends ExInvalidOperation {
    public ExItemIsAvailable() {
        super("The item is currently available.");
    }

    public ExItemIsAvailable(String msg) {
        super(msg);
    }
}
