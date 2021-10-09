package by.emaptc.LibraryProject.entity;

import by.emaptc.LibraryProject.entity.enums.Role;

import java.io.Serializable;
import java.util.Objects;

public class User extends Person implements Serializable {
    private String username;
    private String email;
    private String password;
    private Role role;
    private String status;

    public User( ) {

    }

    public User(String name, String surname,
                String username, String password,
                Role role) {
        super(name,surname);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode(){
        int result=11;
        result+=result*5+name.hashCode();
        result+=result*5+ lastName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(lastName, user.lastName) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public String toString() {
        return "Account{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + lastName + '\'' +
                '}';
    }
}
