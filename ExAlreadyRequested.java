public class ExAlreadyRequested extends ExInvalidOperation {
    public ExAlreadyRequested() {
        super("The same member has already requested the item.");
    }

    public ExAlreadyRequested(String msg) {
        super(msg);
    }

}
