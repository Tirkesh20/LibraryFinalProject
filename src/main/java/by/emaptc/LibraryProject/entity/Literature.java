package by.emaptc.LibraryProject.entity;

import by.emaptc.LibraryProject.entity.enums.Genre;
import by.emaptc.LibraryProject.entity.enums.LiteratureType;

import java.util.Objects;

public class Literature extends Entity {
    private String name;
    public Author author;
    private Genre genre;
    private boolean isAvailable;
    private String type;
    private LiteratureType literatureType;
    private int bookPages;


    public Literature(String name, Author author, Genre genre, boolean isAvailable, int bookPages, LiteratureType literatureType) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.bookPages = bookPages;
        this.literatureType = literatureType;
    }

    public Literature(){

    }

    public Literature(int id, String name, Author author, Genre genre, boolean isAvailable, int bookPages, LiteratureType literatureType) {
        super(id);
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.bookPages = bookPages;
        this.literatureType = literatureType;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LiteratureType getLiteratureType() {
        return literatureType;
    }

    public void setLiteratureType(LiteratureType literatureType) {
        this.literatureType = literatureType;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Literature that = (Literature) o;
        return isAvailable() == that.isAvailable() && getBookPages() == that.getBookPages() && Objects.equals(getName(), that.getName()) && Objects.equals(getAuthor(), that.getAuthor()) && getGenre() == that.getGenre() && getLiteratureType() == that.getLiteratureType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAuthor(), getGenre(), isAvailable(), getLiteratureType(), getBookPages());
    }

    @Override
    public String toString() {
        return "Literature{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", isAvailable=" + isAvailable +
                ", literatureType=" + literatureType +
                ", bookPages=" + bookPages +
                '}';
    }
}
