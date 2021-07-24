package by.emaptc.LibraryProject.beans;

import java.util.Objects;

public class Account {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;

    @Override
    public int hashCode(){
        int result=11;
        result+=result*4+Integer.hashCode(id);
        result+=result*5+name.hashCode();
        result+=result*5+surname.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(name, account.name)
                && Objects.equals(surname, account.surname) &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password);
    }
}
