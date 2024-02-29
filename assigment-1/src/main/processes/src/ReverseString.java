public class ReverseString {
    public static void main(String[] args) {
        if (args[0] == null) return;
        System.out.println(new StringBuilder(args[0]).reverse());
    }
}
