public class ExEntityRegistration extends Exception {
    // General exception for object creation or registration errors
    public ExEntityRegistration() {
        super("Error happened during object creation.");
    }

    public ExEntityRegistration(String msg) {
        super(msg);
    }
}
