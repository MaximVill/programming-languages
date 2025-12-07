package util;

import dao.BookDao;
import dao.VisitorDao;
import model.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonDataLoader {
    private final VisitorDao visitorDao = new VisitorDao();
    private final BookDao bookDao = new BookDao();

    public void loadFromJson(String filePath) throws IOException, java.sql.SQLException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray people = new JSONArray(content);

        for (int i = 0; i < people.length(); i++) {
            JSONObject p = people.getJSONObject(i);
            int visitorId = visitorDao.findOrCreate(
                    p.getString("name"),
                    p.getString("surname"),
                    p.getString("phone"),
                    p.getBoolean("subscribed")
            );

            JSONArray books = p.getJSONArray("favoriteBooks");
            for (int j = 0; j < books.length(); j++) {
                JSONObject b = books.getJSONObject(j);
                bookDao.insert(
                        visitorId,
                        b.getString("name"),
                        b.getString("author"),
                        b.getInt("publishingYear"),
                        b.getString("isbn"),
                        b.getString("publisher")
                );
            }
        }
    }
}