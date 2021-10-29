package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enumEntity.Status;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteratureManagementDAO  extends AbstractDAO<LiteratureManagement>{

    private static final String SQL_DELETE_QUERY="DELETE  from literature_managements where id=?";
    private static final String SQL_READ_BY_ID_QUERY="Select  from literature_managements where id=?";
    private static final String SQL_READ_BY_USER_ID="SELECT FROM literature_managements where user_id=?";
    private static final String SQL_UPDATE_ISSUE_STATUS="UPDATE  literature_managements SET status where id=? ";
    private static final String SQL_INSERT="INSERT INTO literature_managements ( user_id, literature_id,issue_status, date_of_give, date_to_return) VALUES(?,?,?,?,?)";
    private static final String SQL_COUNT_ISSUES="SELECT COUNT(*) FROM literature_managements where user_id=? and issue_status=?";
    private static final String SQL_EXPIRED_ISSUES=" SELECT * FROM literature_managements"+"WHERE date_to_return < NOW()"+" ORDER BY expiry_date ASC LIMIT 0,30";
    protected static final String ID_COLUMN_LABEL = "lm_id";


    public LiteratureManagement readByID(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        return getEntity(SQL_READ_BY_ID_QUERY,params);
    }

    public List<LiteratureManagement> readByUserId(int id) throws DAOException{
        List<String> params=Collections.singletonList(String.valueOf(id));
        return getEntities(SQL_READ_BY_USER_ID,params);
    }

    public void updateIssueStatus(int issue_id,String status)throws DAOException{
        String id=String.valueOf(issue_id);
        List<String> params = Arrays.asList(id, status);
        executeQuery(SQL_UPDATE_ISSUE_STATUS,params);
    }
    public void deleteById(int id) throws DAOException {
        List<String> params = Collections.singletonList(String.valueOf(id));
        executeQuery(SQL_DELETE_QUERY, params);
    }

    public int insert(LiteratureManagement entity) throws DAOException {
        return insertExecuteQuery(SQL_INSERT,entity);
    }

    /**
     *
     * @param result {@code ResultSet}
     * @return {@code Literature} creates and returns entity with initialized fields
     * @throws DAOException my  Exception for DAO layer
     */
    @Override
    protected LiteratureManagement buildEntity(ResultSet result) throws DAOException {
        try {
            LiteratureManagement literature = new LiteratureManagement();
            literature.setId(result.getInt(ID_COLUMN_LABEL));
            literature.setLiterature_id(Integer.parseInt(result.getString("literature_id")));
            literature.setUser_id(Integer.parseInt(result.getString("user_id")));
            literature.setStatus(Status.valueOf(result.getString("issue_status")));
            literature.setDateOfGive(result.getObject("date_of_give ",OffsetDateTime.class));
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
    @Override
    protected void fetchSet(PreparedStatement stmt, LiteratureManagement entity) throws SQLException {
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
     * @throws DAOException my  Exception for DAO layer
     */
    public int countUserIssues(int userId) throws DAOException {
        String id=String.valueOf(userId);
        String status="ISSUED";
        List<String> params = Arrays.asList(id, status);
        int count=0;
        try (Connection connection = getConnection();
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
        }
    }
}
