public class Main {
    public static void main(String[] args) {
        OddEvenSeparator separator = new OddEvenSeparator();
        separator.addNumber(9147);
        separator.addNumber(234);
        separator.addNumber(2128194124);
        separator.addNumber(123);

        separator.even();
        separator.odd();
    }
}