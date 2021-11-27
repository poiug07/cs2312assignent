public class ExItemBorrowedByAnother extends ExInvalidOperation {
    public ExItemBorrowedByAnother() {
        super("The item is not borrowed by this member.");
    }

    public ExItemBorrowedByAnother(String msg) {
        super(msg);
    }
}