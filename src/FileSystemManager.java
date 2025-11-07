import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;

public class FileSystemManager {
    public static void main(String[] args) throws IOException {
        String surname = "Vill";
        String name = "Maxim";

        Path root = Paths.get(surname);

        // директория <surname>
        Files.createDirectories(root);
        System.out.println("Создана директория: " + root);

        // файл <name>
        Path mainFile = root.resolve(name);
        Files.createFile(mainFile);
        System.out.println("Создан файл: " + mainFile);

        // dir1/dir2/dir3 и копируем туда файл
        Path nestedDir = root.resolve("dir1/dir2/dir3");
        Files.createDirectories(nestedDir);
        Path copiedFile = nestedDir.resolve(name);
        Files.copy(mainFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Скопирован файл: " + copiedFile);

        // создаём file1 в dir1
        Path file1 = root.resolve("dir1/file1");
        Files.createFile(file1);
        System.out.println("Создан файл: " + file1);

        // создаём file2 в dir2
        Path file2 = root.resolve("dir1/dir2/file2");
        Files.createFile(file2);
        System.out.println("Создан файл: " + file2);

        // рекурсивный обход и вывод
        System.out.println("\nРекурсивный обход:");
        try (Stream<Path> paths = Files.walk(root)) {
            paths.sorted() // сортировка
                    .forEach(path -> {
                        if (Files.isDirectory(path)) {
                            System.out.println("D: " + path);
                        } else {
                            System.out.println("F: " + path);
                        }
                    });
        }

        // удаляем dir1 со всем содержимым
        try (Stream<Path> walk = Files.walk(root.resolve("dir1"))) {
            walk.sorted((a, b) -> Integer.compare(b.getNameCount(), a.getNameCount()))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("Не удалось удалить: " + path);
                        }
                    });
        }
        System.out.println("\nДиректория dir1 удалена.");
    }
}