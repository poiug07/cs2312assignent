public class ExItemIsAvailable extends Exception {
    public ExItemIsAvailable() {
        super("The item is currently available.");
    }
    
    public ExItemIsAvailable(String msg) {
        super(msg);
    }
}
