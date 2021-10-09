package by.emaptc.LibraryProject.entity.transfer;

import java.io.Serializable;
import java.util.Objects;

public class UserLogin implements Serializable {
    private int id;
    private String role;

    public UserLogin(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserLogin() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return getId() == userLogin.getId() && Objects.equals(getRole(), userLogin.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }
}
