package by.emaptc.LibraryProject.entity;

import java.util.Date;
import java.util.Objects;

public class Author extends Person{
    private  String nickName;
    private Date yearOfBirth;

    public Author(String name, String surname,String nickName) {
        super(name, surname);
        this.nickName=nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return  ((Objects.equals(author.name, ((Author) o).name))
                &&(Objects.equals(author.nickName,((Author)o).nickName)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + lastName + '\'' +
                '}';
    }
}
