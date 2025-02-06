public class Prefix {
    public static void main(String[] args) {
        s = "*9+53";
        if (pref() && i == s.length()) {
            System.out.println("The string \"" + s + "\" is a prefix expression.");
        }
        else {
            System.out.println("The string \"" + s + "\" is not a prefix expression.");
        }
    }
    public static boolean pref() {
        if (op()) {
            if (pref()) {
                if (pref()) {
                    return true;
                }
            }
        }
        else if (dig()) {
            return true;
        }
        return false;
    }
    public static boolean op() {
        char ch;
        if (i < s.length() && ((ch = s.charAt(i)) == '+' || ch == '-' ||
        ch == '*' || ch == '/')) {
        ++i;
            return true;
        }
        return false;
    }
    public static boolean dig() {
    if (i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
    ++i;
        return true;
    }
    return false;
    }
    private static String s;
    private static int i;
}
