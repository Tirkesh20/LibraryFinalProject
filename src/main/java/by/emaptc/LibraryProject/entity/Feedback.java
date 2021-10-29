package by.emaptc.LibraryProject.entity;

import java.util.Objects;

public class Feedback extends Entity {
    private int userId;
    private int bookId;
    private String comment;
    private int mark;

    public Feedback() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Feedback feedback = (Feedback) o;
        return getUserId() == feedback.getUserId() && getBookId() == feedback.getBookId() && Float.compare(feedback.getMark(), getMark()) == 0 && Objects.equals(getComment(), feedback.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getBookId(), getComment(), getMark());
    }
}
