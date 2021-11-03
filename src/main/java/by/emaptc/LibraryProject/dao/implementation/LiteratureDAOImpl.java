package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.LiteratureDAO;
import by.emaptc.LibraryProject.dao.pool.ConnectionManager;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.enumEntity.Genre;
import by.emaptc.LibraryProject.entity.enumEntity.LiteratureType;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.util.*;

public class LiteratureDAOImpl implements LiteratureDAO {
    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();
    private static final String SQL_UPDATE_QUERY="UPDATE literatures SET name=?,author=?,genres=?,publisher=?,type=?  where l_id =?";
    private static final String SQL_DELETE_QUERY="DELETE  FROM literatures where l_id=?";
    private static final String SQL_READ_BY_ID_QUERY="SELECT * FROM literatures where l_id=?";
    private static final String SQL_READ_ALL_QUERY="SELECT * FROM literatures";
    private static final String SQL_INSERT="INSERT INTO literatures (name, author ,genres,isAvailable,type, pages,publisher) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_READ_ALL_USER_LITERATURE_QUERY="SELECT * FROM literature_managements "+"INNER JOIN literatures ON literature_managements.literature_id=literatures.l_id "+"WHERE user_id=? AND issue_status!='CLOSED'";
    private static final String SQL_ROW_CALC="SELECT SQL_CALC_FOUND_ROWS * from literatures limit ";
    private final String SQL_USER_ROW_CALC="SELECT SQL_CALC_FOUND_ROWS * from literatures limit ";
    protected static final String ID_COLUMN_LABEL = "l_id";
    private int noOfRecords;

    /**
     *
     * @param literature {@code Literature} entity which will be manipulated
     * @return {@code int } id of last inserted entity
     * @throws DAOException
     */
    public int add(Literature literature) throws DAOException {
        startTransaction();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fetchSet(preparedStatement,literature);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DAOException("no generated keys found");
                }
            }
        }catch (SQLException exception) {
            rollbackTransaction();
            throw new DAOException(exception.getMessage(), exception);
        }
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


    private Literature buildEntity(ResultSet result) throws DAOException {
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

    private List<Literature> getEntities (String sqlQuery, List < String > params) throws DAOException {
        startTransaction();
        List <Literature> list=new LinkedList<>();
        try (Connection connection =getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            buildStatement(params,preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while(resultSet.next()){
                    list.add(buildEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            close();
        }
        return list;
    }


    protected Literature getEntity (String sqlQuery, List < String > params) throws DAOException {
        startTransaction();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(params,preparedStatement);
            try(ResultSet   resultSet = preparedStatement.executeQuery();) {
                return resultSet.next() ? buildEntity(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }



    public List<Literature> readAll(int offset,int noOfPages) throws DAOException {
        startTransaction();
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
        }finally {
            close();
        }
        return literatureList;
    }


    private void fetchSet(PreparedStatement stmt, Literature entity) throws SQLException {
        stmt.setString(1,entity.getLiteratureName());
        stmt.setString(2,entity.getAuthor());
        stmt.setString(3,entity.getGenre().toString());
        stmt.setBoolean(4,entity.isAvailable());
        stmt.setString(5,entity.getLiteratureType().toString());
        stmt.setInt(6,entity.getBookPages());
        stmt.setString(7,entity.getPublisher());
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param parameters {@code List<String> list of parameters which will use in SQL QUERY execution
     * @throws DAOException
     */
    private void executeQuery(String sqlQuery, List<String> parameters) throws DAOException {
        startTransaction();
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(parameters,preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            rollbackTransaction();
            throw new DAOException(exception.getMessage(), exception);
        }finally {
            close();
        }
    }

    /**
     *Initialize statement from given list of parameters
     *
     * @param parameters {@code List<String>} list of parameters which will use in SQL QUERY execution
     * @param preparedStatement {@code PreparedStatement}
     * @throws DAOException
     */
    private void buildStatement ( List < String > parameters, PreparedStatement preparedStatement) throws DAOException {
        try {
            if (parameters != null) {
                int parameterIndex = 1;
                for (String parameter : parameters) {
                    preparedStatement.setObject(parameterIndex++, parameter);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    private void startTransaction() {
        ConnectionManager connectionManager = new ConnectionManager();
        threadLocal.set(connectionManager);
    }

    private void rollbackTransaction() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.rollbackTransaction();
    }

    private void close() {
        ConnectionManager connectionManager = threadLocal.get();
        connectionManager.close();
        threadLocal.remove();
    }

    private Connection getConnection() {
        ConnectionManager cm = threadLocal.get();
        if (cm != null) {
            return cm.getConnection();
        }
        startTransaction();
        return threadLocal.get().getConnection();
    }

}
