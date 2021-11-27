public class ExLoanQuotaExceeded extends ExQuotaExceeded {
    public ExLoanQuotaExceeded() {
        super("Loan quota exceeded.");
    }

    public ExLoanQuotaExceeded(String msg) {
        super(msg);
    }
}
