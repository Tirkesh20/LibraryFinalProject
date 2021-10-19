package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enums.Genre;
import by.emaptc.LibraryProject.entity.enums.LiteratureType;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteratureDAO extends AbstractDAO<Literature> {

    private final String SQL_UPDATE_QUERY="UPDATE literatures SET name=?,author=?,genres=?,publisher=?,type=?  where id =?";
    private final String SQL_DELETE_QUERY="DELETE  FROM literatures where id=?";
    private String SQL_READ_BY_ID_QUERY="SELECT FROM literatures where id=?";
    private String SQL_READ_ALL_QUERY="SELECT * FROM literatures";
    private final String SQL_READ_ALL_USER_LITERATURE_QUERY="SELECT *FROM literature_managements WHERE user_id=?"+"INNER JOIN literatures ON literature_managements.literature_id=literatures.id";

    public int insertLiterature(Literature user) throws DAOException {
        String fields = "insert into literatures (name, lastname ,username, password, email, status,publisher) values(?,?,?,?,?,?,?)";
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

    public List<Literature> returnUserLiteratures(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        return   getEntities(SQL_READ_ALL_USER_LITERATURE_QUERY,params);
    }
    public List<Literature> readByGenre(Genre genre) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(genre));
        return getEntities(SQL_READ_ALL_QUERY,params);
    }

    public void deleteById(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(SQL_DELETE_QUERY,params);
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
    public Literature buildEntity(ResultSet result) throws DAOException {
        try {
            Literature literature = new Literature();
            literature.setId(result.getInt(ID_COLUMN_LABEL));
            literature.setLiteratureName((result.getString("name")));
            literature.setGenre(Genre.valueOf(result.getString("genre")));
            literature.setAuthor(result.getString("author"));
            literature.setLiteratureType(LiteratureType.valueOf(result.getString("type")));
            literature.setAvailable(Boolean.parseBoolean(result.getString("isAvailable")));
            literature.setBookPages(result.getInt("pages"));
            literature.setPublisher(result.getString("publisher"));
            return literature;
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }}

    public void update(Literature literature) throws DAOException {
        String id=String.valueOf(literature.getId());
        String author=literature.getAuthor();
        String genre=literature.getGenre().toString();
        String type=literature.getLiteratureType().toString();
        String publisher=literature.getPublisher();
        List<String> params = Arrays.asList(id,author,genre,type,publisher);
        executeQuery(SQL_UPDATE_QUERY, params);
    }

    public List<Literature> readAll() throws DAOException {
        Statement statement=null;
        try {
            connection=getConnection();
             statement=connection.createStatement();
            resultSet=statement.executeQuery(SQL_READ_ALL_QUERY);
            List<Literature> literatureList=new ArrayList<>();
            while (resultSet.next()){
//                Literature literature=buildEntity(resultSet);???
                literatureList.add(buildEntity(resultSet));
            }
            return literatureList;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }finally {
            closeConnection(connection,statement,resultSet);
        }
    }
}
