public class ExItemIdInUse extends ExEntityRegistration {
    public ExItemIdInUse() {
        super("Item ID already in use!");
    }

    public ExItemIdInUse(String message) {
        super(message);
    }
}
