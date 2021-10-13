package by.emaptc.LibraryProject.entity.transfer;

import java.io.Serializable;
import java.util.Objects;

public class UserLogin implements Serializable {
    private int id;
    private String roleName;
    private String status;

    public UserLogin(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public UserLogin() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return getId() == userLogin.getId() && Objects.equals(getRoleName(), userLogin.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }
}
