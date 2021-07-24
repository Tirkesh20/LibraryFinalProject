package by.emaptc.LibraryProject.beans;

import by.emaptc.LibraryProject.beans.enums.Genre;

public class Book {
    private int id;
    private String name;
    public String author;
    private Genre genre;
    private int bookPages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
