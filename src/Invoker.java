import java.lang.reflect.Method;

public class Invoker {
    public static void main(String[] args) {
        try {
            MyClass obj = new MyClass();
            Method[] ms = MyClass.class.getDeclaredMethods(); // получаю все методы класса

            // есть ли аннотация @Repeat
            for (Method m : ms) {
                if (m.isAnnotationPresent(Repeat.class)) {
                    Repeat r = m.getAnnotation(Repeat.class);
                    m.setAccessible(true);
                    Object[] p = new Object[m.getParameterCount()];

                    // массив аргументов, чтобы передать в метод при вызове
                    for (int j = 0; j < p.length; j++) {
                        Class<?> t = m.getParameterTypes()[j];
                        p[j] = t == String.class ? "test" : t == int.class ? j + 1 : null;
                    }

                    // вызов метода необходимое количество раз
                    for (int i = 0; i < r.times(); i++) {
                        m.invoke(obj, p);
                    }
                }
            }
            // проверка на ошибки
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}