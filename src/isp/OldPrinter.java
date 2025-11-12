package isp;

/**
 * Возможно, этот класс выполняет слишком много задач, которые ему не свойственны.
 * Проведите рефакторинг в соответствии с принципом ISP.
 */
public class OldPrinter implements Machine {

    @Override
    public void print(String document) {
        System.out.println("Печатаю: " + document);
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException("Сканирование не поддерживается!");
    }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException("Факс не поддерживается!");
    }
}