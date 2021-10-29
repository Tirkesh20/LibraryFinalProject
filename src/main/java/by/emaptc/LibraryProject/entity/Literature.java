package by.emaptc.LibraryProject.entity;

import by.emaptc.LibraryProject.entity.enumEntity.Genre;
import by.emaptc.LibraryProject.entity.enumEntity.LiteratureType;

import java.util.Objects;

public class Literature extends Entity {
    private String literatureName;
    public String author;
    private Genre genre;
    private boolean isAvailable;
    private LiteratureType literatureType;
    private int bookPages;
    private String publisher;


    public Literature(){

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public String getLiteratureName() {
        return literatureName;
    }

    public void setLiteratureName(String literatureName) {
        this.literatureName = literatureName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Literature that = (Literature) o;
        return isAvailable() == that.isAvailable() && getBookPages() == that.getBookPages() && Objects.equals(getLiteratureName(), that.getLiteratureName()) && Objects.equals(getAuthor(), that.getAuthor()) && getGenre() == that.getGenre() && getLiteratureType() == that.getLiteratureType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLiteratureName(), getAuthor(), getGenre(), isAvailable(), getLiteratureType(), getBookPages());
    }

    @Override
    public String toString() {
        return "Literature{" +
                "name='" + literatureName + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", isAvailable=" + isAvailable +
                ", literatureType=" + literatureType +
                ", bookPages=" + bookPages +
                '}';
    }
}
