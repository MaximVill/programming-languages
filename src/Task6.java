import java.util.List;
import java.util.stream.Collectors;

public class Task6 {
    public static void run(List<Visitor> visitors) {
        // №6. Вывести список всех сгенерированных SMS-сообщений на экран.
        // Среднее количество книг:
        double averageBooks = visitors.stream().mapToDouble(v -> v.getFavoriteBooks().size())
                .average().orElse(0.0); // 2.8

        // Группировка согласившихся на рассылку:
        List<SmsMessage> smsMessages = visitors.stream().filter(Visitor::isSubscribed).map(visitor -> {
            int count = visitor.getFavoriteBooks().size();
            String msg;

            if (count > averageBooks) {
                msg = "you are a bookworm";
            } else if (count < averageBooks) {
                msg = "read more";
            } else {
                msg = "fine";
            }

            SmsMessage sms = new SmsMessage();
            sms.setPhoneNumber(visitor.getPhone());
            sms.setMessage(msg);
            return sms;
        }).collect(Collectors.toList());

        // Вывод:
        System.out.println("\nЗАДАНИЕ 6, СПИСОК ВСЕХ СГЕНЕРИРОВАННЫХ ВСЕХ SMS-СООБЩЕНИЙ:");
        for (SmsMessage sms : smsMessages) {
            System.out.println(sms.getPhoneNumber() + " " + sms.getMessage());
        }
    }
}
