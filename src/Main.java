import dip.EmailSender;
import dip.NotificationService;
import dip.SmsSender;
import isp.OldPrinter;
import isp.Printer;
import lsp.Bird;
import lsp.Flyable;
import lsp.Penguin;
import lsp.Sparrow;
import ocp.DiscountCalculator;
import ocp.RegularDiscountStrategy;
import ocp.SuperVipDiscountStrategy;
import ocp.VipDiscountStrategy;
import srp.ReportManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ---------- S ----------
        ReportManager manager = new ReportManager(List.of(5, 10, 15, 20));
        manager.generateReport();

        // ---------- O ----------
        DiscountCalculator calculator = new DiscountCalculator();
        System.out.println("Regular: " + calculator.calculateDiscount(new RegularDiscountStrategy(), 1000));
        System.out.println("VIP: " + calculator.calculateDiscount(new VipDiscountStrategy(), 1000));
        System.out.println("Super VIP: " + calculator.calculateDiscount(new SuperVipDiscountStrategy(), 1000));

        // ---------- L ----------
        displayBird(new Sparrow());
        displayBird(new Penguin());     // Здесь будет исключение

        // ---------- I ----------
        Printer printer = new OldPrinter();
        printer.print("Отчёт за неделю");

        // ---------- D ----------
        NotificationService emailService = new NotificationService(new EmailSender());
        emailService.send("Ваш заказ готов к выдаче!");

        NotificationService smsService = new NotificationService(new SmsSender());
        smsService.send("Ваш заказ готов к выдаче (SMS)!");
    }

    public static void displayBird(Bird bird) {
        bird.eat();
        if (bird instanceof Flyable) {
            ((Flyable) bird).fly();
        } else {
            System.out.println(bird.getClass().getSimpleName() + " не умеет летать.");
        }
    }
}