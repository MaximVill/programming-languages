public class Balance {
    int weightRight = 0;
    int weightLeft = 0;

    public void addRight(int weight) {
        weightRight += weight;
    }

    public void addLeft(int weight) {
        weightLeft += weight;
    }

    public void result() {
        if (weightRight == weightLeft) {
            System.out.println("=");
        }
        if (weightRight > weightLeft) {
            System.out.println("R");
        }
        if (weightRight < weightLeft) {
            System.out.println("L");
        }
    }
}