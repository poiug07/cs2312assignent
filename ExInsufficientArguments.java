public class ExInsufficientArguments extends Exception {
    // No need to create general exception class for this, because it is only
    // command exception
    public ExInsufficientArguments() {
        super("Insufficient command arguments.");
    }

    public ExInsufficientArguments(String msg) {
        super(msg);
    }
}
