public class ExInvalidOperation extends Exception {
    // General exception for exceptions occured due to wrong nature of commands,
    // That is action aware user will never cause this exception
    public ExInvalidOperation() {
        super("Invalid operation!");
    }

    public ExInvalidOperation(String msg) {
        super(msg);
    }
}
