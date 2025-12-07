package model;

public class Book {
    private int id;
    private int visitorId;
    private String title, author, isbn, publisher;
    private int publishingYear;

    public Book(int id, int visitorId, String title, String author, int publishingYear, String isbn, String publisher) {
        this.id = id;
        this.visitorId = visitorId;
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public int getId() { return id; }
    public int getVisitorId() { return visitorId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishingYear() { return publishingYear; }
    public String getIsbn() { return isbn; }
    public String getPublisher() { return publisher; }
}