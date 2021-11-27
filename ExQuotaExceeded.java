public class ExQuotaExceeded extends Exception {
    // General exception for some quota or limit exceeding
    public ExQuotaExceeded() {
        super("Some quota limit exceeded.");
    }

    public ExQuotaExceeded(String msg) {
        super(msg);
    }
}
