package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.LiteratureManagementDAO;
import by.emaptc.LibraryProject.dao.pool.ConnectionManager;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enumEntity.Status;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LiteratureManagementDAOImpl implements LiteratureManagementDAO {
    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();
    private static final String SQL_DELETE_QUERY="DELETE  from literature_managements where lm_id=?";
    private static final String SQL_READ_BY_ID_QUERY="Select * from literature_managements where lm_id=?";
    private static final String SQL_READ_BY_USER_ID="SELECT FROM literature_managements where user_id=?";
    private static final String SQL_UPDATE_ISSUE_STATUS="UPDATE  literature_managements SET issue_status=? where lm_id=? ";
    private static final String SQL_INSERT="INSERT INTO literature_managements ( user_id, literature_id,issue_status, date_of_give, date_to_return) VALUES(?,?,?,?,?)";
    private static final String SQL_COUNT_ISSUES="SELECT COUNT(*) FROM literature_managements where user_id=? and issue_status=?";
    private static final String SQL_EXPIRED_ISSUES=" SELECT * FROM literature_managements"+"WHERE date_to_return < NOW()"+" ORDER BY expiry_date ASC LIMIT 0,30";
    private static final String SQL_RETURN_ISSUE="UPDATE  literature_managements SET issue_status='CLOSED' WHERE user_id=? AND literature_id=? AND literature_managements.issue_status!='CLOSED'";
    protected static final String ID_COLUMN_LABEL = "lm_id";


    public LiteratureManagement readByID(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        return getEntity(SQL_READ_BY_ID_QUERY,params);
    }
    public LiteratureManagement readByStatus(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        return getEntity(SQL_EXPIRED_ISSUES,params);
    }
    public List<LiteratureManagement> readByUserId(int id) throws DAOException{
        List<String> params=Collections.singletonList(String.valueOf(id));
        return getEntities(SQL_READ_BY_USER_ID,params);
    }

    public void updateIssueStatus(int issue_id,String status)throws DAOException{
        String id=String.valueOf(issue_id);
        List<String> params = Arrays.asList(status,id);
        executeQuery(SQL_UPDATE_ISSUE_STATUS,params);
    }


    @Override
    public void returnIssue(int userId, int literatureId) throws DAOException {
        String uId=String.valueOf(userId);
        String lId=String.valueOf(literatureId);
        List<String> params=Arrays.asList(uId,lId);
  executeQuery(SQL_RETURN_ISSUE,params);
    }

    @Override
    public boolean userHasLiterature(int user_id,int literatureId) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_COUNT_ISSUES)){
            preparedStatement.setInt(1,user_id);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return true;
                }
            }
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage());
        }finally {
            close();
        }
        return false;
    }

    public void deleteById(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(SQL_DELETE_QUERY, params);
    }
    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param params {@code List<String>}list of parameters which will be executed with prepared statement
     * @return {@code int } id of last inserted entity
     * @throws DAOException custom exception for catching SQL exception
     */

    private List<LiteratureManagement> getEntities (String sqlQuery, List < String > params) throws DAOException {
        List <LiteratureManagement> list=new LinkedList<>();
        try(Connection connection =getConnection();
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

    public int insert(LiteratureManagement literatureManagement) throws DAOException {
        startTransaction();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fetchSet(preparedStatement,literatureManagement);
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
        }finally {
            close();
        }
    }

    /**
     *
     * @param result {@code ResultSet}
     * @return {@code Literature} creates and returns entity with initialized fields
     * @throws DAOException custom exception for catching SQL exception
     */

    private LiteratureManagement buildEntity(ResultSet result) throws DAOException {
        try {
            LiteratureManagement literature = new LiteratureManagement();
            literature.setId(result.getInt(ID_COLUMN_LABEL));
            literature.setLiterature_id(Integer.parseInt(result.getString("literature_id")));
            literature.setUser_id(Integer.parseInt(result.getString("user_id")));
            literature.setStatus(Status.valueOf(result.getString("issue_status")));
            literature.setDateOfGive(result.getObject("date_of_give",OffsetDateTime.class));
            literature.setDateToReturn(result.getObject("date_to_return",OffsetDateTime.class));
            return literature;
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }}


    /**
     *initialize fields of given entity
     * @param stmt {@code PreparedStatement}
     * @param entity {@code LiteratureManagement}
     * @throws SQLException JDBC SQl exception
     */

    private void fetchSet(PreparedStatement stmt, LiteratureManagement entity) throws SQLException {
        stmt.setInt(1, entity.getUser_id());
        stmt.setInt(2, entity.getLiterature_id());
        stmt.setString(3, entity.getStatus().toString());
        stmt.setObject(4, entity.getDateOfGive());
        stmt.setObject(5,  entity.getDateToReturn());
    }

    /**
     *
     * @param userId{@code int} value which uniquely indicates the user
     * @return {@code int} count of issues user owm
     * @throws DAOException custom exception for catching SQL exception
     */
    public int countUserIssues(int userId) throws DAOException {
        String id=String.valueOf(userId);
        String status="ISSUED";
        List<String> params = Arrays.asList(id, status);
        int count=0;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_COUNT_ISSUES)){
            buildStatement(params,preparedStatement);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    count= resultSet.getInt(1);
                }
            }
            return count;
        }catch (SQLException | DAOException exception) {
            throw new DAOException(exception.getMessage());
        }finally {
            close();
        }
    }

    private LiteratureManagement getEntity (String sqlQuery, List < String > params) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(params,preparedStatement);
            try(ResultSet   resultSet = preparedStatement.executeQuery();) {
                return resultSet.next() ? buildEntity(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }finally {
            close();
        }
    }

    /**
     *Initialize statement from given list of parameters
     *
     * @param parameters {@code List<String>} list of parameters which will use in SQL QUERY execution
     * @param preparedStatement {@code PreparedStatement}
     * @throws DAOException custom exception for catching SQL exception
     */
    private void buildStatement ( List < String > parameters, PreparedStatement preparedStatement) throws DAOException {
        try {
            if (parameters != null) {
                int parameterIndex = 1;
                for (String parameter : parameters) {
                    System.out.println(parameterIndex+ parameter);
                    preparedStatement.setObject(parameterIndex++, parameter);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    /**
     *
     * @param sqlQuery {@code String} sql query for given task
     * @param parameters {@code List<String> list of parameters which will use in SQL QUERY execution
     * @throws DAOException custom exception for catching SQL exception
     */
    private void executeQuery(String sqlQuery, List<String> parameters) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            buildStatement(parameters,preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            rollbackTransaction();
            throw new DAOException(exception.getMessage());
        }finally {
            close();
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
