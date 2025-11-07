public class Bell {
    boolean isCalling = true;
    public void sound() {
        if (isCalling) {
            System.out.println("ding");
        } else {
            System.out.println("dong");
        }

        isCalling = !isCalling;
    }
}