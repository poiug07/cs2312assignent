public class ExItemIdInUse extends Exception {
    public ExItemIdInUse() {
        super("Item ID already in use!");
    }

    public ExItemIdInUse(String message) {
        super(message);
    }
}
