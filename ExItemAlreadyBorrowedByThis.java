public class ExItemAlreadyBorrowedByThis extends Exception {
    public ExItemAlreadyBorrowedByThis() {
        super("The item is already borrowed by the same member.");
    }

    public ExItemAlreadyBorrowedByThis(String msg) {
        super(msg);
    }
}
