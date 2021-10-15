package by.emaptc.LibraryProject.entity;

import java.util.Objects;

public class Feedbacks extends Entity {
    private int userId;
    private int bookId;
    private String comment;
    private float mark;

    public Feedbacks(int userId, int bookId, String comment, float mark) {
        this.userId = userId;
        this.bookId = bookId;
        this.comment = comment;
        this.mark = mark;
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

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Feedbacks feedbacks = (Feedbacks) o;
        return getUserId() == feedbacks.getUserId() && getBookId() == feedbacks.getBookId() && Float.compare(feedbacks.getMark(), getMark()) == 0 && Objects.equals(getComment(), feedbacks.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getBookId(), getComment(), getMark());
    }
}
