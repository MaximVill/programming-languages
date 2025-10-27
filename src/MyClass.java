public class MyClass {

    // Публичные методы
    public void publicMethod1() {
        System.out.println("Public method 1");
    }

    public void publicMethod2() {
        System.out.println("Public method 2");
    }

    // Защищённые методы
    @Repeat(times = 2)
    protected void protectedMethod1() {
        System.out.println("Protected method 1");
    }

    @Repeat(times = 1)
    protected void protectedMethod2(String msg) {
        System.out.println("Protected method 2: " + msg);
    }

    // Приватные методы
    @Repeat(times = 3)
    private void privateMethod1() {
        System.out.println("Private method 1");
    }

    @Repeat(times = 2)
    private void privateMethod2(int x, int y) {
        System.out.println("Private method 2: " + x + " + " + y + " = " + (x + y));
    }
}