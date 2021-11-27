public class ExMemberIdInUse extends ExEntityRegistration {
    public ExMemberIdInUse() {
        super("Member ID already in use!");
    }

    public ExMemberIdInUse(String message) {
        super(message);
    }
}
