package by.emaptc.LibraryProject.entity;

import by.emaptc.LibraryProject.entity.enums.Status;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Objects;

public class LiteratureManagement extends Entity {
    private int user_id;
    private int literature_id;
    private Timestamp dateOfGive;
    private Timestamp dateToReturn;
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

    public Timestamp getDateOfGive() {
        return dateOfGive;
    }

    public void setDateOfGive(Timestamp dateOfGive) {
        this.dateOfGive = dateOfGive;
    }

    public Timestamp getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(Timestamp dateToReturn) {
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
