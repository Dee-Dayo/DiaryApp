package data.models;

import java.time.LocalDateTime;

public class Entry {
    private int id;
    private String title;
    private String author;
    private String body;
    private LocalDateTime dateCreated = LocalDateTime.now();

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Body='" + body + '\'' +
                ", Date Created=" + dateCreated + '\'';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
