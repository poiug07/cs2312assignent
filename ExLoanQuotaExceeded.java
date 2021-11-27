public class ExLoanQuotaExceeded extends Exception {
    public ExLoanQuotaExceeded() {
        super("Loan quota exceeded.");
    }

    public ExLoanQuotaExceeded(String msg) {
        super(msg);
    }
}
