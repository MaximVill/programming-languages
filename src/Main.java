public class Main {
    public static void main(String[] args) {
        Balance balance1 = new Balance();
        balance1.addRight(3);
        balance1.addLeft(2);
        balance1.result();

        Balance balance2 = new Balance();
        balance2.addRight(2);
        balance2.addLeft(3);
        balance2.result();

        Balance balance3 = new Balance();
        balance3.addRight(3);
        balance3.addLeft(3);
        balance3.result();
    }
}