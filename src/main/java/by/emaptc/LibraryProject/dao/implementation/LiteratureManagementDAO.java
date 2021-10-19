package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.AbstractDAO;
import by.emaptc.LibraryProject.entity.Literature;
import by.emaptc.LibraryProject.entity.LiteratureManagement;
import by.emaptc.LibraryProject.entity.enums.Genre;
import by.emaptc.LibraryProject.entity.enums.LiteratureType;
import by.emaptc.LibraryProject.entity.enums.Status;
import by.emaptc.LibraryProject.exceptions.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteratureManagementDAO  extends AbstractDAO<LiteratureManagement>{

    private final String SQL_DELETE_QUERY="DELETE  from literature_managements where id=?";
    private String SQL_READ_BY_ID_QUERY="Select  from literature_managements where id=?";
    private final String SQL_READ_BY_USER_ID="SELECT FROM literature_managements where user_id=?";
    private final String SQL_UPDATE_ISSUE_STATUS="UPDATE  literature_managements SET status where id=? ";

    public int insertLiterature(LiteratureManagement management) throws DAOException {
        String fields = "insert into literature_managements ( user_id ,issue_status, date_of_give, date_to_return, literature_id) values(?,?,?,?,?)";
        return insert(management, fields);
    }


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

    @Override
    protected LiteratureManagement buildEntity(ResultSet result) throws DAOException {
        try {
            LiteratureManagement literature = new LiteratureManagement();
            literature.setId(result.getInt(ID_COLUMN_LABEL));
            literature.setLiterature_id(Integer.parseInt(result.getString("literature_id")));
            literature.setUser_id(Integer.parseInt(result.getString("user_id")));
            literature.setStatus(Status.valueOf(result.getString("issue_status")));
            literature.setDateOfGive(result.getDate("date_of_give"));
            literature.setDateToReturn(result.getDate("date_to_return"));
            return literature;
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }}

}
