public class SystemDate extends Day {

    private static SystemDate instance;

    private SystemDate(String sDay) {
        super(sDay);
    }

    public static SystemDate getInstance() {
        return instance;
    }

    public static void createTheInstance(String sDay) {
        if (instance != null) {
            System.out.println("Cannot create one more system date instance.");
        } else {
            instance = new SystemDate(sDay);
        }
    }
}
