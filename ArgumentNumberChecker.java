public class ArgumentNumberChecker {
    public static boolean pass(int num, String[] cmdParts) {
        // Later checking can be added here later
        return cmdParts.length == num;
    }
}
