// все классы для работы с аннотациями
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // сохраняем аннотацию до момента выполнения программы
@Target(ElementType.METHOD) // эту аннотацию можно ставить только на методы

// аннотация, которая будет говорить сколько раз выполнять программу
public @interface Repeat {
    int times();
}