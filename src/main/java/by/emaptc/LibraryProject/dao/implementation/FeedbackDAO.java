package by.emaptc.LibraryProject.dao.implementation;

import by.emaptc.LibraryProject.dao.pool.ConnectionManager;
import by.emaptc.LibraryProject.entity.Feedback;
import by.emaptc.LibraryProject.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    private static final ThreadLocal<ConnectionManager> threadLocal = new ThreadLocal<>();
    private static final String SQL_INSERT_FEEDBACK="INSERT INTO feedbacks (f_user_id, f_literature_id ,rating, comment) VALUES(?,?,?,?)";
    private static final String SQL_USER_LIKES ="SELECT MAX(rating) from feedbacks limit 1,3 ";
    protected  final String ID_COLUMN_LABEL = "f_id";

    public int insert(Feedback feedback) throws DAOException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(SQL_INSERT_FEEDBACK, Statement.RETURN_GENERATED_KEYS)) {
            fetchSet(preparedStatement,feedback);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new DAOException("no generated keys found");
                }
            }
        }catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    public List<Feedback> mostLiked()throws DAOException{
        List<Feedback> feedbacks=new ArrayList<>();
        try(Connection connection=getConnection();
            Statement statement=connection.createStatement()) {
            try(ResultSet resultSet=statement.executeQuery(SQL_USER_LIKES);) {
                while (resultSet.next()){
                    feedbacks.add(buildEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return feedbacks;
    }


    protected void fetchSet(PreparedStatement stmt, Feedback entity) throws SQLException {
        stmt.setInt(1,entity.getUserId());
        stmt.setInt(2,entity.getBookId());
        stmt.setInt(3,entity.getMark());
        stmt.setString(4,entity.getComment());
    }


    private Feedback buildEntity(ResultSet result) throws DAOException {
        try {
            Feedback feedback = new Feedback();
            feedback.setId(result.getInt(ID_COLUMN_LABEL));
            feedback.setUserId((result.getInt("f_user_id")));
            feedback.setBookId(result.getInt("f_literature_id"));
            feedback.setMark(result.getInt("rating"));
            feedback.setComment(result.getString("comment"));
            return feedback;
        }catch (SQLException e){
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