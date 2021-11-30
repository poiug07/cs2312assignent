public class ExRequestQuotaExceeded extends ExQuotaExceeded {
    public ExRequestQuotaExceeded() {
        super("Item request quota exceeded.");
    }

    public ExRequestQuotaExceeded(String msg) {
        super(msg);
    }

}
