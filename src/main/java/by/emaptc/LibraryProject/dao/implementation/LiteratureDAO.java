package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enumEntity.Genre;
import by.emaptc.LibraryProject.entity.enumEntity.LiteratureType;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.util.*;

public class LiteratureDAO extends AbstractDAO<Literature> {

    private static final String SQL_UPDATE_QUERY="UPDATE literatures SET name=?,author=?,genres=?,publisher=?,type=?  where id =?";
    private static final String SQL_DELETE_QUERY="DELETE  FROM literatures where id=?";
    private static final String SQL_READ_BY_ID_QUERY="SELECT * FROM literatures where id=?";
    private static final String SQL_READ_ALL_QUERY="SELECT * FROM literatures";
    private static final String SQL_INSERT="INSERT INTO literatures (name, lastname ,username, password, email, status,publisher) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_READ_ALL_USER_LITERATURE_QUERY="SELECT * FROM literature_managements "+"INNER JOIN literatures ON literature_managements.literature_id=literatures.id "+"WHERE user_id=?";
    private static final String SQL_ROW_CALC="SELECT SQL_CALC_FOUND_ROWS * from literatures limit ";
    private final String SQL_USER_ROW_CALC="SELECT SQL_CALC_FOUND_ROWS * from literatures limit ";
    protected static final String ID_COLUMN_LABEL = "l_id";
    private int noOfRecords;


    public int insertLiterature(Literature literature) throws DAOException {
        return insertExecuteQuery(SQL_INSERT, literature);
    }

    public void deleteLiteratureByID(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(SQL_DELETE_QUERY, params);
    }

    public int getNoOfRecords() {
        return noOfRecords;
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
            literature.setGenre(Genre.valueOf(result.getString("genres").toUpperCase(Locale.ROOT)));
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

    public List<Literature> readAll(int offset,int noOfPages) throws DAOException {
        List<Literature> literatureList=new ArrayList<Literature>();
        try(Connection connection=getConnection();
           Statement statement=connection.createStatement()) {
            try(ResultSet resultSet=statement.executeQuery(SQL_ROW_CALC+offset+","+noOfPages);) {
                while (resultSet.next()){
                    literatureList.add(buildEntity(resultSet));
                }
            }try(ResultSet resultSet=statement.executeQuery("SELECT FOUND_ROWS()")) {
                if (resultSet.next())
                    this.noOfRecords=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return literatureList;
    }

    @Override
    protected void fetchSet(PreparedStatement stmt, Literature entity) throws SQLException {
        stmt.setString(1,entity.getLiteratureName());
        stmt.setString(2,entity.getAuthor());
        stmt.setString(3,entity.getGenre().toString());
        stmt.setBoolean(4,entity.isAvailable());
        stmt.setString(5,entity.getLiteratureType().toString());
        stmt.setInt(6,entity.getBookPages());
        stmt.setString(7,entity.getPublisher());
    }
}
