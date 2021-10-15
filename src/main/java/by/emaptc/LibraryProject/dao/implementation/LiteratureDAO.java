package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enums.Genre;
import by.emaptc.LibraryProject.entity.enums.LiteratureType;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteratureDAO extends AbstractDAO<Literature> {

    private final String SQL_DELETE_QUERY="DELETE  from literatures where id=?";
    private String SQL_READ_BY_ID_QUERY="Select * from literatures where id=?";
    private String SQL_READ_ALL_QUERY="Select * from literatures where genre=?";

    public int insertLiterature(Literature user) throws DAOException {
        String fields = "insert into literatures (name, lastname ,username, password, email, status) values(?,?,?,?,?,?)";
        return insert(user, fields);
    }

    public void deleteLiteratureByID(int id) throws DAOException {
        String sqlQuery = "delete from literatures  where id = ?";
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(sqlQuery, params);
    }

    public Literature readByID(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        return getEntity(SQL_READ_BY_ID_QUERY,params);
    }

    public List<Literature> readByGenre(Genre genre) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(genre));
         return getEntities(SQL_READ_ALL_QUERY,params);
    }

    public int deleteById(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
       return executeQuery(SQL_DELETE_QUERY,params);
    }

    @Override
    protected List<String> getEntityParameters(Literature entity) {
        String literatureName = entity.getLiteratureName();
        String author=entity.getAuthor();
        String genre = entity.getGenre().toString();
        String literatureType = entity.getLiteratureType().toString();
        String isAvailable = String.valueOf(entity.isAvailable());
        return Arrays.asList(literatureName, author, genre, literatureType, isAvailable);
    }

    @Override
    public Literature buildEntity(ResultSet result) throws SQLException {
        Literature literature = new Literature();
        literature.setId(result.getInt(ID_COLUMN_LABEL));
        literature.setLiteratureName((result.getString("name")));
        literature.setGenre(Genre.valueOf(result.getString("genre")));
        literature.setAuthor(result.getString("author"));
        literature.setLiteratureType(LiteratureType.valueOf(result.getString("type")));
        literature.setAvailable(Boolean.parseBoolean(result.getString("isAvailable")));
        literature.setBookPages(result.getInt("pages"));
        return literature;
    }

}
