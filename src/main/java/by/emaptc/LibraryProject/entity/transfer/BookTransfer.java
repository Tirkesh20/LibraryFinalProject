package by.emaptc.LibraryProject.entity.transfer;

import by.emaptc.LibraryProject.entity.enums.Genre;

import java.io.Serializable;

public class BookTransfer implements Serializable {
    private int bookId;
    private String bookName;
    private Genre bookGenre;
}
