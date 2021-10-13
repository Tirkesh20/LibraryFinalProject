package by.emaptc.LibraryProject.entity;

import by.emaptc.LibraryProject.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.Objects;

public class LiteratureManagement extends Entity {
    private int user_id;
    private int literature_id;
    private LocalDateTime dateOfGive;
    private LocalDateTime dateToReturn;
    private Status status;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLiterature_id() {
        return literature_id;
    }

    public void setLiterature_id(int literature_id) {
        this.literature_id = literature_id;
    }

    public LocalDateTime getDateOfGive() {
        return dateOfGive;
    }

    public void setDateOfGive(LocalDateTime dateOfGive) {
        this.dateOfGive = dateOfGive;
    }

    public LocalDateTime getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(LocalDateTime dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LiteratureManagement that = (LiteratureManagement) o;
        return getId() == that.getId() && getUser_id() == that.getUser_id() && getLiterature_id() == that.getLiterature_id() && Objects.equals(getDateOfGive(), that.getDateOfGive()) && Objects.equals(getDateToReturn(), that.getDateToReturn()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUser_id(), getLiterature_id(), getDateOfGive(), getDateToReturn(), getStatus());
    }

    @Override
    public String toString() {
        return "LiteratureManagement{" +
                "id=" + getId() +
                ", user_id=" + user_id +
                ", literature_id=" + literature_id +
                ", dateOfGive=" + dateOfGive +
                ", dateToReturn=" + dateToReturn +
                ", status=" + status +
                '}';
    }
}
